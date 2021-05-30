import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * time passed.
     */
    void timePassed();
}
