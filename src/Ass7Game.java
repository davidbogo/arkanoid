import levels.LevelInformation;
import levels.LevelSpecificationReader;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;


/**
 * The type Ass 7 game.
 */
public class Ass7Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
//
    // * The entry point of application.
    // *
    // * @param args the input arguments
    // *
    public static void main(String[] args) {
        int horizontalBound = 800;
        int verticalBound = 600;
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL resource = null;
        String levelDefFile;
        if (args.length > 0) {
            levelDefFile = args[0];
            resource = classLoader.getResource(levelDefFile);
            if (resource == null) {
                // A bug in build.xml causes a bad parameter to be passed to Ass7Game.main()
                // if the program is run as just "ant run", with no parameters specified by
                // -Dargs="...". In this or any other case when the supplied parameter is wrong,
                // we'll try again with the default file name
                levelDefFile = "level_definitions.txt";
            }
        } else {
            levelDefFile = "level_definitions.txt";
        }
        if (resource == null) {
            resource = classLoader.getResource(levelDefFile);
        }
        if (resource != null) {
            try {
                FileReader fileReader = new FileReader(resource.getPath());
                LevelSpecificationReader levelReader = new LevelSpecificationReader(horizontalBound, verticalBound);
                List<LevelInformation> levels = levelReader.fromReader(fileReader);
                if (levels.size() > 0) {
                    GameFlow game = new GameFlow(horizontalBound, verticalBound);
                    game.runLevels(levels);
                }
            } catch (IOException ioException) {
            }
        }
    }
}