package miscellaneous;

import java.awt.Color;

import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import eventhandlers.BallRemover;
import eventhandlers.Counter;
import eventhandlers.ScoreTrackingListener;
import eventhandlers.BlockRemover;
import eventhandlers.ScoreIndicator;
import gameelements.Ball;
import gameelements.Block;
import gameelements.Paddle;
import movement.Collidable;
import movement.Velocity;
import miscellaneous.Sprite;
import miscellaneous.SpriteCollection;
import miscellaneous.GameEnvironment;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Miscellaneous.Game.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private GUI gui;
    private Sleeper sleeper;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private List<Block> blocks;
    private Counter score;
    private Color backgroundColor;
    private int horizontalBound;
    private int verticalBound;
    private int margin;

    /**
     * Instantiates a new Game.
     */
    public Game(Counter scr,
                int horBound,
                int verBound) {
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        horizontalBound = horBound;
        verticalBound = verBound;
        margin = 0;
        score = scr;
        backgroundColor = Color.BLUE;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * this method set the game background according to a given color.
     *
     * @param d     the DrawSurface of this game.
     * @param color the given color.
     */
    public void setBackground(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle(0, 0, gui.getDrawSurface().getWidth(),
                        gui.getDrawSurface().getHeight());
    }
     /* Initialize.
     *
     * @param horizontalBound the horizontal bound
     * @param verticalBound   the vertical bound
     * @param margin          the margin
     * @param paddleWidth     the paddle width
     * @param paddleHeight    the paddle height
     */
    public void initialize(double horizontalBound, double verticalBound, int margin,
                           int paddleWidth, int paddleHeight) {
        blocks = new ArrayList<Block>();
        remainingBlocks = new Counter(0);
        remainingBalls = new Counter(0);
        int iHorizontalBound = (int) horizontalBound;
        int iVerticalBound = (int) verticalBound;
        Block topMargin = new Block(0, 0, horizontalBound, 20, Color.GRAY, 1);
        Block bottomMargin = new Block(
                20, verticalBound - 20, horizontalBound - 2 * 20, 20, Color.GRAY, 1);
        Block leftMargin = new Block(
                0, 20, 20, verticalBound - 20, Color.GRAY, 1);
        Block rightMargin = new Block(
                horizontalBound - 20, 20, 20, verticalBound - 20, Color.GRAY, 1);
        topMargin.addToGame(this);
        bottomMargin.addToGame(this);
        leftMargin.addToGame(this);
        rightMargin.addToGame(this);
        Ball ball1 = new Ball(horizontalBound / 2, verticalBound - 26, 5,
                Color.WHITE, iHorizontalBound, iVerticalBound, environment);
        Ball ball2 = new Ball(horizontalBound / 2, verticalBound - 26, 5,
                Color.WHITE, iHorizontalBound, iVerticalBound, environment);
        ball1.setVelocity(new Velocity(1, -1));
        ball2.setVelocity(new Velocity(-1, -1));
        ball1.addToGame(this);
        remainingBalls.increase(1);
        ball2.addToGame(this);
        remainingBalls.increase(1);
        Block newBlock;
        Point upperLeftBlockPoint;
        for (int i = 0; i < 12; ++i) {
            upperLeftBlockPoint = new Point((730 - i * 50), 100);
            newBlock = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.GRAY, 1);
            newBlock.addToGame(this);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 11; ++i) {
            upperLeftBlockPoint = new Point((730 - i * 50), 120);
            newBlock = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.RED, 1);
            newBlock.addToGame(this);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 10; ++i) {
            upperLeftBlockPoint = new Point((730 - i * 50), 140);
            newBlock = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.YELLOW, 1);
            newBlock.addToGame(this);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 9; ++i) {
            upperLeftBlockPoint = new Point((730 - i * 50), 160);
            newBlock = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.BLUE, 1);
            newBlock.addToGame(this);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 8; ++i) {
            upperLeftBlockPoint = new Point((730 - i * 50), 180);
            newBlock = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.PINK, 1);
            newBlock.addToGame(this);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 7; ++i) {
            upperLeftBlockPoint = new Point((730 - i * 50), 200);
            newBlock = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.GREEN, 1);
            newBlock.addToGame(this);
            remainingBlocks.increase(1);
        }

        blockRemover = new BlockRemover(this, remainingBlocks);
        ballRemover = new BallRemover(this, remainingBalls);
        scoreTrackingListener = new ScoreTrackingListener(score, remainingBlocks.getValue());
        scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
        bottomMargin.addHitListener(ballRemover);
        for (Block b: blocks) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            b.addToGame(this);
        }

        gui = new GUI("Arkanoid", iHorizontalBound, iVerticalBound);
        sleeper = new Sleeper();
        Paddle paddle = new Paddle(horizontalBound / 2 - paddleWidth / 2,
                verticalBound - margin - paddleHeight,
                paddleWidth, paddleHeight,
                Color.YELLOW, gui, margin);
        paddle.addToGame(this);
    }

    /**
     * run to run it up.
      */
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
		DrawSurface d;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            d = gui.getDrawSurface();
            setBackground(d, Color.BLUE);
            sprites.drawAllOn(d);
            gui.show(d);
            sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (remainingBalls.getValue() == 0 || remainingBlocks.getValue() == 0) {
                break;
            }
        }
        d = gui.getDrawSurface();
        setBackground(d, Color.BLUE);
        sprites.drawAllOn(d);
        d.setColor(Color.GRAY);
        d.fillRectangle(margin,
                        255,
                        horizontalBound - 2 * margin,
                        100);
        d.setColor(Color.BLACK);
        d.drawText(215, 310, "Game Over. Press ENTER", 30);
        gui.show(d);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        while (!keyboard.isPressed(KeyboardSensor.ENTER_KEY)) {
            int notGoingAnywhere = 1;
        }
        gui.close();
        return;
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void  removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
