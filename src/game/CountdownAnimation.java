package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Ori Angel
 * ID: 314617739
 * CountdownAnimation class
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int currentCount;

    private boolean start;

    /**
     * Constructor.
     * build CountdownAnimation.
     *
     * @param numOfSeconds the numofseconds for the countdown.
     * @param countFrom    the number to start the count.
     * @param gameScreen   the screen to show the count down.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.currentCount = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;

        this.start = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (currentCount >= 0) {
            long timeForSleep = (long) (1000 * this.numOfSeconds) / this.countFrom;
            if (currentCount == countFrom) {
                timeForSleep = (long) (1 * this.numOfSeconds) / this.countFrom;

            }
            Sleeper sleeper = new biuoop.Sleeper();
            gameScreen.drawAllOn(d);

            d.setColor(Color.WHITE);
            d.drawText(400, 400, Integer.toString(currentCount) + "...", 40);
            if (timeForSleep >= 0) {
                sleeper.sleepFor(timeForSleep);
            }
            currentCount--;

        } else {
            this.start = true;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.start;
    }
}
