package levels;

import biuoop.KeyboardSensor;
import eventhandlers.Counter;
import eventhandlers.LevelNameIndicator;
import eventhandlers.ScoreIndicator;
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
    private int margin;
    private ScoreIndicator scoreIndicator;
    private LevelNameIndicator levelNameIndicator;


    /**
     * Instantiates a new Game flow.
     */
    public GameFlow() {
        this.horizontalBound = 800;
        this.verticalBound = 600;
        this.margin = 20;
        this.gui = new GUI("Breaking Bad", this.horizontalBound, this.verticalBound);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.score = new Counter(0);
        this.scoreIndicator = null;
        this.levelNameIndicator = null;
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
