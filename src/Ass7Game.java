import levels.LevelInformation;
import levels.LevelSpecificationReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

        String levelDefFile = "level_definitions.txt";
        if (args.length > 0) {
            levelDefFile = args[0];
        }
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL resource = classLoader.getResource(levelDefFile);
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