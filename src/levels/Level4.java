package levels;

import gameelements.Block;
import miscellaneous.Sprite;
import movement.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the fourth level.
 */
public class Level4 implements LevelInformation {
    private Sprite background;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;

    /**
     * Constructs level 4
     * Creates background and blocks.
     * Initializes balls' speeds.
     */
    public Level4() {
        background = new Background4();
        blocks = new ArrayList<Block>();
        for (int i = 0; i < 15; ++i) {
            Block block = new Block(725 - i * 50, 50, 50, 20, Color.GRAY, 1);
            blocks.add(block);
        }
        for (int i = 0; i < 15; ++i) {
            Block block = new Block(725 - i * 50, 70, 50, 20, Color.RED, 1);
            blocks.add(block);
        }
        for (int i = 0; i < 15; ++i) {
            Block block = new Block(725 - i * 50, 90, 50, 20, Color.YELLOW, 1);
            blocks.add(block);
        }
        for (int i = 0; i < 15; ++i) {
            Block block = new Block(725 - i * 50, 110, 50, 20, Color.GREEN, 1);
            blocks.add(block);
        }
        for (int i = 0; i < 15; ++i) {
            Block block = new Block(725 - i * 50, 130, 50, 20, Color.WHITE, 1);
            blocks.add(block);
        }
        for (int i = 0; i < 15; ++i) {
            Block block = new Block(725 - i * 50, 150, 50, 20, Color.PINK, 1);
            blocks.add(block);
        }
        for (int i = 0; i < 15; ++i) {
            Block block = new Block(725 - i * 50, 170, 50, 20, Color.CYAN, 1);
            blocks.add(block);
        }
        initialBallVelocities = new ArrayList<Velocity>();
        initialBallVelocities.add(new Velocity(0, -3));
        initialBallVelocities.add(new Velocity(3, -3));
        initialBallVelocities.add(new Velocity(-3, -3));
    }

    /**
     * this method returns number of balls in the level
     * @return number of balls.
     */
    public int numberOfBalls() {
        return initialBallVelocities.size();
    }

    /**
     * this method returns a list of balls' velocities.
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
     * this method returns the level's name.
     * @return the level's name.
     */
    public String levelName() {
        return "Final Four";
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
