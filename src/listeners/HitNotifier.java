package listeners;

/**
 * @author Ori Angel
 * ID: 314617739
 * listeners.HitNotifier interface
 * listeners.HitNotifier interface notify that there is hit.
 */
public interface HitNotifier {
    /**
     * addHitListener Add hl as a listener to hit events.
     *
     * @param hl listen to hit.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener Remove hl from the list of listeners to hit events.
     *
     * @param hl listen to hit.
     */
    void removeHitListener(HitListener hl);
}