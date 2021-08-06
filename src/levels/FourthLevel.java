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
 * FourthLevel class
 * FourthLevel of the game.
 */
public class FourthLevel implements LevelInformation {
    private final int widthFrame = 800, heightFrame = 600, gap = 40, widthBlock = 48, heightBlock = 30;

    /**
     * /**
     * Constructor.
     * build FourthLevel.
     */
    public FourthLevel() {

    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<>();
        int angle = -40;
        for (int i = 0; i < numberOfBalls(); i++) {
            lst.add(Velocity.fromAngleAndSpeed(angle, 7));
            angle = angle + 90;
        }
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Final Four";
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
        int iStart = startPoint, jStart = gap * 2;
        Color[] colors = new Color[]{Color.gray, Color.red, Color.yellow, Color.blue, Color.white, Color.pink
                , Color.cyan};
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 15; i++) {
                Rectangle rectangle = new Rectangle(new Point(iStart, jStart), widthBlock, heightBlock,
                        colors[j], ShapeKind.REGULAR);
                Block block = new Block(rectangle);
                if (colors[j] == (Color.gray)) {
                    Block twice = new Block(rectangle);
                    listBlocks.add(twice);
                }
                listBlocks.add(block);
                iStart = iStart - widthBlock;
            }
            jStart = jStart + heightBlock;
            iStart = startPoint;

        }

        return listBlocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public int levelNumber() {
        return 4;
    }
}