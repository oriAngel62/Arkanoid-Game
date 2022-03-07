import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.GameFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ori Angel
 * ID: 314617739
 * Ass3Game class
 * Ass3Game Create game class and run the game.
 */
public class Arkanoid {
    /**
     * The main creates the game object, initialize and run the game.
     *
     * @param args the levels that will run in the game.
     */
    public static void main(String[] args) {
        final int widthFrame = 800, heightFrame = 600;
        GUI gui = new GUI("title", widthFrame, heightFrame);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        List<Integer> clean = cleanStringLevels(args);
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, cleanStringLevels(args));
        gui.close();
    }

    /**
     * cleanStringLevels return list with the levels of the game.
     *
     * @param args input from command line.
     * @return the list after cleaning.
     */
    public static List<Integer> cleanStringLevels(String[] args) {
        List<Integer> clean = new ArrayList<Integer>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1") || args[i].equals("2") || args[i].equals("3") || args[i].equals("4")) {
                clean.add(Integer.parseInt(args[i]));
            }
        }
        return clean;
    }
}


