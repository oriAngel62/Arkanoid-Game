package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Ori Angel
 * ID: 314617739
 * game.PauseScreen class
 * responsible to puase the scrren.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * PauseScreen pause the game.
     *
     * @param sensor key sensor press.
     */
    public PauseScreen(KeyboardSensor sensor) {
        this.keyboard = sensor;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}