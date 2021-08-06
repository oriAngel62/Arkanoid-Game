package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * @author Ori Angel
 * ID: 314617739
 * hitAndMove.SpriteCollection class
 * The hitAndMove.SpriteCollection class has list of all of the object that show on screen.
 */
public class SpriteCollection {
    private ArrayList<Sprite> spriteCollection;

    /**
     * Constructor.
     * hitAndMove.SpriteCollection is the constructor that creates the spriteCollection list.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList();
    }

    /**
     * addSprite add sprite to collection.
     *
     * @param sprite a hitAndMove.Sprite is a game object that can be drawn to the screen.
     */
    public void addSprite(Sprite sprite) {
        this.spriteCollection.add(sprite);
    }

    /**
     * notifyAllTimePassed call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> s = new ArrayList(this.spriteCollection);
        for (Sprite sprite : s) {
            sprite.timePassed();
        }
    }

    /**
     * getSpriteCollection return the spriteCollection list.
     *
     * @return the spriteCollection list.
     */
    public ArrayList<Sprite> getSpriteCollection() {
        return spriteCollection;
    }


    /**
     * drawAllOncall drawOn(d) on all sprites.
     *
     * @param d the surface to draw the balls on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteCollection) {
            sprite.drawOn(d);
        }
    }

}