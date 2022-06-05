package animation;

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The Pause screen.
 */
public class PauseScreen implements Animation {

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
    }

    /**
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(0, 128, 0)); // Green
        d.fillRectangle(0, 245, d.getWidth(), 100);
        d.setColor(Color.BLACK);
        d.drawText(180, 305, "Paused -- press space to continue", 30);
    }

    /**
     * @return false
     */
    public boolean shouldStop() {
        return false;
    }
}
