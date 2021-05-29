package levels;

import gameelements.Block;
import miscellaneous.Sprite;
import movement.Velocity;

import java.util.List;

/**
 * The class Level information.
 */
public class LevelInformation {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Blocks.
     */
    private List<Block> blocks;
    /**
     * The Number of blocks to remove.
     */
    private  int numberOfBlocksToRemove;
    /**
     * The Ball velocities.
     */
    private List<Velocity> ballVelocities;
    /**
     * The Background.
     */
    private Sprite background;
    /**
     * The Paddle speed.
     */
    private int paddleSpeed;
    /**
     * The Paddle width.
     */
    private int paddleWidth;

    /**
     * Instantiates a new Level information.
     *
     * @param levelName    the level name
     * @param levelBlocks  the level blocks
     * @param numBlk       the num blocks
     * @param ballVel      the ball vel
     * @param paddleSp     the paddle speed
     * @param paddleWd     the paddle width
     * @param bkgnd        the bkgnd
     */
    public LevelInformation(
            String levelName,
            List<Block> levelBlocks,
            int numBlk,
            List<Velocity> ballVel,
            int paddleSp,
            int paddleWd,
            Sprite bkgnd) {
        this.name = levelName;
        this.blocks = levelBlocks;
        this.numberOfBlocksToRemove = numBlk;
        this.background = bkgnd;
        this.ballVelocities = ballVel;
        this.paddleSpeed = paddleSp;
        this.paddleWidth = paddleWd;
    }

    /**
     * Number of balls int.
     *
     * @return the int
     */
    public int numberOfBalls() {
        return this.ballVelocities.size();
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
// The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
// the level name will be displayed at the top of the screen.
    public String levelName() {
        return this.name;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
// Returns a sprite with the background of the level
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
// The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
// Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * Reset blocks.
     */
    public void resetBlocks() {
        for (Block b : this.blocks) {
            b.reset();
        }
    }
}
