package animation;

import biuoop.DrawSurface;

/**
 * The interface animation.Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d Surface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Reports whether the animation needs to be stopped.
     *
     * @return true if the animation needs to stop, false otherwise
     */
    boolean shouldStop();
}
