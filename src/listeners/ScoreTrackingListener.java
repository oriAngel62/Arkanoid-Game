package listeners;

import game.Counter;
import shapes.Ball;
import shapes.Block;

/**
 * @author Ori Angel
 * ID: 314617739
 * listeners.ScoreTrackingListener class
 * The listeners.ScoreTrackingListener class track the score in the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * listeners.ScoreTrackingListener constructor.
     *
     * @param scoreCounter score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}