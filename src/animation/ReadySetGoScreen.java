package animation;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Ready.. Set.. Go.. screen.
 */
public class ReadySetGoScreen implements Animation {
    /**
     * The Start time.
     */
    private long startTime;

    /**
     * Instantiates a new Ready set go screen.
     */
    public ReadySetGoScreen() {
        startTime = System.currentTimeMillis();
    }

    /**
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        String textToShow;
        long curTime = System.currentTimeMillis();
        if (curTime - startTime < 1000) {
            textToShow = "3";
        } else if (curTime - startTime < 2000) {
            textToShow = "2";
        } else {
            textToShow = "1";
        }
        d.setColor(Color.BLACK);
        d.drawText(370, 300, textToShow, 100);
    }

    /**
     * @return true after 3 seconds
     */
    public boolean shouldStop() {
        return (System.currentTimeMillis() - startTime > 3000);    // Stop after 3 seconds
    }
}
