package arkanoid.game;

import arkanoid.animation.AnimationRunner;
import arkanoid.animation.EndScreen;
import arkanoid.animation.HighScoresAnimation;
import arkanoid.animation.KeyPressStoppableAnimation;
import arkanoid.levels.LevelInformation;
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
    private List levels;
    private Counter lives;
    private Counter score;
    private HighScoresTable highScores;
    private File scoresFile;

    /**
     * Constructor for the game flow class.
     * <p>
     *
     * @param ar         - the animation runner.
     * @param ks         - the keyboard sensor.
     * @param gui        - the gui.
     * @param l          - the all game lives counter.
     * @param s          - the all game score counter.
     * @param highscores the high score.
     * @param levels     the levels.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, List<LevelInformation> levels,
                    Counter l, Counter s, File
                            highscores) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.levels = levels;
        this.score = s;
        this.lives = l;
        this.scoresFile = highscores;
        this.highScores = HighScoresTable.loadFromFile(scoresFile, 10);
    }

    /**
     * Constructor for the game flow class.
     * <p>
     *
     * @param ar         - the animation runner.
     * @param ks         - the keyboard sensor.
     * @param gui        - the gui.
     * @param l          - the all game lives counter.
     * @param s          - the all game score counter.
     * @param highscores the high score.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, Counter l, Counter s, File
            highscores) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.score = s;
        this.lives = l;
        this.scoresFile = highscores;
        this.highScores = HighScoresTable.loadFromFile(scoresFile, 10);
    }

    /**
     * Runs the levels.
     * <p>
     *
     * @param lvls the list of levels.
     */
    public void runLevels(List<LevelInformation> lvls) {
        // Going over each level.
        boolean win = true;
        this.lives.increase(7);
        for (LevelInformation levelInfo : lvls) {
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
        EndScreen es = new EndScreen(this.score, win);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", es));
        new Sleeper().sleepFor(1000);

        // Add score to high scores if eligible
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
        // show High Scores screen
        HighScoresAnimation hs = new HighScoresAnimation(this.highScores);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", hs));
    }

    /**
     * Returns levels.
     * <p>
     *
     * @return list of levels
     */
    public List getLevels() {
        return this.levels;
    }

    /**
     * Resets score and lives.
     * <p>
     */
    public void resetScoreAndLives() {
        this.score = new Counter();
        this.lives = new Counter();
    }

}