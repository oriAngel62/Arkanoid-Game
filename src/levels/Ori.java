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
//public class Ori implements LevelInformation {
//    private final int widthFrame = 800, heightFrame = 600, gap = 40, widthBlock = 45, heightBlock = 40;
//    private final int widthPaddle = 200, heightPaddle = 30;
//
//    public Ori() {
//
//    }
//
//    @Override
//    public int numberOfBalls() {
//        return 10;
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
//        return "Second Level";
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
//        Rectangle rectangle = new Rectangle(new Point(gap, gap), xFrame, yFrame, Color.lightGray, ShapeKind.FRAME);
//        Block frame = new Block(rectangle);
//        return frame;
//    }
//
//    @Override
//    public List<Block> blocks() {
//
//        List<Block> listBlocks = new ArrayList<>();
//        int startPoint = widthFrame - (gap + widthBlock);
//        int iStart = gap, jStart = gap;
//        int rowCount = 7;
//        Color[] colors = new Color[]{Color.red, Color.yellow, Color.cyan, Color.blue, Color.pink};
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 18; j++) {
//                Rectangle rectangle = new Rectangle(new Point(iStart, jStart), widthBlock, heightBlock,
//                        colors[1], ShapeKind.REGULAR);
//                Block block = new Block(rectangle);
//                if (i == 2) {
//                    if (j == 2 || j == 3 || j == 4 || j == 6 || j == 7 || j == 8 || j == 11 || j == 12 || j == 13) {
//                        listBlocks.add(block);
//                    }
//                }
//
//                if (i == 3) {
//                    if (j == 2 || j == 4 || j == 6 || j == 8 || j == 12) {
//                        listBlocks.add(block);
//                    }
//                }
//                if (i == 4) {
//                    if (j == 2 || j == 4 || j == 7 || j == 6 || j == 8 || j == 12) {
//                        listBlocks.add(block);
//                    }
//                }
//                if (i == 5) {
//                    if (j == 2 || j == 4 || j == 6 || j == 8 || j == 12) {
//                        listBlocks.add(block);
//                    }
//                }
//
//                if (i == 6) {
//                    if (j == 2 || j == 3 || j == 4 || j == 6 || j == 8 || j == 9 || j == 11 || j == 12 || j == 13) {
//                        listBlocks.add(block);
//                    }
//                }
//                iStart = iStart + widthBlock;
//            }
//            jStart = jStart + heightBlock;
//            ;
//            iStart = gap;
//            rowCount--;
//
//        }
//        return listBlocks;
//
//    }
//
//    @Override
//    public int levelNumber() {
//        return 4;
//    }
//
//    @Override
//    public int numberOfBlocksToRemove() {
//        return blocks().size();
//    }
//}
