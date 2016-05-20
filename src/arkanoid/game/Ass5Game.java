package arkanoid.game;

import arkanoid.levels.LevelInformation;
import biuoop.GUI;
import arkanoid.Counter;
import arkanoid.animation.AnimationRunner;
import arkanoid.levels.GameFlow;
import arkanoid.levels.Level1;
import arkanoid.levels.Level2;
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
        levels.add(new Level1());
        levels.add(new Level2());
        GameFlow g = new GameFlow(ar, ks, gui, new Counter(), new Counter());
        g.runLevels(levels);
    }

}