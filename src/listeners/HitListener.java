package listeners;

import shapes.Ball;
import shapes.Block;

/**
 * @author Ori Angel
 * ID: 314617739
 * listeners.HitListener interface
 * game.Game Create the game environment the gui and balls.
 */
public interface HitListener {
    /**
     * hitEvent is called whenever the beingHit object is hit.
     * The hitter parameter is the shapes.Ball that's doing the hitting.
     *
     * @param beingHit hit block.
     * @param hitter   hit ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}