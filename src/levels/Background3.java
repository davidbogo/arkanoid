package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import miscellaneous.Sprite;

/**
 * This class represents the background of the first level.
 */
public class Background3 implements Sprite {
    /**
     * this method draws the background on given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#006400")); // Dark green
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(145, 180, 10, 240);
        d.fillRectangle(135, 410, 30, 40);
        d.setColor(Color.ORANGE);
        d.fillCircle(150, 180, 10);
        d.setColor(Color.RED);
        d.fillCircle(150, 180, 7);
        d.setColor(Color.WHITE);
        d.fillCircle(150, 180, 3);
        d.setColor(Color.DARK_GRAY.darker());
        d.fillRectangle(100, 450, 100, 150);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(110 + 18 * i,
                        460 + 30 * j,
                        10, 25);
            }
        }
    }
    /**
     * this method notifies the background that a time unit has passed.
     */
    public void timePassed() {
    }
}
