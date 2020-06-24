package levels;

import gameelements.Block;
import gameelements.SolidBlock;
import geometry.Rectangle;

public class SolidBlockCreator implements BlockCreator {
    private BlockParams blockParams;

    public SolidBlockCreator(BlockParams blkParams) {
        this.blockParams = blkParams;
    }

    @Override
    public Block create(int xPos, int yPos) {
        Rectangle blockRectangle = new Rectangle(xPos, yPos, this.blockParams.width, this.blockParams.height);
        return new SolidBlock(
                blockRectangle,
                this.blockParams.fillColor,
                this.blockParams.stroke,
                this.blockParams.hitPoints);
    }
}
