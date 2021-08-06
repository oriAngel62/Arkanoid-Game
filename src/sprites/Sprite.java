package sprites;

import biuoop.DrawSurface;

/**
 * @author Ori Angel
 * ID: 314617739
 * hitAndMove.Sprite interface
 * hitAndMove.Sprite interface manage objects show on the screen(GUI).
 */
public interface Sprite {
    /**
     * drawOn draw the sprite to the screen.
     * @param d the surface to draw the balls on.
     * */
    void drawOn(DrawSurface d);

    /**
     * timePassed notify the sprite that time has passed.
     */
    void timePassed();
}