package arkanoid.animation;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01/06/2016
 */

public interface Menu<T> extends Animation {

    /**
     * Adds selection options to the menu.
     * <p>
     * @param key the key to wait for
     * @param message the message to display
     * @param returnVal the return value
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Return the status of the menu.
     * <p>
     * @return T
     */
    T getStatus();

    void setStop(boolean stop);
}
