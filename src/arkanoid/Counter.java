package arkanoid;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 09/05/2016
 */

public class Counter {

    private int count;

    /**
     * Inits a counter.
     * <p/>
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Adds a given number to the count.
     * <p/>
     *
     * @param number the number to increase by
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract a given number from the count.
     * <p/>
     *
     * @param number the number to decrease by
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current value.
     * <p/>
     *
     * @return int value.
     */
    public int getValue() {
        return this.count;
    }
}
