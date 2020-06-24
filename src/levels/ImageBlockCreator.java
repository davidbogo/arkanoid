package levels;

import gameelements.Block;
import gameelements.ImageBlock;

public class ImageBlockCreator implements BlockCreator {
    private BlockParams blockParams;

    public ImageBlockCreator(BlockParams blkParams) {
        this.blockParams = blkParams;
    }

    @Override
    public Block create(int xPos, int yPos) {
        return new ImageBlock(
                xPos,
                yPos,
                this.blockParams.width,
                this.blockParams.height,
                this.blockParams.fillImage,
                this.blockParams.stroke,
                this.blockParams.hitPoints);
    }
}
