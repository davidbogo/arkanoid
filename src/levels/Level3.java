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
public class Level3 implements LevelInformation {
    private Sprite background;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;

    /**
     * construct level 1.
     * creating background and blocks.
     * Initializing balls' speeds.
     */
    public Level3() {
        this.background = new Background3();
        this.blocks = new ArrayList<Block>();
        for (int i = 0; i < 10; ++i) {
            Block block = new Block(730 - i * 50, 100, 50, 20, Color.GRAY, 1);
            this.blocks.add(block);
        }
        for (int i = 0; i < 9; ++i) {
            Block block = new Block(730 - i * 50, 120, 50, 20, Color.RED, 1);
            this.blocks.add(block);
        }
        for (int i = 0; i < 8; ++i) {
            Block block = new Block(730 - i * 50, 140, 50, 20, Color.YELLOW, 1);
            this.blocks.add(block);
        }
        for (int i = 0; i < 7; ++i) {
            Block block = new Block(730 - i * 50, 160, 50, 20, Color.BLUE, 1);
            this.blocks.add(block);
        }
        for (int i = 0; i < 6; ++i) {
            Block block = new Block(730 - i * 50, 180, 50, 20, Color.WHITE, 1);
            this.blocks.add(block);
        }
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.initialBallVelocities.add(new Velocity(3, -3));
        this.initialBallVelocities.add(new Velocity(-3, -3));
    }
    /**
     * this method returns number of balls in level 3.
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
        return 5;
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
        return "Final Four";
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


