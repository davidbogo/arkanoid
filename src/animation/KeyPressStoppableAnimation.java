package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private Animation actualAnimation;
    private boolean stop;
    private String stopKey;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.stopKey = key;
        this.actualAnimation = animation;
        this.stop = false;
    }

    /**
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        this.actualAnimation.doOneFrame(d);
        if (this.keyboard.isPressed(stopKey)) {
            this.stop = true;
        }
    }

    /**
     * @return true when needs to stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
