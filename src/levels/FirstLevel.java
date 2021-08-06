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
 * FirstLevel class
 * FirstLevel of the game.
 */
public class FirstLevel implements LevelInformation {

    private final int widthFrame = 800, heightFrame = 600, gap = 40, widthBlock = 70, heightBlock = 30;

    /**
     * /**
     * Constructor.
     * build FirstLevel.
     */
    public FirstLevel() {

    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            lst.add(new Velocity(4 + i, 4 + i));
        }
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
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
        Rectangle rectangle = new Rectangle(new Point(gap, gap), xFrame, yFrame, Color.LIGHT_GRAY, ShapeKind.FRAME);
        Block frame = new Block(rectangle);
        return frame;
    }

    @Override
    public List<Block> blocks() {

        List<Block> listBlocks = new ArrayList<>();
        Rectangle rectangle = new Rectangle(new Point(widthFrame / 2 + gap / 2 - widthBlock / 2, 200), 40, 40,
                Color.red, ShapeKind.REGULAR);
        Block block = new Block(rectangle);
        listBlocks.add(block);


        return listBlocks;

    }


    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public int levelNumber() {
        return 1;
    }
}
