package shapes;

import biuoop.DrawSurface;
import game.GameLevel;
import sprites.Collidable;
import sprites.Sprite;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ori Angel
 * ID: 314617739
 * shapes.Block class
 * The shapes.Block class responsible for the blocks and the frame in the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private List<HitListener> hitListeners;


    /**
     * Constructor.
     * shapes.Block creates shapes.Block when given his shape.
     *
     * @param rectangle the values of the shape of the block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList();

    }

    /**
     * getCollisionRectangle get the shape of the block.
     *
     * @return the shape of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }


    /**
     * notifyHit Notify all listeners about a hit event:.
     *
     * @param hitter the ball that hit.
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


//    public void deathBlockHit(shapes.Ball hitter) {
//        removeFromGame();
//    }

    /**
     * hit mange what happen if the ball hit the block.
     * @param hitter the ball that hit.
     * @param collisionPoint  collision point between block to ball.
     * @param currentVelocity of the ball
     * @return return the velocity after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double xPoint = currentVelocity.getDx();
        double yPoint = currentVelocity.getDy();
        Velocity c = currentVelocity;
        //next move of the ball.
        Line collision = new Line(new Point(collisionPoint.getX(), collisionPoint.getY()),
                new Point(collisionPoint.getX() + xPoint, collisionPoint.getY() + yPoint));
        //check in witch line of the rectangle the collision point and change the velocity
        // (if corner change both x and y).
        if (collision.isIntersecting(rectangle.getUp())) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else {
            if (collision.isIntersecting(rectangle.getDown())) {
                currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
        }
        if (collision.isIntersecting(rectangle.getLeft())) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            if (collision.isIntersecting(rectangle.getRight())) {
                currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            }
        }
        //check change in velocity direction after hit
        if (xPoint == c.getDx() && yPoint == c.getDy()) {
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    /**
     * drawOn method draws the block on the screen.
     *
     * @param surface the surface to draw the blocks on.
     */
    @Override
    public void drawOn(DrawSurface surface) {

        surface.setColor(rectangle.getColor());
        int xRectangle = (int) rectangle.getUpperLeft().getX();
        int yRectangle = (int) rectangle.getUpperLeft().getY();
        surface.fillRectangle(xRectangle, yRectangle, rectangle.getWidth(), rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle(xRectangle, yRectangle, rectangle.getWidth(), rectangle.getHeight());

    }

    @Override
    public void timePassed() {

    }

    /**
     * addToGame method add the block to sprite and collidable.
     *
     * @param gameLevel object that contain the objects in the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * removeFromGame method remove the block from the game.
     *
     * @param gameLevel object that contain the objects in the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
        gameLevel.getBlockCounter().decrease(1);
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
