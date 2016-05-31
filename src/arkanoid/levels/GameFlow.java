package arkanoid.levels;

import arkanoid.animation.HighScoresAnimation;
import arkanoid.game.Counter;
import arkanoid.animation.AnimationRunner;
import arkanoid.game.GameLevel;
import arkanoid.animation.EndScreen;
import arkanoid.game.HighScoresTable;
import arkanoid.game.ScoreInfo;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.io.File;
import java.io.IOException;
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
    private HighScoresTable highScores;
    private File scoresFile;

    /**
     * Constructor for the game flow class.
     * <p>
     *
     * @param ar  - the animation runner.
     * @param ks  - the keyboard sensor.
     * @param gui - the gui.
     * @param l   - the all game lives counter.
     * @param s   - the all game score counter.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, Counter l, Counter s, File highscores) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.score = s;
        this.lives = l;
        this.scoresFile = highscores;
        this.highScores = HighScoresTable.loadFromFile(scoresFile, 10);
        l.increase(7);
    }

    /**
     * Runs the levels.
     * <p>
     *
     * @param levels the list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // Going over each level.
        boolean win = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner,
                    this.keyboardSensor, this.gui, this.lives, this.score);
            level.initialize();

            while (level.livesLeft() != 0 && level.blocksLeft() != 0) {
                level.playOneTurn();
            }

            if (level.livesLeft() == 0) {
                win = false;
                break;
            }

        }
        // Show end screen
        this.animationRunner.run(new EndScreen(this.keyboardSensor, this.score, win));
        new Sleeper().sleepFor(1000);
        if (this.highScores.getRank(this.score.getValue()) < this.highScores.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.highScores.add(new ScoreInfo(name, this.score.getValue()));
            try {
                this.highScores.save(this.scoresFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.animationRunner.run(new HighScoresAnimation(this.highScores, this.keyboardSensor));
        this.gui.close();
    }

}