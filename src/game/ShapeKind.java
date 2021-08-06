package game;

/**
 * @author Ori Angel
 * ID: 314617739
 * game.ShapeKind enum
 * block shape.
 */
public enum ShapeKind {
    FRAME("frame"),
    PADDLE("paddle"),
    DEATH("death"),
    REGULAR("regular"),
    DOUBLE("double");

    private String stringRepresentation;

    /**
     * Constructor.
     * game.ShapeKind is the shape of the block.
     *
     * @param stringRepresentation for shape.
     */
    private ShapeKind(String stringRepresentation) {

        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public String toString() {
        return this.stringRepresentation;
    }


}
