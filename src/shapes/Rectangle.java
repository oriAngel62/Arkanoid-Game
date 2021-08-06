package shapes;

import game.ShapeKind;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ori Angel
 * ID: 314617739
 * shapes.Rectangle class
 * The shapes.Rectangle class has upperLeft, a width, a height a color, and Lines that represent the borders.
 */
public class Rectangle {
    private Point upperLeft;
    private int width;
    private int height;
    private Color color;

    private Line up;
    private Line down;
    private Line right;
    private Line left;

    private String type;
    private ShapeKind shapeKind;

    /**
     * Constructor.
     * shapes.Rectangle creates a rectangle with given upperLeft, a width, a height and color.
     * The method create the lines of the borders.
     *
     * @param type      the type of the rectangle.
     * @param upperLeft the x and y values of the upperLeft point.
     * @param width     of the rectangle.
     * @param height    of the rectangle.
     * @param color     of the rectangle.
     */
    public Rectangle(Point upperLeft, int width, int height, Color color, ShapeKind type) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        Point upperRight = new Point(upperLeft.getX() + this.width, upperLeft.getY());
        Point downerLeft = new Point(upperLeft.getX(), upperLeft.getY() + this.height);
        Point downerRight = new Point(upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        this.up = new Line(upperLeft, upperRight);
        this.down = new Line(downerLeft, downerRight);
        this.right = new Line(upperRight, downerRight);
        this.left = new Line(upperLeft, downerLeft);
        this.color = color;
        this.shapeKind = type;
    }

    /**
     * setBorders set the rectangle's borders with given upperLeft, a width and a height.
     *
     * @param upperLeft1 the x and y values of the upperLeft point.
     * @param width1     of the rectangle.
     * @param height1    of the rectangle.
     */
    public void setBorders(Point upperLeft1, int width1, int height1) {
        Point upperRight = new Point(upperLeft1.getX() + width1, upperLeft1.getY());
        Point downerLeft = new Point(upperLeft1.getX(), upperLeft1.getY() + height1);
        Point downerRight = new Point(upperLeft1.getX() + width1, upperLeft1.getY() + height1);
        this.up = new Line(upperLeft1, upperRight);
        this.down = new Line(downerLeft, downerRight);
        this.right = new Line(upperRight, downerRight);
        this.left = new Line(upperLeft1, downerLeft);
    }

    /**
     * getColor return the color of the rectangle.
     *
     * @return the color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * getType return the type of the rectangle.
     *
     * @return the type.
     */
    public ShapeKind getType() {
        return shapeKind;
    }

    /**
     * intersectionPoints return a (possibly empty) List of intersection points.
     *
     * @param line the represent the trajectory of the ball.
     * @return a (possibly empty) List of intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionList = new ArrayList<Point>();

        if (line.intersectionWith(up) != null) {
            intersectionList.add(line.intersectionWith(up));
        }
        if (line.intersectionWith(down) != null) {
            intersectionList.add(line.intersectionWith(down));
        }
        if (line.intersectionWith(right) != null) {
            intersectionList.add(line.intersectionWith(right));
        }
        if (line.intersectionWith(left) != null) {
            intersectionList.add(line.intersectionWith(left));
        }
        return intersectionList;
    }

    /**
     * getWidth return the rectangle width.
     *
     * @return the rectangle width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * getHeight return the rectangle height.
     *
     * @return the rectangle height.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * getUpperLeft return the upper-left point of the rectangle.
     *
     * @return the the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * setUpperLeft set the upper-left point of the rectangle.
     *
     * @param upperLeft1 the x and y values of the upperLeft point.
     */
    public void setUpperLeft(Point upperLeft1) {
        this.upperLeft = upperLeft1;
    }

    /**
     * setLeft set the left border of the rectangle.
     *
     * @param left1 the left border of the rectangle.
     */
    public void setLeft(Line left1) {
        this.left = left1;
    }

    /**
     * setDown set the down border of the rectangle.
     *
     * @param down1 the down border of the rectangle.
     */
    public void setDown(Line down1) {
        this.down = down1;
    }

    /**
     * setRight set the right border of the rectangle.
     *
     * @param right1 the right border of the rectangle.
     */
    public void setRight(Line right1) {
        this.right = right1;
    }

    /**
     * setUp set the up border of the rectangle.
     *
     * @param up1 the up border of the rectangle.
     */
    public void setUp(Line up1) {
        this.up = up1;
    }

    /**
     * setHeight set the height of the rectangle.
     *
     * @param height1 the height of the rectangle.
     */
    public void setHeight(int height1) {
        this.height = height1;
    }

    /**
     * setWidth set the width of the rectangle.
     *
     * @param width1 the width of the rectangle.
     */
    public void setWidth(int width1) {
        this.width = width1;
    }

    /**
     * setColor set the color of the rectangle.
     *
     * @param color1 the color of the rectangle.
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * getDown get the down border of the rectangle.
     *
     * @return down border of the rectangle.
     */
    public Line getDown() {
        return down;
    }

    /**
     * getRight get the right border of the rectangle.
     *
     * @return right border of the rectangle.
     */
    public Line getRight() {
        return right;
    }

    /**
     * getUp get the up border of the rectangle.
     *
     * @return up border of the rectangle.
     */
    public Line getUp() {
        return up;
    }

    /**
     * getLeft get the left border of the rectangle.
     *
     * @return left border of the rectangle.
     */
    public Line getLeft() {
        return left;
    }
}