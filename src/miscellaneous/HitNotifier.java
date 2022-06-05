package miscellaneous;

import eventhandlers.HitListener;

/**
 * The interface Hit notifier.
 */
public interface HitNotifier {
    /**
     * Adds hl as a listener to hit events.
     *
     * @param hl        the hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Removes hl from the list of listeners to hit events.
     *
     * @param hl        the hit listener
     */
    void removeHitListener(HitListener hl);
}
