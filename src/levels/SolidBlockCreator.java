package levels;

import gameelements.Block;
import gameelements.SolidBlock;
import geometry.Rectangle;

/**
 * The type Solid block creator.
 */
public class SolidBlockCreator implements BlockCreator {
    private BlockParams blockParams;

    /**
     * Instantiates a new Solid block creator.
     *
     * @param blkParams the blk params
     */
    public SolidBlockCreator(BlockParams blkParams) {
        this.blockParams = blkParams;
    }

    @Override
    /**
     * Creates a solid block.
     * @xPos the x pos
     * @yPos the y pos
     */
    public Block create(int xPos, int yPos) {
        Rectangle blockRectangle = new Rectangle(xPos, yPos, this.blockParams.getWidth(), this.blockParams.getHeight());
        return new SolidBlock(
                blockRectangle,
                this.blockParams.getFillColor(),
                this.blockParams.getStroke(),
                this.blockParams.getHitPoints());
    }
}
