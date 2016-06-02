package arkanoid.game;

import arkanoid.animation.*;
import arkanoid.levels.Level1;
import arkanoid.levels.Level2;
import arkanoid.levels.Level3;
import arkanoid.levels.Level4;
import arkanoid.levels.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 07/04/2016
 */

public class Ass6Game {

    /**
     * Main program.
     * <p>
     * Creates a new game and runs it.
     * <p>
     * @param args arguments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(60, gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        ArrayList<LevelInformation> levels = new ArrayList<>();
        File highscores = new File("src/highscores.ser");

        // Adding levels class to the array.

        if (args.length == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }

        // Creating a game.
        for (String arg : args) {
            if (arg.equals("1")) {
                levels.add(new Level1());
            }
            if (arg.equals("2")) {
                levels.add(new Level2());
            }
            if (arg.equals("3")) {
                levels.add(new Level3());
            }
            if (arg.equals("4")) {
                levels.add(new Level4());
            }
        }
        GameFlow g = new GameFlow(ar, ks, gui, levels, new Counter(), new Counter(), highscores);
        g.showMenu();
    }

}
