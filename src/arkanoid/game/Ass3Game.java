package arkanoid.game;
import arkanoid.levels.Level1;
import arkanoid.levels.Level2;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 07/04/2016
 */
public class Ass3Game {

    /**
     * Main program.
     * <p>
     * Creates a new game and runs it.
     * <p>
     * @param args arguments.
     */
    public static void main(String[] args) {

        GameLevel game = new GameLevel(new Level1());
        game.initialize();
        game.run();
    }

}
