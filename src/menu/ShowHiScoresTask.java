package menu;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor keyB;

    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor keyboard) {
        this.runner = runner;
        this.keyB = keyboard;
        this.highScoresAnimation = highScoresAnimation;
    }
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.keyB,
                KeyboardSensor.SPACE_KEY, this.highScoresAnimation));
        return null;
    }
}
