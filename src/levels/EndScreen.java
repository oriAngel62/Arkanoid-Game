package levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;
import game.Counter;

/**
 * @author Ori Angel
 * ID: 314617739
 * EndScreen class
 * end screen of the game.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private boolean isWon;

    /**
     * Constructor.
     * build CountdownAnimation.
     *
     * @param sensor key sensor for press.
     * @param score  the score in the game.
     * @param isWon  check if the player won.
     */
    public EndScreen(KeyboardSensor sensor, Counter score, boolean isWon) {
        this.keyboard = sensor;
        this.stop = false;
        this.score = score;
        this.isWon = isWon;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (isWon) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is  " + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
