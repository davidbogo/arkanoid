package levels;

import gameelements.Block;

import java.util.HashMap;
import java.util.Map;

public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<String, Integer>();
        this.blockCreators =  new HashMap<String, BlockCreator>();
    }

    public void registerSpacerSymbol(String spaceSymbol, int width) {
        this.spacerWidths.put(spaceSymbol, width);
    }

    public void registerBlockCreator(String blockSymbol, BlockCreator blockCreator) {
        this.blockCreators.put(blockSymbol, blockCreator);
    }

    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }
}
