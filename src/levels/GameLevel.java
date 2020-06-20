package levels;

import java.awt.Color;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.GameOverScreen;
import animation.PauseScreen;
import animation.ReadySetGoScreen;
import eventhandlers.Counter;
import eventhandlers.BallRemover;
import eventhandlers.BlockRemover;
import eventhandlers.ScoreTrackingListener;
import gameelements.Paddle;
import gameelements.Ball;
import gameelements.Block;
import miscellaneous.GameEnvironment;
import miscellaneous.Sprite;
import miscellaneous.SpriteCollection;
import movement.Collidable;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;
import movement.Velocity;

import java.util.List;

/**
 * The type Miscellaneous.Game.
 */
public class GameLevel implements Animation {
    private LevelInformation level;
    private SpriteCollection sprites;
    private boolean running;
    private GameEnvironment environment;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private GUI gui;
    private Sleeper sleeper;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Color backgroundColor;
    private Paddle paddle;
    private AnimationRunner animationRunner;
    private int paddleHeight;
    private int horizontalBound;
    private int verticalBound;
    private int margin;
    private KeyboardSensor keyboard;
    private boolean isLast;
    private boolean countdownDisplayed;

    /**
     * this method constructs a game object.
     *
     * @param curLevel the cur level
     * @param guiParam the gui param
     * @param theKey   the the key
     * @param score    the score
     * @param horBound the horizontal bound
     * @param verBound the vertical bound
     * @param marg     the marg
     * @param last     the last
     */
    public GameLevel(LevelInformation curLevel,
                     GUI guiParam,
                     KeyboardSensor theKey,
                     Counter score,
                     int horBound,
                     int verBound,
                     int marg,
                     boolean last
                     ) {
        this.gui = guiParam;
        this.keyboard = theKey;
        this.isLast = last;
        this.level = curLevel;
        this.horizontalBound = horBound;
        this.verticalBound = verBound;
        this.margin = marg;
        this.score = score;
        this.paddleHeight = 15;
        this.countdownDisplayed = false;
        this.environment = new GameEnvironment();
        this.animationRunner = new AnimationRunner(this.gui, 60);
    }

    /**
     * this method adds a given collidable object to game.
     *
     * @param c the given collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * this method adds a given sprite object to the game.
     *
     * @param s the given sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**.
     * none
     */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        Block topMargin = new Block(
                0, 0, this.horizontalBound, this.margin, Color.LIGHT_GRAY, 0);
        Block bottomMargin = new Block(
                this.margin, this.verticalBound,
                this.horizontalBound - 2 * this.margin, this.margin, Color.LIGHT_GRAY, 0);
        Block leftMargin = new Block(
                0, this.margin, this.margin, this.verticalBound - this.margin, Color.LIGHT_GRAY, 0);
        Block rightMargin = new Block(
                this.horizontalBound - this.margin, this.margin, this.margin,
                this.verticalBound - this.margin, Color.LIGHT_GRAY, 0);
        topMargin.addToGame(this);
        bottomMargin.addToGame(this);
        leftMargin.addToGame(this);
        rightMargin.addToGame(this);
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.ballRemover = new BallRemover(this, this.remainingBalls);
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        bottomMargin.addHitListener(this.ballRemover);
        List<Block> blocks = this.level.blocks();
        for (Block b: blocks) {
            b.addHitListener(this.blockRemover);
            b.addHitListener(this.scoreTrackingListener);
            b.addToGame(this);
            this.remainingBlocks.increase(1);
        }
        this.scoreTrackingListener.setInitialBlockCount(this.remainingBlocks.getValue());
        this.sleeper = new Sleeper();
        this.paddle = new Paddle(this.horizontalBound / 2 - level.paddleWidth() / 2,
                this.verticalBound - this.paddleHeight - 10,
                level.paddleWidth(), paddleHeight,
                Color.YELLOW, this.gui, this.margin);
        this.paddle.setBounds(this.horizontalBound, this.verticalBound);
        this.paddle.addToGame(this);
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        level.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
    }

    /**
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (!this.countdownDisplayed) {
            this.animationRunner.run(new ReadySetGoScreen());
            this.countdownDisplayed = true;
        }
        drawOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen();
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, pauseScreen));
        }
        if (remainingBlocks.getValue() == 0) {
            if (isLast) {
                Animation winScreen = new GameOverScreen(true,
                        this.score.getValue());
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard,
                        KeyboardSensor.SPACE_KEY, winScreen));
            }
            this.running = false;
        }
        if (remainingBalls.getValue() == 0) {
            Animation gameOverScreen = new GameOverScreen(false,
                    this.score.getValue());
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, gameOverScreen));
            this.running = false;
        }
    }

    /**
     *
     * @return false
     */
    public boolean shouldStop() {
      return  !this.running;
    }

    /**
     * Create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
        this.paddle.putBallsInTheMiddle();
        List<Velocity> initialBallsVelocity = this.level.initialBallVelocities();
        for (Velocity ballVelocity: initialBallsVelocity) {
            Ball ball = new Ball(this.horizontalBound / 2,
                    this.verticalBound - this.margin - paddleHeight - 10,
                    5,
                    Color.WHITE,
                    this.horizontalBound,
                    this.verticalBound,
                    this.environment);
            ball.setVelocity(ballVelocity);
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }
        this.paddle.addToGame(this);
    }

    /**
     * this method starts the animation loop.
     */
    public void run() {
        this.createBallsOnTopOfPaddle();
        this.running = true;
        this.animationRunner.run(this);
    }

    /**
     * No balls boolean.
     *
     * @return the boolean
     */
    public boolean noBalls() {
        if (remainingBalls.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * No blocks boolean.
     *
     * @return the boolean
     */
    public boolean noBlocks() {
        if (remainingBlocks.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void  removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
