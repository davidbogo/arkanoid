package eventhandlers;

/**
 * The type EventHandlers.Counter.
 */
public class Counter {
    private int value;

    /**
     * Instantiates a new EventHandlers.Counter.
     *
     * @param initialvalue the initialvalue
     */
    public Counter(int initialvalue) {
        value = initialvalue;
    }

    /**
     * Increases the current count by a given amount
     *
     * @param number the number
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * Decreases the current count by a given amount
     *
     * @param number the number
     */
    public void decrease(int number) {
        value -= number;
    }

    /**
     * Gets the current count.
     *
     * @return the current count
     */
    public int getValue() {
        return value;
    }
}
