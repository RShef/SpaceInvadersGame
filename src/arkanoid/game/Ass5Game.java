package arkanoid.game;

import arkanoid.Counter;
import arkanoid.animation.AnimationRunner;
import arkanoid.levels.GameFlow;
import arkanoid.levels.Level1;
import arkanoid.levels.Level2;
import arkanoid.levels.Level3;
import arkanoid.levels.Level4;
import arkanoid.levels.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 07/04/2016
 */

public class Ass5Game {

    /**
     * Main program.
     * <p/>
     * Creates a new game and runs it.
     * <p/>
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(60, gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        ArrayList<LevelInformation> levels = new ArrayList<>();
        // Adding levels class to the array.

        if (args.length == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }

        // Creating a game.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                levels.add(new Level1());
            }
            if (args[i].equals("2")) {
                levels.add(new Level2());
            }
            if (args[i].equals("3")) {
                levels.add(new Level3());
            }
            if (args[i].equals("4")) {
                levels.add(new Level4());
            }
        }
        GameFlow g = new GameFlow(ar, ks, gui, new Counter(), new Counter());
        g.runLevels(levels);
    }

}
