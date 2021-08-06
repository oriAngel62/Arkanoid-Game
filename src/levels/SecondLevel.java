package levels;

import game.ShapeKind;
import shapes.Block;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ori Angel
 * ID: 314617739
 * SecondLevel class
 * SecondLevel of the game.
 */
public class SecondLevel implements LevelInformation {
    private final int widthFrame = 800, heightFrame = 600, gap = 40, widthBlock = 48, heightBlock = 40;
    /**
     * /**
     * Constructor.
     * build SecondLevel.
     */
    public SecondLevel() {

    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<>();
        int angle = -40;
        for (int i = 0; i < numberOfBalls(); i++) {

            lst.add(Velocity.fromAngleAndSpeed(angle, 10));
            angle = angle + 10;
        }
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return widthFrame - gap * 4;
    }

    @Override
    public String levelName() {
        return "Second Level";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public Block getFrameBlock() {
        int xFrame = widthFrame - 2 * gap;
        int yFrame = heightFrame - 2 * gap;
        //frame
        Rectangle rectangle = new Rectangle(new Point(gap, gap), xFrame, yFrame, Color.lightGray, ShapeKind.FRAME);
        Block frame = new Block(rectangle);
        return frame;
    }

    @Override
    public List<Block> blocks() {

        List<Block> listBlocks = new ArrayList<>();
        int startPoint = widthFrame - (gap + widthBlock);
        int iStart = gap, jStart = gap + 100;
        int rowCount = 7;
        Color[] colors = new Color[]{Color.red, Color.red, Color.orange, Color.orange, Color.yellow, Color.yellow,
                Color.green, Color.green, Color.green, Color.blue, Color.blue, Color.pink,
                Color.pink, Color.cyan, Color.cyan};
        for (int i = 0; i < 15; i++) {

            Rectangle rectangle = new Rectangle(new Point(iStart, jStart), widthBlock, heightBlock,
                    colors[i], ShapeKind.REGULAR);
            Block block = new Block(rectangle);
//                block.addToGame(this);
//                blockCounter.increase(1);
//                BlockRemover blockRemover = new BlockRemover(this, blockCounter);
//                block.addHitListener(blockRemover);
//                ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
//                block.addHitListener(scoreTrackingListener);
            listBlocks.add(block);
            iStart = iStart + widthBlock;
        }
        jStart = jStart + heightBlock;
        ;
        iStart = gap;
        rowCount--;


        return listBlocks;

    }

    @Override
    public int levelNumber() {
        return 2;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
