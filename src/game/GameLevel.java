package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import levels.EndScreen;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import shapes.Point;
import shapes.Rectangle;
import shapes.Block;
import shapes.Paddle;
import shapes.Velocity;
import shapes.Ball;
import sprites.Collidable;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.List;

/**
 * @author Ori Angel
 * ID: 314617739
 * game.Game class
 * game.Game Create the game environment the gui and balls.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballsCounter;
    private Counter score;
    private Counter lives;
    private Paddle paddle;
    private AnimationRunner animationRunner;
    private boolean running;
    private boolean endGame;
    private boolean lastLevel;


    //const values to create the game environment.
    private final int widthFrame = 800, heightFrame = 600, gap = 40;
    private final int heightPaddle = 30;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInformation;
    private boolean skipLevel = true;

    /**
     * Constructor to create the game.
     *
     * @param levelInformation the information of the level.
     * @param ks               key press.
     * @param ar               animation run screens.
     * @param score            the score in the game.
     * @param lives            num of lives.
     * @param lastLevel        alert that going to finish the game.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor ks, AnimationRunner ar, Counter score,
                     Counter lives, boolean lastLevel) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(0);
        this.ballsCounter = new Counter(0);
        this.score = score;
        this.lives = lives;
        this.endGame = false;
        this.lastLevel = lastLevel;

//        this.gui = new GUI("title", widthFrame, heightFrame);
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.levelInformation = levelInformation;
    }

    /**
     * run run the game -- start the animation loop.
     */
    public void run() {

        // countdown before turn starts.
        this.animationRunner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.animationRunner.run(this);

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        //no more blocks
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();


        if (this.keyboardSensor.isPressed("p")) {
            Animation pauseScreen = new PauseScreen(keyboardSensor);
            Animation a = new KeyPressStoppableAnimation(keyboardSensor, "p", pauseScreen);
            this.animationRunner.run(a);
        }

        if (this.blockCounter.getValue() == 0) {
            if (lastLevel) {
                Animation endScreen = new EndScreen(this.keyboardSensor, score, true);
                Animation a = new KeyPressStoppableAnimation(keyboardSensor, "k", endScreen);
                this.animationRunner.run(a);
            }
            this.running = false;

        }
        //no more balls
        if (this.getBallsCounter().getValue() == 0) {
            if (this.lives.getValue() == 0) {
                Animation endScreen = new EndScreen(this.keyboardSensor, score, false);
                Animation a = new KeyPressStoppableAnimation(keyboardSensor, "m", endScreen);
                this.animationRunner.run(a);
                this.endGame = true;
                this.running = false;
            } else {
                this.lives.decrease(1);
                buildBalls();
                // countdown before turn starts.
                this.animationRunner.run(new CountdownAnimation(2, 3, sprites));
            }

        }

    }

    /**
     * getEndGame get when the game end.
     *
     * @return true if the game end.
     */
    public boolean getEndGame() {
        return this.endGame;
    }

    /**
     * addSprite add sprite to collection.
     *
     * @param sprite a hitAndMove.Sprite is a game object that can be drawn to the screen.
     */
    public void addSprite(Sprite sprite) {
        this.sprites.addSprite(sprite);
    }

    /**
     * addCollidable add collidable to collection.
     *
     * @param collidable collidable will be used by things that can be collided with.
     */
    public void addCollidable(Collidable collidable) {

        this.environment.addCollidable(collidable);
    }

    /**
     * getBallsCounter return BallsCounter.
     *
     * @return ballsCounter.
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
     * Initialize a new game: create the Blocks and shapes.Ball (and shapes.Paddle)
     * and add them to the game.
     */
    public void initialize() {

        //frame
        Block frame = levelInformation.getFrameBlock();
        frame.addToGame(this);
        buildBlocks();
        buildPaddle(frame);
        buildBalls();
        buildDeadBlock();
        //score
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, levelInformation.levelName(), lives);
        scoreIndicator.addToGame(this);


    }

    /**
     * buildBlocks build the blocks in the game.
     */
    public void buildBlocks() {
        List<Block> listBlocks = levelInformation.blocks();
        for (int i = 0; i < listBlocks.size(); i++) {
            Block block = listBlocks.get(i);
            block.addToGame(this);
            blockCounter.increase(1);
            BlockRemover blockRemover = new BlockRemover(this, blockCounter);
            block.addHitListener(blockRemover);
            ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
            block.addHitListener(scoreTrackingListener);
        }
        blockCounter.setValue(listBlocks.size());

    }

    /**
     * buildDeadBlock build the dead block in the game.
     */
    public void buildDeadBlock() {
        //death
        Rectangle rect = new Rectangle(new Point(gap, gap + heightFrame - 2 * gap - 10), widthFrame - 2 * gap,
                10,
                Color.white, ShapeKind.DEATH);
        Block death = new Block(rect);
        death.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        death.addHitListener(ballRemover);
    }

    /**
     * buildPaddle build the peddle block in the game.
     *
     * @param frame for paddle to move.
     */
    public void buildPaddle(Block frame) {
        //paddle
        double xPaddle = (widthFrame / 2 - levelInformation.paddleWidth() / 2);
        double yPaddle = heightFrame - gap - heightPaddle;
        Point pointPaddle = new Point(xPaddle, yPaddle);
        Paddle paddle1 = new Paddle(keyboardSensor, new Rectangle(pointPaddle, levelInformation.paddleWidth()
                , heightPaddle, Color.orange, ShapeKind.PADDLE),
                frame.getCollisionRectangle(), levelInformation.paddleSpeed());
        paddle1.addToGame(this);
        this.paddle = paddle1;
    }

    /**
     * buildBalls build the balls in the game.
     */
    public void buildBalls() {
        double xPaddle = (widthFrame / 2 - levelInformation.paddleWidth() / 2);
        double yPaddle = heightFrame - gap - heightPaddle;
        Point pointPaddle = new Point(xPaddle, yPaddle);
        this.paddle.setRectangle(new Rectangle(pointPaddle, levelInformation.paddleWidth(), heightPaddle
                , Color.orange, ShapeKind.PADDLE));
        List<Velocity> listVelocity = levelInformation.initialBallVelocities();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            double x1 = paddle.getCollisionRectangle().getUp().middle().getX();
            double y1 = paddle.getCollisionRectangle().getUp().middle().getY() - 10;
            Ball ball = new Ball(x1, y1, 7, Color.white,
                    listVelocity.get(i),
                    this.environment);
            ball.addToGame(this);
            this.ballsCounter.increase(1);
        }
    }


    /**
     * finishBlockCheck check if finish level.
     */
    public void finishBlockCheck() {
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
        }
    }

    /**
     * getBlockCounter return blockCounter.
     *
     * @return blockCounter.
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * removeCollidable remove collide object.
     *
     * @param c object that can collide.
     */
    public void removeCollidable(Collidable c) {
        for (int i = 0; i < this.environment.getCollidableObjects().size(); i++) {
            if (this.environment.getCollidableObjects().get(i).equals(c)) {
                this.environment.getCollidableObjects().remove(i);
            }
        }
    }

    /**
     * removeSprite remove sprite object.
     *
     * @param s object that can sprite.
     */
    public void removeSprite(Sprite s) {
        for (int i = 0; i < this.sprites.getSpriteCollection().size(); i++) {
            if (this.sprites.getSpriteCollection().get(i).equals(s)) {
                this.sprites.getSpriteCollection().remove(i);
            }
        }
    }

    /**
     * getScore return the score.
     *
     * @return the score.
     */
    public Counter getScore() {
        return score;
    }


}
