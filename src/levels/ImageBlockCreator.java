package levels;

import gameelements.Block;
import gameelements.ImageBlock;

/**
 * The type Image block creator.
 */
public class ImageBlockCreator implements BlockCreator {
    private BlockParams blockParams;

    /**
     * Instantiates a new Image block creator.
     *
     * @param blkParams the blk params
     */
    public ImageBlockCreator(BlockParams blkParams) {
        this.blockParams = blkParams;
    }

    @Override
    /**
     * Creates an image block.
     * @xPos the x pos
     * @yPos the y pos
     */
    public Block create(int xPos, int yPos) {
        return new ImageBlock(
                xPos,
                yPos,
                this.blockParams.getWidth(),
                this.blockParams.getHeight(),
                this.blockParams.getFillImage(),
                this.blockParams.getStroke(),
                this.blockParams.getHitPoints());
    }
}
