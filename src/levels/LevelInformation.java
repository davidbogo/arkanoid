package levels;

import gameelements.Block;
import miscellaneous.Sprite;
import movement.Velocity;

import java.util.List;

/**
 * The class Level information.
 */
public class LevelInformation {
    String name;
    List<Block> blocks;
    int numberOfBlocksToRemove;
    List<Velocity> ballVelocities;
    Sprite background;
    int paddleSpeed;
    int paddleWidth;

    public LevelInformation(
            String levelName,
            List<Block> levelBlocks,
            int num_blocks,
            List<Velocity> ballVel,
            int paddle_speed,
            int paddle_width,
            Sprite bkgnd) {
        this.name = levelName;
        this.blocks = levelBlocks;
        this.numberOfBlocksToRemove = num_blocks;
        this.background = bkgnd;
        this.ballVelocities = ballVel;
        this.paddleSpeed = paddle_speed;
        this.paddleWidth = paddle_width;
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

    public void resetBlocks() {
        for (Block b : this.blocks) {
            b.reset();
        }
    }
}
