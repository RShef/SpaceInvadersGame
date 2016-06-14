package arkanoid.game;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 11/06/2016
 */

import arkanoid.animation.MenuAnimation;

import arkanoid.levels.LevelInformation;
import arkanoid.levels.SetFileFormat;

import arkanoid.levels.LevelSpecificationReader;

import java.io.BufferedReader;

import arkanoid.levels.LevelsReaderForSub;
import arkanoid.animation.AnimationRunner;

import arkanoid.animation.HighScoresAnimation;

import java.io.InputStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import arkanoid.animation.KeyPressStoppableAnimation;

import java.util.List;

import java.util.ArrayList;

import biuoop.KeyboardSensor;

import biuoop.GUI;

/**
 * The main class.
 */
public class Main {
    private HighScoresTable scores;
    private File file;
    private String levelPath;

    /**
     * Constructor.
     *
     * @param scores - HighScoresTable.
     * @param file   - File.
     * @param param  - String.
     */
    public Main(HighScoresTable scores, File file, String param) {
        this.scores = scores;
        this.file = file;
        this.levelPath = param;
    }

    /**
     * main for running the game.
     */
    public void run() {
        this.scores = HighScoresTable.loadFromFile(file, (int) file.length());
        if (this.scores == null) {
            try {
                this.scores.save(file);
            } catch (IOException e) {
                return;
            }
        }
        // Settings.
        final HighScoresTable finalScores = this.scores;
        final GUI gui = new GUI("Arkanoid", 800, 600);
        LevelsReaderForSub lr = new LevelsReaderForSub();
        List<SetFileFormat> sffList = new ArrayList<SetFileFormat>();
        final String path = this.levelPath;
        BufferedReader fr = null;
        List<LevelInformation> levelInformationList = null;
        // Loading the file.
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            fr = new BufferedReader(new InputStreamReader(is));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // If the file is  given.
        if (path.equals("definitions/levelset.txt")) {
            // Interpreting the file given.
            sffList = lr.readLevels(fr);
        }
        // In case the file is not given.
        else {
            levelInformationList = new LevelSpecificationReader().fromReader(fr);
        }

        final AnimationRunner animationRunner = new AnimationRunner(60, gui);
        // Tasks for the 's' option.
        MenuAnimation<Task<Void>> subMenu =
                new MenuAnimation<Task<Void>>(gui.getKeyboardSensor(), "Levels", animationRunner);
        for (int i = 0; i < sffList.size(); i++) {
            subMenu.addSelection(
                    sffList.get(i).getKey(),
                    sffList.get(i).getName(),
                    new TaskGame<Void>(finalScores, animationRunner, gui
                            .getKeyboardSensor(), this.file, sffList.get(i)
                            .getLevelList()), null);
        }

        MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>(gui.getKeyboardSensor(), "Arkanoid",
                animationRunner);
        if (!this.levelPath.equals("definitions/levelset.txt")) {
            subMenu = null;
        }
        // Task if the 'h' option.
        Task<Void> highScores = new Task<Void>() {
            // private KeyPressStoppableAnimation animation = scoresTable;
            private AnimationRunner anRunner = animationRunner;

            public Void run() {
                this.anRunner.run(new KeyPressStoppableAnimation(gui
                        .getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(finalScores)));
                return null;
            }
        };
        // Task for the 'q' option.
        Task<Void> quit = new Task<Void>() {
            public Void run() {
                System.exit(1);
                return null;
            }
        };

        // Adding the sub menus.
        menu.addSubMenu("s", "(s) start game", new TaskGame<>(this.scores, animationRunner, gui.getKeyboardSensor(),
                this.file, levelInformationList), subMenu);
        menu.addSelection("h", "(h) High scores", highScores, null);
        menu.addSelection("q", "(q) Quit", quit, null);
        Task<Void> task;
        // Running the animation.
        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            task = menu.getStatus();
            if (task != null) {
                task.run();
            }
        }
    }
}