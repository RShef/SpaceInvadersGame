package arkanoid.game;

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
        Game game = new Game();
        game.initialize();
        game.run();
    }

}
