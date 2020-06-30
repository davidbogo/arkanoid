package levels;

import gameelements.Block;

/**
 * The interface Block creator.
 */
public interface BlockCreator {
    /**
     * Create block.
     *
     * @param xPos the x pos
     * @param yPos the y pos
     * @return the block
     */
// Create a block at the specified location.
    Block create(int xPos, int yPos);
}
