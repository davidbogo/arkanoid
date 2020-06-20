package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import miscellaneous.Sprite;

/**
 * This class represents the background of the first level.
 */
public class Background4 implements Sprite {
    /**
     * this method draws the background on given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#5A78C8"));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }
    /**
     * this method notifies the background that a time unit has passed.
     */
    public void timePassed() {
    }
}
