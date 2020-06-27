
import java.util.ArrayList;
import java.util.List;

import levels.*;
import menu.ExitTask;
import menu.Menu;
import menu.MenuAnimation;
import menu.ShowHiScoresTask;
import menu.StartGameTask;
import menu.Task;
import animation.AnimationRunner;
import biuoop.GUI;

/**
 * this class is the main class.
 */
public class Ass7Game {
    /**
     * this is the main method.
     * @param args this array stores the user's input.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
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
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                "Arkanoid", gui.getKeyboardSensor(), animationRunner);
        menu.addSelection("s", "start game", new StartGameTask(levels));
        menu.addSelection("h", "Hi Score", new ShowHiScoresTask(
                animationRunner, menu));
        menu.addSelection("e", "Exit", new ExitTask(gui));
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}