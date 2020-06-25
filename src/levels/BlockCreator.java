package levels;

import gameelements.Block;

public interface BlockCreator {
    // Create a block at the specified location.
    Block create(int xPos, int yPos);
}