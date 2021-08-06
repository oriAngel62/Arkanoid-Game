package listeners;

import game.Counter;
import game.GameLevel;
import shapes.Ball;
import shapes.Block;

/**
 * @author Ori Angel
 * ID: 314617739
 * listeners.BallRemover class
 * listeners.BallRemover remove ball from game.
 */

public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     * listeners.BlockRemover creates listeners.BlockRemover details.
     *
     * @param gameLevel           object that contain the objects in the game.
     * @param remainingBalls update remainingBalls.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = new Counter(remainingBalls.getValue());
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
    }
}
