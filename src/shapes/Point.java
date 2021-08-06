package shapes;

/**
 * @author Ori Angel
 * ID: 314617739
 * shapes.Point class
 * Create shapes.Point with x and y coordinates.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * shapes.Point creates a point with given x and y values.
     *
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance method returns the distance of this point to the other point.
     *
     * @param other the second point to check it's distance from the first one.
     * @return double with the value of the distance between the points.
     */
    public double distance(Point other) {

        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * equals method checks whether 2 points are the same or not.
     *
     * @param other the second point to check if it's the same as the first one.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other.getX() == this.x && other.getY() == this.y) {
            return true;
        }
        return false;
    }

    /**
     * getX method returns the x value of the point.
     *
     * @return x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY method returns the y value of the point.
     *
     * @return y value of the point.
     */
    public double getY() {
        return this.y;
    }
}
