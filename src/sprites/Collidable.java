package sprites;

import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;

/**
 * @author Ori Angel
 * ID: 314617739
 * hitAndMove.Collidable interface
 * hitAndMove.Collidable interface manage objects that the ball can collistion.
 */
public interface Collidable {
    /**
     * getCollisionRectangle Return the "collision shape" of the object.
     *
     * @return the shape of the collision.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the ball that hit.
     * @param collisionPoint  collision point between paddle to ball.
     * @param currentVelocity of the ball.
     * @return the speed of the ball during the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}