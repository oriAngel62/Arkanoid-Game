//package levels;
//
//import game.ShapeKind;
//import shapes.Block;
//import shapes.Point;
//import shapes.Rectangle;
//import shapes.Velocity;
//import spriteAndCollision.Sprite;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Prev implements LevelInformation {
//
//    private final int widthFrame = 800, heightFrame = 600, gap = 40, widthBlock = 70, heightBlock = 30;
//    private final int widthPaddle = 200, heightPaddle = 30;
//
//    public Prev() {
//
//    }
//
//    @Override
//    public int numberOfBalls() {
//        return 700;
//    }
//
//    @Override
//    public List<Velocity> initialBallVelocities() {
//        List<Velocity> lst = new ArrayList<>();
//        for (int i = 0; i < numberOfBalls(); i++) {
//            lst.add(new Velocity(4 + i, 4 + i));
//        }
//        return lst;
//    }
//
//    @Override
//    public int paddleSpeed() {
//        return 10;
//    }
//
//    @Override
//    public int paddleWidth() {
//        return 200;
//    }
//
//    @Override
//    public String levelName() {
//        return "First Level";
//    }
//
//    @Override
//    public Sprite getBackground() {
//        int xFrame = widthFrame - 2 * gap;
//        int yFrame = heightFrame - 2 * gap;
//        //frame
//        Rectangle rectangle = new Rectangle(new Point(gap, gap), xFrame, yFrame, Color.LIGHT_GRAY, ShapeKind.FRAME);
//        Block frame = new Block(rectangle);
//        return frame;
//    }
//
//    public Block getFrameBlock() {
//        int xFrame = widthFrame - 2 * gap;
//        int yFrame = heightFrame - 2 * gap;
//        //frame
//        Rectangle rectangle = new Rectangle(new Point(gap, gap), xFrame, yFrame, Color.LIGHT_GRAY, ShapeKind.FRAME);
//        Block frame = new Block(rectangle);
//        return frame;
//    }
//
//    @Override
//    public List<Block> blocks() {
//
//        List<Block> listBlocks = new ArrayList<>();
//        int startPoint = widthFrame - (gap + widthBlock);
//        int iStart = startPoint, jStart = gap * 3;
//        int rowCount = 7;
//        Color[] colors = new Color[]{Color.red, Color.yellow, Color.cyan, Color.blue, Color.pink};
//        for (int j = 0; j < 5; j++) {
//            for (int i = 0; i < rowCount; i++) {
//                Rectangle rectangle = new Rectangle(new Point(iStart, jStart), widthBlock, heightBlock,
//                        colors[j], ShapeKind.REGULAR);
//                Block block = new Block(rectangle);
////                block.addToGame(this);
////                blockCounter.increase(1);
////                BlockRemover blockRemover = new BlockRemover(this, blockCounter);
////                block.addHitListener(blockRemover);
////                ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
////                block.addHitListener(scoreTrackingListener);
//                listBlocks.add(block);
//                iStart = iStart - widthBlock;
//            }
//            jStart = jStart + heightBlock;
//            iStart = startPoint;
//            rowCount--;
//        }
//
//        return listBlocks;
//
//    }
//
//
//    @Override
//    public int numberOfBlocksToRemove() {
//        return blocks().size();
//    }
//
//    @Override
//    public int levelNumber() {
//        return 1;
//    }
//}
