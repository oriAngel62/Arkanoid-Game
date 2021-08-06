package game;

import shapes.Line;
import shapes.Point;
import sprites.Collidable;
import sprites.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ori Angel
 * ID: 314617739
 * game.GameEnvironment class
 * game.GameEnvironment mange list with all the collidableObjects.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidableObjects;

    /**
     * Constructor.
     * game.GameEnvironment creates collidable Objects list.
     */
    public GameEnvironment() {
        this.collidableObjects = new ArrayList<Collidable>();
    }

    /**
     * addCollidable add collidable to collection.
     *
     * @param collidable collidable will be used by things that can be collided with.
     */
    public void addCollidable(Collidable collidable) {
        this.collidableObjects.add(collidable);
    }

    /**
     * getCollidableObjects return collidableObjects.
     *
     * @return collidableObjects return collidableObjects list.
     */
    public List<Collidable> getCollidableObjects() {
        return collidableObjects;
    }

    /**
     * getClosestCollision return hitAndMove.CollisionInfo with the closest hitAndMove.CollisionInfo.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of the ball.
     * @return hitAndMove.CollisionInfo return hitAndMove.CollisionInfo with the expected values.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double min = 1000;
        int iMin = -1;
        Point pClosest = null;
        for (int i = 0; i < collidableObjects.size(); i++) {
            pClosest =
                    trajectory.closestIntersectionToStartOfLine(collidableObjects.get(i).getCollisionRectangle());
            if (pClosest != null) {
                double distance = pClosest.distance(trajectory.start());
                if (distance < min) {
                    min = distance;
                    iMin = i;
                }
            }
        }
        if (iMin == -1) {
            return null;
        }
        pClosest = trajectory.closestIntersectionToStartOfLine(collidableObjects.get(iMin).getCollisionRectangle());
        return new CollisionInfo(pClosest, collidableObjects.get(iMin));

    }
}