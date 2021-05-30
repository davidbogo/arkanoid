import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Game.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
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
     * Initialize.
     *
     * @param horizontalBound the horizontal bound
     * @param verticalBound   the vertical bound
     * @param margin          the margin
     * @param paddleWidth     the paddle width
     * @param paddleHeight    the paddle height
     */
    public void initialize(double horizontalBound, double verticalBound, int margin,
                           int paddleWidth, int paddleHeight) {
        int iHorizontalBound = (int) horizontalBound;
        int iVerticalBound = (int) verticalBound;
        Block topMargin = new Block(0, 0, horizontalBound, 20);
        Block bottomMargin = new Block(
                20, verticalBound - 20, horizontalBound - 2 * 20, 20);
        Block leftMargin = new Block(
                0, 20, 20, verticalBound - 20);
        Block rightMargin = new Block(
                horizontalBound - 20, 20, 20, verticalBound - 20);
        topMargin.addToGame(this);
        bottomMargin.addToGame(this);
        leftMargin.addToGame(this);
        rightMargin.addToGame(this);
        Ball ball1 = new Ball(horizontalBound / 2, verticalBound - 26, 5,
                Color.WHITE, iHorizontalBound, iVerticalBound, environment);
        Ball ball2 = new Ball(horizontalBound / 2, verticalBound - 26, 5,
                Color.WHITE, iHorizontalBound, iVerticalBound, environment);
        ball1.setVelocity(1, -1, 800, 600, 0, 0, environment);
        ball2.setVelocity(-1, -1, 800, 600, 0, 0, environment);
        ball1.addToGame(this);
        ball2.addToGame(this);
        Block[] row1 = new Block[12];
        for (int i = 0; i < 12; ++i) {
            Point upperLeftBlockPoint = new Point((730 - i * 50), 100);
            row1[i] = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.GRAY);
            row1[i].addToGame(this);
        }
        Block[] row2 = new Block[11];
        for (int i = 0; i < 11; ++i) {
            Point upperLeftBlockPoint = new Point((730 - i * 50), 120);
            row2[i] = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.RED);
            row2[i].addToGame(this);
        }
        Block[] row3 = new Block[10];
        for (int i = 0; i < 10; ++i) {
            Point upperLeftBlockPoint = new Point((730 - i * 50), 140);
            row3[i] = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.YELLOW);
            row3[i].addToGame(this);
        }
        Block[] row4 = new Block[9];
        for (int i = 0; i < 9; ++i) {
            Point upperLeftBlockPoint = new Point((730 - i * 50), 160);
            row4[i] = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.BLUE);
            row4[i].addToGame(this);
        }
        Block[] row5 = new Block[8];
        for (int i = 0; i < 8; ++i) {
            Point upperLeftBlockPoint = new Point((730 - i * 50), 180);
            row5[i] = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.PINK);
            row5[i].addToGame(this);
        }
        Block[] row6 = new Block[7];
        for (int i = 0; i < 7; ++i) {
            Point upperLeftBlockPoint = new Point((730 - i * 50), 200);
            row6[i] = new Block(new Rectangle(upperLeftBlockPoint, 50, 20), Color.GREEN);
            row6[i].addToGame(this);
        }
            gui = new GUI("Arkanoid", iHorizontalBound, iVerticalBound);
            sleeper = new Sleeper();
       Paddle paddle = new Paddle(horizontalBound / 2 - paddleWidth / 2,
                verticalBound - margin - paddleHeight,
                paddleWidth, paddleHeight,
                Color.YELLOW, this.gui, margin);
        paddle.addToGame(this);

    }
    /**
     * run to run it up.
      */
    public void run() {
        //...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.DARK_GRAY);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

}
