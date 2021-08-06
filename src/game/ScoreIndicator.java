package game;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Ori Angel
 * ID: 314617739
 * game.ScoreIndicator class
 * The game.ScoreIndicator class update the score in the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private String name;
    private Counter lives;

    /**
     * Constructor.
     * game.ScoreIndicator update the score.
     *
     * @param counter the score in the game.
     * @param name    of the levels.
     * @param lives   till you death.
     */
    public ScoreIndicator(Counter counter, String name, Counter lives) {
        this.score = counter;
        this.name = name;
        this.lives = lives;
    }

    /**
     * addToGame method add the score to sprite.
     *
     * @param gameLevel object that contain the objects in the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }


    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawText(50, 30, name + "      " + "Score: " + score.getValue()
                + "      Lives: " + lives.getValue(), 20);

    }

    @Override
    public void timePassed() {
    }
}

