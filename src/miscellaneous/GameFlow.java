package miscellaneous;

import biuoop.KeyboardSensor;
import eventhandlers.Counter;
import eventhandlers.LevelNameIndicator;
import eventhandlers.ScoreIndicator;
import levels.LevelInformation;
import levels.GameLevel;
import animation.AnimationRunner;
import menu.Task;
import menu.Menu;
import menu.MenuAnimation;
import animation.HighScoresAnimation;
import menu.QuitTask;
import menu.ShowHiScoresTask;
import menu.StartGameTask;
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
    private HighScoresAnimation highScoresAnimation;
    private HighScores highScore;
    private int margin;
    private ScoreIndicator scoreIndicator;
    private LevelNameIndicator levelNameIndicator;

    /**
     * Instantiates a new Game flow.
     *
     * @param horBound  the hor bound
     * @param vertBound the vert bound
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
        this.highScore = new HighScores();
        this.highScoresAnimation = new HighScoresAnimation(highScore);
    }

    /**
     * Reset score.
     */
    public void resetScore() {
        this.score.decrease(this.score.getValue());
    }

    /**
     * Run menu.
     *
     * @param levels the levels
     */
    public void runMenu(List<LevelInformation> levels) {
        while (true) {
            // Quit task will terminate the program once selected
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                    "Arkanoid", gui.getKeyboardSensor(), this.animationRunner);
            menu.addSelection("s", "Start game", new StartGameTask(this, levels));
            menu.addSelection("h", "High Score", new ShowHiScoresTask(this.animationRunner,
                    this.highScoresAnimation,
                    this.keyboardSensor));
            menu.addSelection("q", "Quit", new QuitTask(gui));
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
        this.highScore.updateHighScore(this.score.getValue());
        this.highScore.save();
        this.resetScore();
    }
}
