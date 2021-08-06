package shapes;

/**
 * @author Ori Angel
 * ID: 314617739
 * hitAndMove.Velocity class
 * hitAndMove.Velocity change the position on the x and the y axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor to create the ball velocity.
     *
     * @param dx the speed in the x plane.
     * @param dy the speed in the y plane.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getDx returns the ball dx attribute.
     *
     * @return the x speed.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy returns the ball dy attribute.
     *
     * @return the y speed.
     */
    public double getDy() {
        return this.dy;
    }


    /**
     * applyToPoint methods get a point and returns an updated point according to the velocity (x+dx, y+dy).
     *
     * @param p The current coordinates.
     * @return the updated coordinates.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * getSpeed return the speed of the ball.
     *
     * @return the he speed of the ball.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * fromAngleAndSpeed calculates the dx and dy parameters by angle and speed
     * that are given.
     *
     * @param angle the angle of the ball movement.
     * @param speed the speed of the ball.
     * @return the game.hitAndMove.Velocity created.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double newDx = speed * Math.sin(Math.toRadians(angle));
        double newDy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(newDx, newDy);
    }
}