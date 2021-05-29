package animation;

import biuoop.DrawSurface;
import miscellaneous.HighScores;
import java.awt.Color;

/**
 * creates high scores animation.
 */
public class HighScoresAnimation implements Animation {
private HighScores highScores;

    /**
     * @param score the highest score object
     */
    public HighScoresAnimation(HighScores score) {
        this.highScores = score;
    }

    /**
     * @param d the surface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.CYAN);
        String val = String.valueOf(this.highScores.getHighScore());
        d.drawText(280, 190, "High Score: " + val, 30);
    }

    /**
     * @return false, the stopping is controlled by the KeyPressStoppableAnimation decorator
     */
    public boolean shouldStop() {
        return false;
    }
}
