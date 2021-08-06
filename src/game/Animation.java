package game;

import biuoop.DrawSurface;

/**
 * @author Ori Angel
 * ID: 314617739
 * Animation interface
 * responsible to the screens in the game.
 */

public interface Animation {
    /**
     * doOneFrame show the special screen in the game.
     *
     * @param d show the special screen in the game.
     */
    void doOneFrame(DrawSurface d);

    /**
     * should stop is tells when the screen need to pass.
     *
     * @return when should stop.
     */
    boolean shouldStop();
}