package arkanoid.game;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 29/05/2016
 */
class HighScoresTable {

    private int size;
    private List<ScoreInfo> highScores;

    /**
     * Instantiates an empty high scores table of the specified size.
     * <p>
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
     * @param score the score to be added
     */
    public void add(ScoreInfo score) {
        if (this.highScores.isEmpty()) {
            this.highScores.add(score);
        } else if (getRank(score.getScore()) <= this.size) {
          this.highScores.add(getRank(score.getScore()), score);
            if (this.highScores.size() > this.size) {
                this.highScores.remove(this.highScores.size()-1);
            }
        }
    }

    /**
     * Returns the table size.
     * <p>
     * @return size of table
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the current high scores from highest to lowest.
     * <p>
     * @return high scores table
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * Returns the rank of a given score in the scores table.
     * <p>
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
     * @param filename the given file to load from
     * @throws IOException
     */
    public void load(File filename) throws IOException {

    }

    /**
     * Saves the current high scores to a given file.
     * <p>
     * @param filename the given file to save to
     * @throws IOException
     */
    public void save(File filename) throws IOException {

    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    /*public static HighScoresTable loadFromFile(File filename) {
    }*/

    @Override
    public String toString() {
        for (int i = 0; i < this.highScores.size(); i++) {
            String name = this.highScores.get(i).getName();
            int score = this.highScores.get(i).getScore();
            System.out.println(i+1 + ". " + name + "       " + score);
        }
        return "DONE";
    }
}

/*
    PrintWriter os = null;
try {
        os = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.scoresTable)));
        os.printf(score.getName());
        os.println(score.getScore());
        } catch (IOException e) {
        e.printStackTrace();
        } finally {
        if (os != null) {
        os.close();
        }
        }*/
