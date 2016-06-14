package arkanoid.game;

import java.io.File;
import java.util.List;

import biuoop.KeyboardSensor;
import arkanoid.animation.AnimationRunner;
import arkanoid.levels.LevelInformation;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 11/06/2016
 */

/**
 * The game task.
 *
 * @param <T> the task.
 */
public class TaskGame<T> implements Task<T> {
    private HighScoresTable scores;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private File file;
    private List<LevelInformation> levelInfoList;

    /**
     * TaskGame().
     *
     * @param table - HighScoresTable.
     * @param ar    - AnimationRunner.
     * @param ks    - KeyboardSensor.
     * @param file  - File.
     * @param lil   - List<LevelInformation>.
     */
    public TaskGame(HighScoresTable table, AnimationRunner ar,
                    KeyboardSensor ks, File file, List<LevelInformation> lil) {
        this.scores = table;
        this.ar = ar;
        this.ks = ks;
        this.file = file;
        this.levelInfoList = lil;
    }

    @Override
    public T run() {
        Counter lives = new Counter();
        GameFlow game = new GameFlow(this.ar, this.ks, this.ar.getGui(), this.levelInfoList,
                lives, new Counter(), this.file);
        game.runLevels(levelInfoList);
        return null;
    }
}
