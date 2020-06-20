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
     * construct level 1.
     * creating background and blocks.
     * Initializing balls' speeds.
     */
    public Level1() {
        this.background = new Background1();
        this.blocks = new ArrayList<Block>();
        Block block = new Block(385, 150, 30, 30, Color.RED, 1);
        this.blocks.add(block);
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.initialBallVelocities.add(new Velocity(0, -3));
    }
    /**
     * this method returns number of balls in level 1.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.initialBallVelocities.size();
    }
    /**
     * this method returns a list of balls' velocities.
     * @return list list of balls' velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
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
     * this method returns a string of the level's name.
     * @return string of the level's name.
     */
    public String levelName() {
        return "Direct Hit";
    }
    /**
     * this method returns the level's background.
     * @return the level's background.
     */
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * this method returns a list of the level's blocks.
     * @return a list of the level's blocks.
     */
    public List<Block> blocks() {
        return this.blocks;
    }
    /**
     * this method returns the number of blocks left on the screen.
     * @return number of blocks left on the screen.
     */
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }
}
