package eventhandlers;

import java.awt.Color;

import geometry.Rectangle;
import levels.GameLevel;
import miscellaneous.Sprite;
import biuoop.DrawSurface;

/**
 * This class represents a score indicator object.
 */
public class LevelNameIndicator implements Sprite {
    private String name;
    private final Rectangle rectangle;

    /**
     * Construct a score indicator from a score counter object.
     *
     * @param levelName the level name
     */
    public LevelNameIndicator(String levelName) {
        name = levelName;
        rectangle = new Rectangle(400, 2, 400, 15);
    }

    /**
     * this method draws the score indicator on given DrawSurface.
     * @param surface the Surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawText((int) (rectangle.getUpperLeft().getX() + rectangle.getWidth() / 2 - 20),
                         (int) (rectangle.getUpperLeft().getY() + rectangle.getHeight() / 2 + 5),
                         "Level Name: " + name, 13);
    }

    /**
     * this method notifies the score indicator that a time unit has passed.
     */
    public void timePassed() {
    }

    /**
     * this method adds the score indicator to the game.
     *
     * @param game  the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
