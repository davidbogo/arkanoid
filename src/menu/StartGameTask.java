package menu;

import java.util.List;

import levels.LevelInformation;
import miscellaneous.GameFlow;

/**
 * this class represents a menu.StartGameTask object.
 */
public class StartGameTask implements Task<Void> {
    private GameFlow gameFlow;
    private List<LevelInformation> levels;
    /**
     * construct a menu.StartGameTask from a GUI, an animationRunner,
     * a highScoresTable, a levelInformation list,
     */
    public StartGameTask(GameFlow game, List<LevelInformation> level) {
        this.gameFlow = game;
        this.levels = level;
    }
    /**
     * this method runs this task.
     * @return unimplemented option.
     */
    public Void run() {
        gameFlow.runLevels(this.levels);
        return null;
    }
}

