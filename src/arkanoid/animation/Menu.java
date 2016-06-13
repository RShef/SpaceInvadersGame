package arkanoid.animation;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01/06/2016
 */

public interface Menu<T> extends Animation {
    /**
     * Return the status of the menu, Very important.
     * <p>
     *
     * @return T
     */
    T getStatus();

    /**
     * Adds selection options to the menu.
     * <p>
     *
     * @param key       the key to wait for
     * @param message   the message to display
     * @param returnVal the return value
     */
    void addSelection(String key, String message, T returnVal, Menu<T> subMenu);





    /**
     * Adds a Sub menu.
     *
     * @param key     a string key.
     * @param message a message.
     * @param subMenu a submenu.
     */
    void addSubMenu(String key, String message, T returnVal, Menu<T> subMenu);
    /**
     * Stops.
     *
     * @param stop a boolean.
     */
    void setStop(boolean stop);

    /**
     * Reat method.
     */
    void rest();

}
