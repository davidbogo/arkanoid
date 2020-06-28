package miscellaneous;

import biuoop.KeyboardSensor;
import eventhandlers.Counter;
import eventhandlers.LevelNameIndicator;
import eventhandlers.ScoreIndicator;
import levels.LevelInformation;
import levels.GameLevel;
import menu.ExitTask;
import menu.Menu;
import menu.MenuAnimation;
import menu.ShowHiScoresTask;
import menu.StartGameTask;
import menu.Task;
import animation.AnimationRunner;
import biuoop.GUI;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private int horizontalBound;
    private int verticalBound;
    private Counter score;
    private GUI gui;
    private AnimationRunner animationRunner;
    private int margin;
    private ScoreIndicator scoreIndicator;
    private LevelNameIndicator levelNameIndicator;

    /**
     * Instantiates a new Game flow.
     */
    public GameFlow(int horBound, int vertBound) {
        this.horizontalBound = horBound;
        this.verticalBound = vertBound;
        this.margin = 20;
        this.gui = new GUI("Arkanoid", this.horizontalBound, this.verticalBound);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.score = new Counter(0);
        this.scoreIndicator = null;
        this.levelNameIndicator = null;
        this.animationRunner = new AnimationRunner(this.gui, 60);
    }

	public void runMenu(List<LevelInformation> levels) {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                "Arkanoid", gui.getKeyboardSensor(), this.animationRunner);
        menu.addSelection("s", "start game", new StartGameTask(this, levels));
        menu.addSelection("h", "Hi Score", new ShowHiScoresTask(
                animationRunner, menu));
        menu.addSelection("q", "Quit", new ExitTask(gui));
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
	}

	/**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        // ...
        int totalLevels = levels.size();
        int curLevel = 0;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(
                    levelInfo,
                    this.gui,
                    this.animationRunner,
                    this.keyboardSensor,
                    this.score,
                    this.horizontalBound,
                    this.verticalBound,
                    this.margin,
                    (curLevel == totalLevels - 1)
                    );
            level.initialize();
            ScoreIndicator si = new ScoreIndicator(this.score);
            LevelNameIndicator lni = new LevelNameIndicator(levelInfo.levelName());
            level.addSprite(si);
            level.addSprite(lni);
            level.run();
            if (level.noBalls()) {
                break;
            }
            curLevel++;
        }
        gui.close();
    }
}
