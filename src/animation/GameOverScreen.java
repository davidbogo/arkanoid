package animation;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Game over screen.
 */
public class GameOverScreen implements Animation {
    private boolean win;
    private int finalScore;

    /**
     * Instantiates a new Game over screen.
     *
     * @param isWin 	true if it's a win screen. false if it's a game over screen
     * @param score 	the score
     */
    public GameOverScreen(boolean isWin, int score) {
        win = isWin;
        finalScore = score;
    }

    /**
     *
     * @param d Surface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 255, d.getWidth(), 100);
        String endMessage;
        if (win) {
            d.setColor(Color.CYAN);
            endMessage = "You Win! Your score is ";
        } else {
            d.setColor(Color.BLACK);
            endMessage = "Game Over. Your score is ";
        }
        String score = String.valueOf(finalScore);
        endMessage += score;
        d.drawText(215, 315, endMessage, 30);
    }

    /**
     * @return false
     */
    public boolean shouldStop() {
        return false;
    }
}
