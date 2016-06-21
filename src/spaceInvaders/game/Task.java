package spaceinvaders.game;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 01/06/2016
 */

/**
 * The task interface.
 *
 * @param <T> the task.
 */
public interface Task<T> {

    /**
     * runs a specific task.
     * <p>
     *
     * @return the task object
     */
    T run();
}
