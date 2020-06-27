package menu;

import animation.Animation;
import animation.AnimationRunner;
import levels.HighScoresTable;

public class ShowHiScoresTask implements Task<Void> {
    private HighScoresTable table;
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}
