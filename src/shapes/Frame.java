package shapes;

/**
 * @author Ori Angel
 * ID: 314617739
 * shapes.Frame class
 * Class that give all of the shapes.Frame information.
 */
public class Frame {
    private int xPoint;
    private int yPoint;
    private int width;
    private int height;

    /**
     * Constructor.
     * shapes.Frame creates frame details.
     *
     * @param xPoint the x value of the frame.
     * @param yPoint the y value of the frame.
     * @param width  the width value of the frame.
     * @param height the width height of the frame.
     */
    public Frame(int xPoint, int yPoint, int width, int height) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        this.width = width;
        this.height = height;
    }
    /**
     * getYPoint returns the frame yPoint attribute.
     *
     * @return  the frame yPoint attribute.
     */
    public int getYPoint() {
        return yPoint;
    }
    /**
     * getXPoint returns the frame yPoint attribute.
     *
     * @return the frame yPoint attribute..
     */
    public int getXPoint() {
        return xPoint;
    }
    /**
     * getHeight returns the frame height attribute.
     *
     * @return the frame height attribute..
     */
    public int getHeight() {
        return height;
    }
    /**
     * getWidth returns the frame width attribute.
     *
     * @return the frame width attribute..
     */
    public int getWidth() {
        return width;
    }

}
