package levels;

import shapes.Block;
import shapes.Velocity;
import sprites.Sprite;

import java.util.List;

/**
 * @author Ori Angel
 * ID: 314617739
 * LevelInformation interface
 * responsible to informations of the level.
 */
public interface LevelInformation {
    /**
     * numberOfBalls.
     *
     * @return numberOfBalls.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities.
     *
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed.
     *
     * @return paddleSpeed.
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     *
     * @return paddleWidth.
     */
    int paddleWidth();

    /**
     * levelName.
     *
     * @return levelName.
     */
    String levelName();

    /**
     * getBackground.
     *
     * @return the background of the level.
     */
    Sprite getBackground();

    /**
     * blocks.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove.
     *
     * @return the number of block to remove.
     */
    int numberOfBlocksToRemove();

    /**
     * levelNumber.
     *
     * @return levelNumber.
     */
    int levelNumber();

    /**
     * getFrameBlock.
     *
     * @return getFrameBlock.
     */
    Block getFrameBlock();
}

