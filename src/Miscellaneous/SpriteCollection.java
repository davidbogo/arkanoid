package miscellaneous;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Miscellaneous.Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Instantiates a new Sprite collection.
     */
    public  SpriteCollection() {
        spriteList = new ArrayList<Sprite>();
    }


    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).drawOn(d);
        }
    }

    /**
     * this method removes a sprite object from collection.
     * @param s the given sprite object.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }
}