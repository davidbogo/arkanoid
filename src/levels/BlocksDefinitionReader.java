package levels;

import java.io.BufferedReader;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {

    /**
     * Create blocks factory from block definitions file
     * @param reader the reader
     * @return the blocks factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws java.io.IOException {
        BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory();
        BufferedReader bufferedReader = new BufferedReader(reader);
        BlockParams defaultParams = new BlockParams();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.startsWith("#")) {
                if (line.startsWith("default")) {
                    defaultParams.parseCommaSeparatedParams(line);
                } else if (line.startsWith("bdef")) {
                    BlockParams blockParams = new BlockParams();
                    blockParams.parseCommaSeparatedParams(line);
                    if (blockParams.completeAndValidate(defaultParams)) {
                        BlockCreator blockCreator;
                        if (blockParams.fillImage != null) {
                            blockCreator = new ImageBlockCreator(blockParams);
                        } else {
                            blockCreator = new SolidBlockCreator(blockParams);
                        }
                        factory.registerBlockCreator(blockParams.symbol, blockCreator);
                    }
                } else if (line.startsWith("sdef")) {
                    SpacerParams spacerParams = new SpacerParams();
                    spacerParams.parseCommaSeparatedParams(line);
                    if ((spacerParams.symbol != null) && (spacerParams.width > 0)) {
                        factory.registerSpacerSymbol(spacerParams.symbol, spacerParams.width);
                    }
                }
            }
        }
        return factory;
    }
}
