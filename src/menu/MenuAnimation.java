package menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class represents a menu animation object.
 * @param <T> the menu type.
 */
public class MenuAnimation<T> implements Menu<T> {
    private String menuTitle;
    private List<String> keys;
    private List<String> messages;
    private List<T> type;
    private List<Boolean> itExsits;
    private T status;
    private boolean shutdown;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    /**
     * construct a menu animation object from a menuTitle,
     * a keyboardSensor and an animationRunner.
     * @param menuTitle the given menuTitle.
     * @param keyboardSensor the given keyboardSensor.
     * @param animationRunner the given animationRunner.
     */
    public MenuAnimation(String menuTitle, KeyboardSensor keyboardSensor,
                         AnimationRunner animationRunner) {
        this.menuTitle = menuTitle;
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.type = new ArrayList<T>();
        this.itExsits = new ArrayList<Boolean>();
        this.shutdown = false;
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
    }
    public boolean shouldStop() {
        return this.shutdown;
    }
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.decode("#1e7f00"));
        d.fillRectangle(0, d.getHeight() / 2 - 160, d.getWidth(), 90);
        d.setColor(Color.RED);
        d.fillRectangle(0, d.getHeight() / 2 - 160, d.getWidth(), 3);
        d.drawText(180, d.getHeight() / 2 - 90, this.menuTitle, 70);
        d.fillRectangle(0, d.getHeight() / 2 - 70, d.getWidth(), 3);
        for (int i = 0; i < this.keys.size(); i++) {
            d.drawText(260, 200 + 40 * (i + 2), "("
                    + this.keys.get(i) + ") " + this.messages.get(i), 30);
        }
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keyboardSensor.isPressed(this.keys.get(i))) {
                if (this.itExsits.get(i)) {
                    this.status = this.type.get(i);
                    this.shutdown = true;
                } else {
                    this.animationRunner.run(this);
                    this.status = null;
                    this.shutdown = true;
                    break;
                }
            }
        }
    }

    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.type.add(returnVal);
        this.itExsits.add(true);
    }
    public T getStatus() {
        return this.status;
    }

}

