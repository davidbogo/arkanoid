package levels;

import gameelements.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<String, Integer>();
        this.blockCreators =  new HashMap<String, BlockCreator>();
    }

    /**
     * Register spacer symbol.
     *
     * @param spaceSymbol the space symbol
     * @param width       the width
     */
    public void registerSpacerSymbol(String spaceSymbol, int width) {
        this.spacerWidths.put(spaceSymbol, width);
    }

    /**
     * Register block creator.
     *
     * @param blockSymbol  the block symbol
     * @param blockCreator the block creator
     */
    public void registerBlockCreator(String blockSymbol, BlockCreator blockCreator) {
        this.blockCreators.put(blockSymbol, blockCreator);
    }

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * Gets space width.
     *
     * @param s the s
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Gets block.
     *
     * @param s the s
     * @param x the x
     * @param y the y
     * @return the block
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }
}
