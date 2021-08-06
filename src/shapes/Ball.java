package shapes;

import biuoop.DrawSurface;
import game.GameLevel;
import game.GameEnvironment;
import game.ShapeKind;
import sprites.Collidable;
import sprites.CollisionInfo;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Ori Angel
 * ID: 314617739
 * shapes.Ball class
 * shapes.Ball have all the attributes that represant balls.
 */

public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor to create the ball.
     *
     * @param center          a shapes.Point which will be the center of the ball.
     * @param radius          the radius of the ball.
     * @param color           the color of the ball.
     * @param velocity        the speed of the ball.
     * @param gameEnvironment the objects that the ball can hit in the game.
     */
    public Ball(Point center, int radius, Color color, Velocity velocity, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
        this.gameEnvironment = gameEnvironment;

    }

    /**
     * Constructor to create the ball.
     *
     * @param xCenter  the x center of the ball.
     * @param yCenter  the y center of the ball.
     * @param radius   the radius of the ball.
     * @param color    the color of the ball.
     * @param game     the objects that the ball can hit in the game.
     * @param velocity the speed of the ball.
     */
    public Ball(double xCenter, double yCenter, int radius, Color color, Velocity velocity, GameEnvironment game) {
        this(new Point(xCenter, yCenter), radius, color, velocity, game);
    }

    /**
     * getX method returns the x coordinate of the center of the ball.
     *
     * @return the x coordinate of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * setCenter method set the center of the ball.
     *
     * @param c the center of the ball.
     */
    public void setCenter(Point c) {
        this.center = c;
    }

    /**
     * getY method returns the y coordinate of the center of the ball.
     *
     * @return the y coordinate of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize method returns the size of the ball.
     *
     * @return the size of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * getColor method returns the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn method draws the ball on the screen.
     *
     * @param surface the surface to draw the balls on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), this.radius);
    }

    /**
     * timePassed method active move ball method.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * setVelocity sets the balls velocity.
     *
     * @param v the velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * setVelocity sets the balls velocity.
     *
     * @param dx the velocity of the x plane.
     * @param dy the velocity of the y plane.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getVelocity gets the balls velocity.
     *
     * @return the balls velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moveOneStep Changes the center of the ball according to the balls
     * velocity.
     */
    public void moveOneStep() {
        Point nextStep = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line trajectory = new Line(this.center, nextStep);
        //going to be collision on next step.
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            //change the values to make the ball not to stuck in the blocks.
            double newXPoint;
            double newYPoint = collisionInfo.collisionPoint().getY();
            if (this.velocity.getDx() < 0) {
                newXPoint = collisionInfo.collisionPoint().getX() + radius;
            } else {
                newXPoint = collisionInfo.collisionPoint().getX() - radius;
            }
            if (this.velocity.getDy() < 0) {
                newYPoint = collisionInfo.collisionPoint().getY() + radius;
            } else {
                newYPoint = collisionInfo.collisionPoint().getY() - radius;
            }
            this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                    this.velocity);
            this.center = new Point(newXPoint, newYPoint);
        } else {
            this.center = this.velocity.applyToPoint(this.center);
        }
        //check if the ball going to be in paddle.
        Collidable paddle = findPaddle();
        double xPaddle = paddle.getCollisionRectangle().getUpperLeft().getX();
        double yPaddle = paddle.getCollisionRectangle().getUpperLeft().getY();
        double widthPaddle = paddle.getCollisionRectangle().getWidth();
        if (paddle != null) {
            if (this.center.getX() >= xPaddle && this.center.getX() <= xPaddle + widthPaddle) {
                while (this.center.getY() >= yPaddle) {
                    this.center = new Point(this.center.getX(), this.center.getY() - 0.1);
                }
            }
        }
    }

    /**
     * findPaddle find the paddle in the collidable list.
     *
     * @return the paddle.
     */
    public Collidable findPaddle() {
        for (int i = 0; i < gameEnvironment.getCollidableObjects().size(); i++) {
            //find the paddle by his color.
            if (gameEnvironment.getCollidableObjects().get(i).getCollisionRectangle().getType() == ShapeKind.PADDLE) {
                return gameEnvironment.getCollidableObjects().get(i);
            }
        }
        return null;
    }

    /**
     * IsInFrame check whether the ball in frame.
     *
     * @param f is the boundaries of the ball.
     * @return true if in frame false otherwise.
     */
    public boolean isInFrame(Frame f) {
        int r = this.radius;
        int xCenter = (int) this.center.getX();
        int yCenter = (int) this.center.getY();
        if ((yCenter - r >= f.getYPoint()) && (yCenter - r <= f.getYPoint() + f.getHeight())) {
            return true;
        }
        if ((xCenter - r >= f.getXPoint()) && (xCenter - r <= f.getXPoint() + f.getWidth())) {
            return true;
        }
        return false;
    }

    /**
     * addToGame method add the ball to sprite.
     *
     * @param gameLevel object that contain the objects in the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }


    /**
     * removeFromGame method remove the ball.
     *
     * @param gameLevel object that contain the objects in the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.getBallsCounter().decrease(1);

    }
}
