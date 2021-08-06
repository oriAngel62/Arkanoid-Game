package listeners;

import game.Counter;
import game.GameLevel;
import shapes.Ball;
import shapes.Block;

/**
 * @author Ori Angel
 * ID: 314617739
 * listeners.BlockRemover class
 * listeners.BallRemover removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * listeners.BlockRemover creates listeners.BlockRemover details.
     *
     * @param gameLevel          object that contain the objects in the game.
     * @param removedBlocks block that remove.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = new Counter(removedBlocks.getValue());
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        this.gameLevel.finishBlockCheck();
    }
}