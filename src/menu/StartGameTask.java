package menu;

import levels.GameFlow;

import java.util.List;

import levels.LevelInformation;

/**
 * this class represents a menu.StartGameTask object.
 */
public class StartGameTask implements Task<Void> {
    private List<LevelInformation> levels;
    /**
     * construct a menu.StartGameTask from a GUI, an animationRunner,
     * a highScoresTable, a levelInformation list,
     */
    public StartGameTask(List<LevelInformation> level) {
        this.levels = level;
    }
    /**
     * this method runs this task.
     * @return unimplemented option.
     */
    public Void run() {
        GameFlow game = new GameFlow();
        game.runLevels(this.levels);
     /**   try {
            table.save(this.highScoresFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
      **/
        return null;
    }
}

