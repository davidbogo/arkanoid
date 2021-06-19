package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import miscellaneous.Sprite;

/**
 * This class represents the background of the first level.
 */
public class Background2 implements Sprite {
    /**
     * this method draws the background on given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.decode("#fff08b"));
        for (int i = 0; i < 100; i++) {
            d.drawLine(150, 100, 8 * i, 200);
        }
        d.fillCircle(150, 100, 60);
        d.setColor(Color.decode("#ffe42f"));
        d.fillCircle(150, 100, 50);
        d.setColor(Color.YELLOW);
        d.fillCircle(150, 100, 40);
    }

    /**
     * this method notifies the background that a time unit has passed.
     */
    public void timePassed() {
    }
}
