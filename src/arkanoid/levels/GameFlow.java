package arkanoid.levels;


import arkanoid.Counter;
import arkanoid.game.*;
import arkanoid.sprites.EndScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 19/05/2016
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Counter lives;
    private Counter score;

    /**
     * Constructor for the game flow class.
     *
     * @param ar  - the animation runner.
     * @param ks  - the keyboard sensor.
     * @param gui - the gui.
     * @param l   - the all game lives counter.
     * @param s   - the all game score counter.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, Counter l, Counter s) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.score = s;
        this.lives = l;
        l.increase(1);
    }

    /**
     * Runs the levels.
     *
     * @param levels the list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // Going over each level.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner,
                    this.keyboardSensor, this.gui, this.lives, this.score);
            level.initialize();

            while (level.livesLeft() != 0 && level.blocksLeft() != 0) {
                level.playOneTurn();
            }

            if (level.livesLeft() == 0) {
                break;
            }

        }
        new EndScreen().drawOn(this.gui.getDrawSurface());
        new Sleeper().sleepFor(2000);
        this.gui.close();

    }
}