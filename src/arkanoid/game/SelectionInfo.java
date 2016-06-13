package arkanoid.game;

import arkanoid.animation.Menu;


/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01/06/2016
 */

/**
 * The class.
 *
 * @param <T> the task.
 */
public class SelectionInfo<T> {

    private String key;
    private String message;
    private Object returnVal;
    private Menu<T> subMenus;

    /**
     * The constructor.
     *
     * @param key       the string of which is passed by the user.
     * @param message   to display.
     * @param returnVal of the menu option.
     * @param subMenu   of the selection.
     */
    public SelectionInfo(String key, String message, Object returnVal, Menu<T> subMenu) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
        this.subMenus = subMenu;
    }

    /**
     * See return.
     *
     * @return the key of the selection.
     */
    public String getKey() {
        return key;
    }

    /**
     * See return.
     *
     * @return the message of the selection.
     */
    public String getMessage() {
        return message;
    }

    /**
     * See return.
     *
     * @return the return value.
     */
    public Object getReturnVal() {
        return returnVal;
    }

    /**
     * See return.
     *
     * @return the sub menu.
     */
    public Menu<T> getSubMenu() {
        return this.subMenus;
    }

    @Override
    /**
     * Preses the message to be displayed.
     */
    public String toString() {
        return "Press " + "'" + this.key + "'" + " to " + this.message;
    }
}
