package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Ori Angel
 * ID: 314617739
 * AnimationRunner class
 * listeners.BallRemover remove ball from game.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     * AnimationRunner run special screen.
     *
     * @param g the gui of the game.
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = 60;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * run special screen.
     *
     * @param animation the special screen.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
