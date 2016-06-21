package spaceinvaders.game;

import java.io.File;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 07/04/2016
 */

public class Ass7Game {

    /**
     * Main program.
     * <p>
     * Creates a new game and runs it.
     * <p>
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        // The default.
        String input = "definitions/level_definitions.txt";
        File highscores = new File("spaceinvaders/highscores.ser");
        // If there given a run time argument.
        if (args.length > 0) {
            input = args[0];
        }
        // Run the main class.
        new Main(new HighScoresTable((int) highscores.length()), highscores, input).run();
    }

}
