package spaceinvaders.game;

import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;


/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 29/05/2016
 */
public class HighScoresTable implements Serializable {

    private int size;
    private List<ScoreInfo> highScores;

    /**
     * Instantiates an empty high scores table of the specified size.
     * <p>
     *
     * @param size table size
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.highScores = new LinkedList<>();
    }

    /**
     * Add a new High score to the table.
     * <p>
     * A new high score will be added only if it is higher than the last score on the table.
     *
     * @param score the score to be added
     */
    public void add(ScoreInfo score) {
        if (this.highScores.isEmpty()) {
            this.highScores.add(score);
        } else if (getRank(score.getScore()) <= this.size) {
            this.highScores.add(getRank(score.getScore()), score);
            if (this.highScores.size() > this.size) {
                this.highScores.remove(this.highScores.size() - 1);
            }
        }
    }

    /**
     * Returns the table size.
     * <p>
     *
     * @return size of table
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the current high scores from highest to lowest.
     * <p>
     *
     * @return high scores table
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * Returns the rank of a given score in the scores table.
     * <p>
     *
     * @param score the given score
     * @return the rank in the table
     */
    public int getRank(int score) {
        for (int i = 0; i < this.highScores.size(); i++) {
            if (score > this.highScores.get(i).getScore()) {
                return i;
            }
        }
        if (this.highScores.size() < this.size) {
            return this.highScores.size();
        } else {
            return this.size + 1;
        }
    }

    /**
     * Clears the high scores table.
     * <p>
     */
    public void clear() {
        this.highScores = new LinkedList<>();
    }

    /**
     * Loads a high scores table from a given file.
     * <p>
     *
     * @param filename the given file to load from
     * @throws IOException an exception.
     */
    public void load(File filename) throws IOException {
        this.clear();

        String file = String.valueOf(filename);
        ScoreInfo scoreInfo;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            for (int i = 0; i < this.size; i++) {
                scoreInfo = (ScoreInfo) objectInputStream.readObject();
                this.highScores.add(scoreInfo);
            }
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + file);
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + file);
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + file);
            }
        }
    }

    /**
     * Saves the current high scores to a given file.
     * <p>
     *
     * @param filename the given file to save to
     * @throws IOException exception.
     */
    public void save(File filename) throws IOException {
        String file = String.valueOf(filename);
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (ScoreInfo highScore : this.highScores) {
                objectOutputStream.writeObject(highScore);
            }
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + file);
            }
        }
    }

    /**
     * Reads a table from a file and returns it.
     * <p>
     * if the file does not exist, an empty table is returned
     * <p>
     *
     * @param filename the file with the table to read
     * @param size     the size.
     * @return a new High Scores Table
     */
    public static HighScoresTable loadFromFile(File filename, int size) {
        HighScoresTable hst = new HighScoresTable(size);
        try {
            hst.load(filename);
        } catch (IOException e) {
            hst.clear();
            e.printStackTrace();
        }

        return hst;
    }
}
