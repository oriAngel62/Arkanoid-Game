package shapes;

import java.util.List;

/**
 * @author Ori Angel
 * ID: 314617739
 * shapes.Line class
 * The shapes.Line class has starting a point and end point and connects them.
 */
public class Line {
    private Point start;
    private Point end;


    /**
     * shapes.Line is the constructor and creates the line with 2 Points.
     *
     * @param start point with given x and y values.
     * @param end   point with given x and y values.
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * shapes.Line is the constructor and creates the line with 2 x and y values.
     *
     * @param x1 is the first point's x value in the line.
     * @param y1 is the first point's y value in the line.
     * @param x2 is the last point's x value in the line.
     * @param y2 is the last point's x value in the line.
     */
    public Line(double x1, double y1, double x2, double y2) {

        //check who is the start point
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        }
        if (x1 > x2) {
            this.end = new Point(x1, y1);
            this.start = new Point(x2, y2);
        }
        if (x1 == x2) {
            if (y1 < y2) {
                this.start = new Point(x1, y1);
                this.end = new Point(x2, y2);
            } else {
                this.end = new Point(x1, y1);
                this.start = new Point(x2, y2);
            }
        }

    }

    /**
     * length method calculates the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * middle method calculates the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double xMiddle = (this.start.getX() + this.end.getX()) / 2;
        double yMiddle = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xMiddle, yMiddle);
    }

    /**
     * start method returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * end method returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * slope method calculates the slope of the line.
     *
     * @return the slope of the line.
     */
    public double slope() {
        if (this.end.getX() - this.start.getX() == 0) {
            return -1;
        }
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * intercept method return the intercept.
     *
     * @return the intercept of the line.
     */
    public double intercept() {
        return this.start.getY() - (slope() * this.start.getX());
    }

    /**
     * isSameSlope method return whether 2 lines in the same slope.
     *
     * @param other is the second line that is checked
     * @return true whether in same slope and false otherwise.
     */
    public boolean isSameSlope(Line other) {
        double s1 = slope();
        double s2 = other.slope();
        if (other.slope() == slope()) {
            return true;
        }
        return false;
    }

    /**
     * .
     * isIntersecting method checks whether 2 lines are intersecting
     *
     * @param other is the second line that is checked whether it intersect with the first line.
     * @return true if the lines intersect and false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point interPoint = intersectionWith(other);
        if (interPoint != null) {
            double xInt = interPoint.getX();
            if (xInt >= this.start.getX() && xInt <= this.end.getX()
                    && xInt >= other.start.getX() && xInt <= other.end.getX()) {
                return true;
            }
        }
        return false;
    }

    /**
     * isVertical method checks line is vertical.
     *
     * @param l line to check if vertical.
     * @return true if the line vertical and false otherwise.
     */
    public boolean isVertical(Line l) {
        if (l.start.getX() == l.end.getX()) {
            return true;
        }
        return false;
    }

    /**
     * verticalCheck method checks whether 2 lines are intersecting and
     * returns the intersection point.
     *
     * @param other is the second line that is checked whether it intersect with the first line.
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point verticalCheck(Line other) {
        //2 vertical Lines
        if (isVertical(this) && isVertical(other)) {
            if (this.start.getY() == other.end.getY()) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (this.end.getY() == other.start.getY()) {
                return new Point(this.end.getX(), this.end.getY());
            }
            //one point and one vertical
            if (this.start.getY() == this.end.getY() || other.start.getY() == other.end.getY()) {
                if (this.start.getY() >= other.start.getY() && this.start.getY() <= other.end.getY()) {
                    return new Point(this.start.getX(), this.end.getY());
                }
                if (other.start.getY() >= this.start.getY() && other.start.getY() <= this.end.getY()) {
                    return new Point(other.start.getX(), other.end.getY());
                }
            }
        } else {
            //1 vertical
            if (isVertical(this)) {
                if (other.start.getX() <= this.start.getX() && other.end.getX() >= this.end.getX()) {
                    double lPoint = other.slope() * this.start.getX() + other.intercept();
                    if (lPoint >= this.start.getY() && lPoint <= this.end.getY()) {
                        return new Point(this.start.getX(), other.slope() * this.start.getX() + other.intercept());
                    }
                }
            }
            if (isVertical(other)) {
                if (this.start.getX() <= other.start.getX() && this.end.getX() >= other.end.getX()) {
                    double lPoint = this.slope() * other.start.getX() + this.intercept();
                    if (lPoint >= other.start.getY() && lPoint <= other.end.getY()) {
                        return new Point(other.start.getX(), this.slope() * other.start.getX() + this.intercept());
                    }
                }
            }
        }
        return null;
    }

    /**
     * intersectionWith method checks whether 2 lines are intersecting and
     * returns the intersection point.
     *
     * @param other is the second line that is checked whether it intersect with the first line.
     * @return the intersection point if the lines intersect and null otherwise.
     */

    public Point intersectionWith(Line other) {
        //same line not point
        if (equals(other) && other.start.getX() != other.end.getX()) {
            return null;
        }
        if (isVertical(other) || isVertical(this)) {
            return verticalCheck(other);
        }
        double xIntersection = (intercept() - other.intercept()) / (other.slope() - slope());
        double yIntersection = slope() * xIntersection + intercept();
        if (xIntersection >= this.start.getX() && xIntersection <= this.end.getX()
                && xIntersection >= other.start.getX() && xIntersection <= other.end.getX()) {

            return new Point(xIntersection, yIntersection);

        }
        return null;
    }


    /**
     * equals method checks whether 2 lines are the same or not.
     *
     * @param other is the second line that is checked whether its the same line as the first one.
     * @return true if the lines are equal and false otherwise.
     */
    public boolean equals(Line other) {
        if (this.start.getX() == other.start.getX() && this.end.getY() == other.end.getY()) {
            if (this.start.getY() == other.start.getY() && this.end.getX() == other.end.getX()) {
                return true;
            }
        }
        return false;
    }

    /**
     * closestIntersectionToStartOfLine equals method checks whether 2 lines are the same or not.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect rectangle to check with the ball.
     * @return point of the closest rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionList = rect.intersectionPoints(this);
        if (intersectionList.isEmpty()) {
            return null;
        }
        int i = 0;
        double min = intersectionList.get(i).distance(this.start);
        int minPoint = 0;
        for (i = 1; i < intersectionList.size(); i++) {
            double num = intersectionList.get(i).distance(this.start);
            if (num < min) {
                min = num;
                minPoint = i;
            }
        }
        return intersectionList.get(minPoint);
    }
}