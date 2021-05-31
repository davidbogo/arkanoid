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
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    public void increase(int number) {
        value += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    public void decrease(int number) {
        value -= number;
    }
    // get current count.

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
