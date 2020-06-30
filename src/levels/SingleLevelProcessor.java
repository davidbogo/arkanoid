package levels;

import auxiliary.ColorsParser;
import gameelements.Block;
import miscellaneous.Sprite;
import movement.Velocity;

import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Single level processor.
 */
public class SingleLevelProcessor {
    private int horizontalBound;
    private int verticalBound;
    private List<String> lines;
    private List<String> blockLines;
    /**
     * The Blocks factory.
     */
    BlocksFromSymbolsFactory blocksFactory;

    /**
     * Instantiates a new Single level processor.
     *
     * @param horBound  the hor bound
     * @param vertBound the vert bound
     */
    public SingleLevelProcessor(int horBound, int vertBound) {
        this.horizontalBound = horBound;
        this.verticalBound = vertBound;
        this.lines = new ArrayList<String>();
        this.blockLines = new ArrayList<String>();
        this.blocksFactory = null;
    }

    /**
     * Add line.
     *
     * @param line the line
     */
    public void addLine(String line) {
        lines.add(line);
    }

    /**
     * Uses block factories to create all blocks of the level
     * @param blocksStartX
     * @param blocksStartY
     * @param rowHeight
     * @return
     */
    private List<Block> createBlocks(int blocksStartX, int blocksStartY, int rowHeight) {
        List<Block> blocks = new ArrayList<Block>();
        int curY = blocksStartY;
        for (String line : this.blockLines) {
            int curX = blocksStartX;
            for (int i = 0; i < line.length(); i++) {
                String curSymbol = line.substring(i, i + 1);
                if (this.blocksFactory.isSpaceSymbol(curSymbol)) {
                    curX += this.blocksFactory.getSpaceWidth(curSymbol);
                } else if (this.blocksFactory.isBlockSymbol(curSymbol)) {
                    Block block = this.blocksFactory.getBlock(curSymbol, curX, curY);
                    blocks.add(block);
                    curX += block.getRectangle().getWidth();
                }
            }
            curY += rowHeight;
        }
        return blocks;
    }

    /**
     * Runs through the lines defining the level, initiates the reading of
     * block definitions, creates the background and finally calls
     * createBlocks() to build all blocks of the level
     *
     * @return the level information
     * @throws IOException the io exception
     */
    public LevelInformation buildLevelInfo() throws java.io.IOException {
        LevelInformation levelInfo = null;
        String levelName = null;
        ColorsParser colorParser = new ColorsParser();
        List<Velocity> ball_velocities = new ArrayList<Velocity>();
        int paddle_speed = -1;
        int paddle_width = -1;
        int blocks_start_x = -1;
        int blocks_start_y = -1;
        int row_height = -1;
        int num_blocks = -1;
        Sprite background = null;
        boolean readingBlockLines = false;
        for (String line : this.lines) {
            if (!readingBlockLines) {
                Pattern pattern = Pattern.compile(":");
                String[] split;
                if (line.contains("START_BLOCKS")) {
                    readingBlockLines = true;
                } else if (line.contains("level_name")) {
                    split = pattern.split(line);
                    levelName = split[1];
                } else if (line.contains("ball_velocities")) {
                    split = pattern.split(line);
                    Pattern spaceSeparatorPattern = Pattern.compile(" ");
                    String[] spaceSeparatedVelocities = spaceSeparatorPattern.split(split[1]);
                    for (int i = 0; i < spaceSeparatedVelocities.length; i++) {
                        Pattern comaSeparatorPattern = Pattern.compile(",");
                        String[] comaSepearatedVelocityParams = comaSeparatorPattern.split(spaceSeparatedVelocities[i]);
                        if (comaSepearatedVelocityParams.length == 2) {
                            Velocity newVelocity = new Velocity(
                                    (double) Integer.parseInt(comaSepearatedVelocityParams[0]),
                                    (double) Integer.parseInt(comaSepearatedVelocityParams[1]));
                            ball_velocities.add(newVelocity);
                        }
                    }
                } else if (line.contains("paddle_speed")) {
                    split = pattern.split(line);
                    paddle_speed = Integer.parseInt(split[1]);
                } else if (line.contains("paddle_width")) {
                    split = pattern.split(line);
                    paddle_width = Integer.parseInt(split[1]);
                } else if (line.contains("blocks_start_x")) {
                    split = pattern.split(line);
                    blocks_start_x = Integer.parseInt(split[1]);
                } else if (line.contains("blocks_start_y")) {
                    split = pattern.split(line);
                    blocks_start_y = Integer.parseInt(split[1]);
                } else if (line.contains("row_height")) {
                    split = pattern.split(line);
                    row_height = Integer.parseInt(split[1]);
                } else if (line.contains("num_blocks")) {
                    split = pattern.split(line);
                    num_blocks = Integer.parseInt(split[1]);
                } else if (line.contains("background")) {
                    split = pattern.split(line);
                    if (split[1].contains("image")) {
                        Pattern regex = Pattern.compile("\\((.*?)\\)");
                        Matcher regexMatcher = regex.matcher(split[1]);
                        if (regexMatcher.find()) {
                            background = new ImageBackground(regexMatcher.group(1));
                        }
                    } else if (split[1].contains("color")) {
                        background = new SolidBackground(
                                this.horizontalBound,
                                this.verticalBound,
                                colorParser.colorFromString(split[1]));
                    }
                } else if (line.contains("block_definitions")) {
                    split = pattern.split(line);
                    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
                    URL resource = classLoader.getResource(split[1]);
                    if (resource != null) {
                        FileReader fileReader = new FileReader(resource.getPath());
                        this.blocksFactory = BlocksDefinitionReader.fromReader(fileReader);
                    }
                }
            } else {
                if (line.contains("END_BLOCKS")) {
                    readingBlockLines = false;
                    // If ignoring values after END_BLOCKS is made a hard requirement rather than an allowed
                    // assumption, we can do break here
                } else {
                    blockLines.add(line);
                }
            }
        }
        if ((levelName != null) &&
                (this.blocksFactory != null) &&
                (background != null) &&
                (ball_velocities.size() > 0) &&
                (paddle_speed > 0) &&
                (paddle_width > 0) &&
                (blocks_start_x >= 0) &&
                (blocks_start_y >= 0) &&
                (row_height > 0) &&
                (num_blocks > 0)) {
            List<Block> blocks = createBlocks(blocks_start_x, blocks_start_y, row_height);
            if (blocks != null) {
                levelInfo = new LevelInformation(
                        levelName,
                        blocks,
                        num_blocks,
                        ball_velocities,
                        paddle_speed,
                        paddle_width,
                        background);
            }
        }

        return levelInfo;
    }
}
