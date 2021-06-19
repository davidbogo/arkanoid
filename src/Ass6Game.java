import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GameFlow game = new GameFlow();
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation level;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "1":
                    level = new Level1();
                    levels.add(level);
                    break;
                case "2":
                    level = new Level2();
                    levels.add(level);
                    break;
                case "3":
                    level = new Level3();
                    levels.add(level);
                    break;
                case "4":
                    level = new Level4();
                    levels.add(level);
                    break;
                default:
                    break;
            }
        }
        game.runLevels(levels);
    }
}
