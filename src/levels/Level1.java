package levels;

import gameelements.Block;
import miscellaneous.Sprite;
import movement.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the first level.
 */
public class Level1 implements LevelInformation {
    private Sprite background;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;

    /**
     * Constructs level 1
     * Creates background and blocks.
     * Initializes balls' speeds.
     */
    public Level1() {
        background = new Background1();
        blocks = new ArrayList<Block>();
        Block block = new Block(385, 150, 30, 30, Color.RED, 1);
        blocks.add(block);
        initialBallVelocities = new ArrayList<Velocity>();
        initialBallVelocities.add(new Velocity(0, -3));
    }

    /**
     * this method returns number of balls in the level.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return initialBallVelocities.size();
    }

    /**
     * this method returns the list of balls' velocities.
     * @return list list of balls' velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return initialBallVelocities;
    }

    /**
     * this method returns the paddle's speed.
     * @return paddle's speed.
     */
    public int paddleSpeed() {
        return 4;
    }

    /**
     * this method returns the paddle's width.
     * @return paddle's width.
     */
    public int paddleWidth() {
        return 75;
    }

    /**
     * this method returns the paddle's height.
     *
     * @return paddle 's height.
     */
    public int paddleHeight() {
        return 15;
    }

    /**
     * this method returns the level's name.
     * @return the level's name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * this method returns the level's background.
     * @return the level's background.
     */
    public Sprite getBackground() {
        return background;
    }

    /**
     * this method returns a list of the level's blocks.
     * @return a list of the level's blocks.
     */
    public List<Block> blocks() {
        return blocks;
    }

    /**
     * this method returns the number of blocks left on the screen.
     * @return number of blocks left on the screen.
     */
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }
}
