package levels;

import gameelements.Block;

import java.util.Map;

public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
    }

    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
    }

    // Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s){
    }

    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }
}
