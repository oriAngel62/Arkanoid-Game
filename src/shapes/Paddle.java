package shapes;

import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Collidable;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Ori Angel
 * ID: 314617739
 * shapes.Paddle class
 * The shapes.Paddle class manage the paddle in the game.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Rectangle bordersFrame;
    private int[] paddleRegions;

    private int speed;

    /**
     * Constructor.
     * shapes.Paddle is the constructor that creates the paddle.
     *
     * @param keyboard     recognize left or right key pressed.
     * @param rectangle    the shape of the paddle.
     * @param bordersFrame bordersFrame for paddle moving.
     * @param speed of peddle.
     */

    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle, Rectangle bordersFrame, int speed) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.bordersFrame = bordersFrame;
        initRegions();
        this.speed = speed;
    }

    /**
     * moveLeft make the paddle to move left on screen.
     */

    public void moveLeft() {
        if (this.rectangle.getLeft().end().getX() > bordersFrame.getLeft().end().getX()) {
            Point upperLeft = new Point(this.rectangle.getUpperLeft().getX() - speed,
                    this.rectangle.getUpperLeft().getY());
            this.rectangle.setUpperLeft(upperLeft);
            this.rectangle.setBorders(upperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());
        } else {
            Point upperLeft = new Point(bordersFrame.getLeft().end().getX(), this.rectangle.getUpperLeft().getY());
            this.rectangle.setUpperLeft(upperLeft);
            this.rectangle.setBorders(upperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * moveRight make the paddle to move right on screen.
     */
    public void moveRight() {

        if (this.rectangle.getRight().end().getX() < bordersFrame.getRight().end().getX()) {
            Point upperLeft = new Point(this.rectangle.getUpperLeft().getX() + speed,
                    this.rectangle.getUpperLeft().getY());
            this.rectangle.setUpperLeft(upperLeft);
            this.rectangle.setBorders(upperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());
        } else {
            Point upperLeft = new Point(bordersFrame.getRight().end().getX() - this.rectangle.getWidth(),
                    this.rectangle.getUpperLeft().getY());
            this.rectangle.setUpperLeft(upperLeft);
            this.rectangle.setBorders(upperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());
        }

    }

    /**
     * initRegions init fun paddle degrees.
     */
    public void initRegions() {
        this.paddleRegions = new int[5];
        paddleRegions[0] = 300;
        paddleRegions[1] = 330;
        paddleRegions[2] = 0;
        paddleRegions[3] = 30;
        paddleRegions[4] = 60;

    }

    /**
     * setRectangle.
     *
     * @param rectangle1 to update.
     */
    public void setRectangle(Rectangle rectangle1) {
        this.rectangle = rectangle1;
    }

    /**
     * timePassed determine if left or key pressed.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawOn draw the paddle on screen.
     *
     * @param d the surface to draw the balls on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(rectangle.getColor());
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                rectangle.getWidth(), rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * getCollisionRectangle return the shape of the paddle.
     *
     * @return return the shape of the paddle (rectangle).
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * "fun" shapes.Paddle
     * hit mange what happen if the ball hit the paddle.
     *
     * @param hitter          the ball that hit.
     * @param collisionPoint  collision point between paddle to ball.
     * @param currentVelocity of the ball.
     * @return return the velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double nextXPoint = currentVelocity.getDx();
        double nextYPoint = currentVelocity.getDy();
        //next move of the ball.
        Line collision = new Line(new Point(collisionPoint.getX(), collisionPoint.getY()),
                new Point(collisionPoint.getX() + nextXPoint, collisionPoint.getY() + nextYPoint));
        //if collision in up peddle run fun peddle mode.
        if (collision.isIntersecting(rectangle.getUp())) {
            return peddleHit(currentVelocity, collision);
        }
        //if the ball hit in border that different than up, react like it is regular block.
        if (collisionPoint.getY() >= this.rectangle.getUpperLeft().getY()) {
            return new Block(this.getCollisionRectangle()).hit(hitter, collisionPoint, currentVelocity);
        }
        return currentVelocity;

    }

    /**
     * peddleHit return the velocity aftr hit the fun paddle.
     *
     * @param collision       collision line between paddle to ball trajectory.
     * @param currentVelocity of the ball
     * @return return the velocity after the hit.
     */
    public Velocity peddleHit(Velocity currentVelocity, Line collision) {
        double oneRegionSize = this.rectangle.getWidth() / 5;
        for (int i = 0; i < this.paddleRegions.length; i++) {
            double xRectangle = rectangle.getUpperLeft().getX();
            double dxVelocity = currentVelocity.getDx();
            double dyVelocity = currentVelocity.getDy();
            //find the region of the hit.
            if (((i + 1) * oneRegionSize) + xRectangle >= collision.start().getX()) {
                if ((i * oneRegionSize) + xRectangle <= collision.start().getX()) {
                    double velocityValue = Math.sqrt(dxVelocity * dxVelocity + dyVelocity * dyVelocity);
                    return currentVelocity.fromAngleAndSpeed(paddleRegions[i], velocityValue);
                }
            }
        }
        return currentVelocity;
    }

    /**
     * addToGame Add this paddle to the game.
     *
     * @param g game object to add the paddle.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}