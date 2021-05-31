package miscellaneous;

import biuoop.DrawSurface;


/**
 * The interface Miscellaneous.Sprite.
 */
public interface Sprite {

    /**
     * Draw on.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
    void timePassed();
}