package menu;

import biuoop.GUI;

/**
 * this class represents a menu.ExitTask object.
 */
public class QuitTask implements Task<Void> {
    private GUI gui;

    /**
     * construct an menu.ExitTask from a given GUI object.
     *
     * @param gui the given GUI.
     */
    public QuitTask(GUI gui) {
        this.gui = gui;
    }
    /**
     * this method runs this task.
     * @return unimplemented option.
     */
    public Void run() {
        this.gui.close();
        System.exit(0);
        return null;
    }
}
