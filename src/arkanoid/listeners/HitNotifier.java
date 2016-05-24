package arkanoid.listeners;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 09/05/2016
 */

public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * <p>
     *
     * @param hl a hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners.
     * <p>
     *
     * @param hl a hit listener
     */
    void removeHitListener(HitListener hl);
}