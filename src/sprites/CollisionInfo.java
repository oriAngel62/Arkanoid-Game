package sprites;

import shapes.Point;

/**
 * @author Ori Angel
 * ID: 314617739
 * hitAndMove.CollisionInfo class
 * hitAndMove.CollisionInfo manage the info of collistion.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     * hitAndMove.CollisionInfo creates hitAndMove.CollisionInfo details.
     *
     * @param collisionPoint  the x value of the frame.
     * @param collisionObject the y value of the frame.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * collisionPoint the point at which the collision occurs.
     *
     * @return collisionPoint.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * collisionObject the collidable object involved in the collision.
     *
     * @return collisionObject.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
