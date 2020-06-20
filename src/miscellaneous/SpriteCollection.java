package miscellaneous;

import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;

/**
 * The type Miscellaneous.Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Instantiates a new Miscellaneous.Sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Notify all time passed.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); ++i) {
            this.spriteList.get(i).timePassed();
        }
    }

    /**
     * this method draws all the sprite objects on a given draw surface.
     *
     * @param d the DrawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); ++i) {
            this.spriteList.get(i).drawOn(d);
        }
    }

    /**
     * this method removes a sprite object from collection.
     *
     * @param s the given sprite object.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
}
