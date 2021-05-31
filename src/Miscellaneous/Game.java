package miscellaneous;

import java.awt.Color;

import eventhandlers.BallRemover;
import eventhandlers.Counter;
import eventhandlers.ScoreTrackingListener;
import eventhandlers.BlockRemover;
import eventhandlers.ScoreIndicator;
import gameelements.Ball;
import gameelements.Block;
import gameelements.Paddle;
import movement.Collidable;
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
     * this method constructs a game object.
     *
     * @param scoreParam        the score
     * @param horBound          the horizontal bound
     * @param verBound          the vertical bound
     */
    public Game(Counter scoreParam,
                int horBound,
                int verBound) {
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        horizontalBound = horBound;
        verticalBound = verBound;
        margin = 0;
        score = scoreParam;
        backgroundColor = Color.BLUE;
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

    /**
     * this method set the game background according to a given color.
     *
     * @param d     the DrawSurface of this game.
     * @param color the given color.
     */
    public void setBackground(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle(0, 0, gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());
    }

    /**
     * this method initializes the game - creates Blocks, Balls and GameElements.Paddle.
     *
     * @param horBound          the available width.
     * @param verBound          the available height.
     * @param marg              the size of the margin blocks.
     * @param paddleWidth       the paddle width.
     * @param paddleHeight      the paddle height.
     */
    public void initialize(int horBound, int verBound, int marg,
                           int paddleWidth, int paddleHeight) {
        blocks = new ArrayList<Block>();
        remainingBlocks = new Counter(0);
        remainingBalls = new Counter(0);
        margin = marg;
        Block topMargin = new Block(
                0, 0, horBound, marg, Color.LIGHT_GRAY, 0);
        Block bottomMargin = new Block(
                marg, verBound - marg,
                horBound - 2 * marg, marg, Color.LIGHT_GRAY, 0);
        Block leftMargin = new Block(
                0, marg, marg, verBound - marg, Color.LIGHT_GRAY, 0);
        Block rightMargin = new Block(
                horBound - marg, marg, marg,
                verBound - marg, Color.LIGHT_GRAY, 0);
        Block newBlock;
        for (int i = 0; i < 12; ++i) {
            newBlock = new Block(730 - i * 50, 100, 50, 20, Color.GRAY, 1);
            blocks.add(newBlock);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 11; ++i) {
            newBlock = new Block(730 - i * 50, 120, 50, 20, Color.RED, 1);
            blocks.add(newBlock);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 10; ++i) {
            newBlock = new Block(730 - i * 50, 140, 50, 20, Color.YELLOW, 1);
            blocks.add(newBlock);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 9; ++i) {
            newBlock = new Block(730 - i * 50, 160, 50, 20, Color.ORANGE, 1);
            blocks.add(newBlock);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 8; ++i) {
            newBlock = new Block(730 - i * 50, 180, 50, 20, Color.PINK, 1);
            blocks.add(newBlock);
            remainingBlocks.increase(1);
        }
        for (int i = 0; i < 7; ++i) {
            newBlock = new Block(730 - i * 50, 200, 50, 20, Color.GREEN, 1);
            blocks.add(newBlock);
            remainingBlocks.increase(1);
        }
        Ball ball1 = new Ball(horBound / 2, verBound
                - marg - paddleHeight - 1,
                5, Color.WHITE, environment);
        remainingBalls.increase(1);
        Ball ball2 = new Ball(horBound / 2, verBound
                - marg - paddleHeight - 1,
                5, Color.RED, environment);
        remainingBalls.increase(1);
        Ball ball3 = new Ball(horBound / 2, verBound
                - marg - paddleHeight - 1,
                5, Color.GREEN, environment);
        remainingBalls.increase(1);
        ball1.setVelocity(1, -1);
        ball2.setVelocity(-1, -1);
        ball3.setVelocity(0, -1);
        topMargin.addToGame(this);
        bottomMargin.addToGame(this);
        leftMargin.addToGame(this);
        rightMargin.addToGame(this);
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
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

        gui = new GUI("Breaking Bad", horBound, verBound);
        sleeper = new Sleeper();
        Paddle paddle = new Paddle(horBound / 2 - paddleWidth / 2,
                verBound - marg - paddleHeight,
                paddleWidth, paddleHeight,
                Color.YELLOW, gui, marg);
        paddle.addToGame(this);
    }

    /**
     * this method starts the animation loop.
     */
    public void run() {
        DrawSurface d;
        while (true) {
            long startTime = System.currentTimeMillis();
            d = gui.getDrawSurface();
            setBackground(d, Color.BLUE);
            sprites.drawAllOn(d);
            gui.show(d);
            sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = 5 - usedTime;
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
        d.fillRectangle(margin,255,horizontalBound - 2 * margin,100);
        d.setColor(Color.BLACK);
        d.drawText(215, 315, "Game Over. Press ENTER", 30);
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
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
