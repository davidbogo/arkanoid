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
     * @param curLevel      the current level
     * @param guiParam      the GUI param
     * @param theKey        the key
     * @param scoreParam    the score
     * @param marg          the margin
     * @param last          indicator whether this is the last level
     */
    public GameLevel(LevelInformation curLevel,
                     GUI guiParam,
                     KeyboardSensor theKey,
                     Counter scoreParam,
                     int marg,
                     boolean last
                     ) {
        gui = guiParam;
        keyboard = theKey;
        isLast = last;
        level = curLevel;
        horizontalBound = 800;
        verticalBound = 600;
        margin = marg;
        score = scoreParam;
        paddleHeight = 15;
        countdownDisplayed = false;
        environment = new GameEnvironment();
        animationRunner = new AnimationRunner(gui, 60);
    }

    /**
     * this method adds a given collidable object to game.
     *
     * @param c the given collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * this method adds a given sprite object to the game.
     *
     * @param s the given sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**.
     * Initializes the level
     */
    public void initialize() {
        sprites = new SpriteCollection();
        remainingBlocks = new Counter(0);
        remainingBalls = new Counter(0);
        Block topMargin = new Block(
                0, 0, horizontalBound, margin, Color.LIGHT_GRAY, 0);
        Block bottomMargin = new Block(
                margin, verticalBound,
                horizontalBound - 2 * margin, margin, Color.LIGHT_GRAY, 0);
        Block leftMargin = new Block(
                0, margin, margin, verticalBound - margin, Color.LIGHT_GRAY, 0);
        Block rightMargin = new Block(
                horizontalBound - margin, margin, margin,
                verticalBound - margin, Color.LIGHT_GRAY, 0);
        topMargin.addToGame(this);
        bottomMargin.addToGame(this);
        leftMargin.addToGame(this);
        rightMargin.addToGame(this);
        blockRemover = new BlockRemover(this, remainingBlocks);
        ballRemover = new BallRemover(this, remainingBalls);
        scoreTrackingListener = new ScoreTrackingListener(score);
        bottomMargin.addHitListener(ballRemover);
        List<Block> blocks = level.blocks();
        for (Block b: blocks) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            b.addToGame(this);
            remainingBlocks.increase(1);
        }
        scoreTrackingListener.setInitialBlockCount(remainingBlocks.getValue());
        sleeper = new Sleeper();
        paddle = new Paddle(horizontalBound / 2 - level.paddleWidth() / 2,
                verticalBound - paddleHeight - 10,
                level.paddleWidth(), paddleHeight,
                Color.YELLOW, gui, margin);
        paddle.setBounds(horizontalBound, verticalBound);
        paddle.addToGame(this);
    }

    /**
     * Draw on the surface
     *
     * @param d surface to draw on
     */
    public void drawOn(DrawSurface d) {
        level.getBackground().drawOn(d);
        sprites.drawAllOn(d);
    }

    /**
     * Displays one frame
     *
     * @param d     Surface for the frame
     */
    public void doOneFrame(DrawSurface d) {
        if (!countdownDisplayed) {
            animationRunner.run(new ReadySetGoScreen());
            countdownDisplayed = true;
        }
        drawOn(d);
        sprites.notifyAllTimePassed();
        if (keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen();
            animationRunner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, pauseScreen));
        }
        if (remainingBlocks.getValue() == 0) {
            if (isLast) {
                Animation winScreen = new GameOverScreen(true,
                        score.getValue());
                animationRunner.run(new KeyPressStoppableAnimation(keyboard,
                        KeyboardSensor.SPACE_KEY, winScreen));
            }
            running = false;
        }
        if (remainingBalls.getValue() == 0) {
            Animation gameOverScreen = new GameOverScreen(false,
                    score.getValue());
            animationRunner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, gameOverScreen));
            running = false;
        }
    }

    /**
     * Indicates whether the level should be stopped
     * @return true if the level needs to be stopped, false otherwise
     */
    public boolean shouldStop() {
      return  !running;
    }

    /**
     * Create balls on top of the paddle.
     */
    public void createBallsOnTopOfPaddle() {
        paddle.putBallsInTheMiddle();
        List<Velocity> initialBallsVelocity = level.initialBallVelocities();
        for (Velocity ballVelocity: initialBallsVelocity) {
            Ball ball = new Ball(horizontalBound / 2,
                                 verticalBound - margin - paddleHeight - 10,
                                 5,
                                  Color.WHITE,
                                  horizontalBound,
                                  verticalBound,
                                  environment);
            ball.setVelocity(ballVelocity);
            ball.addToGame(this);
            remainingBalls.increase(1);
        }
        paddle.addToGame(this);
    }

    /**
     * this method starts the animation loop.
     */
    public void run() {
        createBallsOnTopOfPaddle();
        running = true;
        animationRunner.run(this);
    }

    /**
     * Indicates whether all balls have been removed
     *
     * @return  true if no balls remain, false otherwise
     */
    public boolean noBalls() {
        if (remainingBalls.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Indicates whether all blocks have been removed
     *
     * @return  true if no blocks remain, false otherwise
     */
    public boolean noBlocks() {
        if (remainingBlocks.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Remove a collidable.
     *
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove a sprite.
     *
     * @param s sprite to remove
     */
    public void  removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
