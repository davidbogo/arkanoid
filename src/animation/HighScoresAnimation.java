package animation;

import biuoop.DrawSurface;
import miscellaneous.HighScores;
import java.awt.*;

public class HighScoresAnimation implements Animation {
private HighScores highScores;

public HighScoresAnimation (HighScores score) {
    this.highScores = score;
}
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.CYAN);
        String val = String.valueOf(this.highScores.getHighScore());
        d.drawText(280, 190, "High Score: " + val, 30);
    }

    public boolean shouldStop() {
        return false;
    }
}
