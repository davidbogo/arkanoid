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
        keyboard = sensor;
        stopKey = key;
        actualAnimation = animation;
        stop = false;
    }

    /**
     * @param d Surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        actualAnimation.doOneFrame(d);
        if (keyboard.isPressed(stopKey)) {
            stop = true;
        }
    }

    /**
     * @return true when needs to stop
     */
    public boolean shouldStop() {
        return stop;
    }
}
