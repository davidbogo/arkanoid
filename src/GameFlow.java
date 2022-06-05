import biuoop.KeyboardSensor;
import eventhandlers.Counter;
import eventhandlers.LevelNameIndicator;
import eventhandlers.ScoreIndicator;
import levels.LevelInformation;
import levels.GameLevel;
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
        horizontalBound = 800;
        verticalBound = 600;
        margin = 20;
        gui = new GUI("Breaking Bad", horizontalBound, verticalBound);
        keyboardSensor = gui.getKeyboardSensor();
        score = new Counter(0);
        scoreIndicator = null;
        levelNameIndicator = null;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        int totalLevels = levels.size();
        int curLevel = 0;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(
                    levelInfo,
                    gui,
                    keyboardSensor,
                    score,
                    margin,
                    (curLevel == totalLevels - 1)
                    );
            level.initialize();
            ScoreIndicator si = new ScoreIndicator(score);
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
