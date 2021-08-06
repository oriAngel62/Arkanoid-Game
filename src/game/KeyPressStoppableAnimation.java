package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Ori Angel
 * ID: 314617739
 * game.KeyPressStoppableAnimation class
 * responsible to screen animations.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * Constructor to create the game.
     *
     * @param sensor    key sensor press..
     * @param key       key press.
     * @param animation animation run screens.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}