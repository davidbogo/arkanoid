package levels;

import gameelements.Block;
import miscellaneous.Sprite;
import movement.Velocity;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls
     *
     * @return number of balls
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return the list of ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Paddle width
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Level name
     *
     * @return level name (the one displayed at the top of the screen)
     */
    String levelName();

    /**
     * Get background.
     *
     * @return the  sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * Blocks list
     *
     * @return the list of blocks that make up the level
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove
     *
     * @return number of blocks that need to be removed
     *   before the level is considered cleared
     */
    int numberOfBlocksToRemove();
}
