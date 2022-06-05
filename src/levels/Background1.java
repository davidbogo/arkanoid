package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import miscellaneous.Sprite;

/**
 * This class represents the background of the first level.
 */
public class Background1 implements Sprite {
    /**
     * this method draws the background on given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.drawCircle(400, 165, 50);
        d.drawCircle(400, 165, 85);
        d.drawCircle(400, 165, 125);
        d.drawLine(400, 0, 400, 140);
        d.drawLine(420, 165, 575, 165);
        d.drawLine(400, 190, 400, 330);
        d.drawLine(230, 165, 375, 165);
    }

    /**
     * this method notifies the background that a time unit has passed.
     */
    public void timePassed() {
    }
}
