package arkanoid.game;

import java.io.File;
import java.io.IOException;

/**
 * @author Roey Shefi & Oded Thaller
 * @version 1.0
 * @since 30/05/2016
 */

public class HighScoreTester {

    public static void main(String[] args) {
        HighScoresTable hs = new HighScoresTable(10);

        ScoreInfo s1 = new ScoreInfo("2nd", 90);
        ScoreInfo s2 = new ScoreInfo("1st", 100);
        ScoreInfo s3 = new ScoreInfo("4th", 80);
        ScoreInfo s4 = new ScoreInfo("5th", 70);
        ScoreInfo s5 = new ScoreInfo("3rd", 85);
        ScoreInfo s6 = new ScoreInfo("9th", 45);
        ScoreInfo s7 = new ScoreInfo("7th", 55);
        ScoreInfo s8 = new ScoreInfo("8th", 50);
        ScoreInfo s9 = new ScoreInfo("6th", 60);
        ScoreInfo s10 = new ScoreInfo("10th", 30);

        hs.add(s1);
        hs.add(s2);
        hs.add(s3);
        hs.add(s4);
        hs.add(s5);
        hs.add(s6);
        hs.add(s7);
        hs.add(s8);
        hs.add(s9);
        hs.add(s10);

        System.out.println(hs);

        File highScores = new File("High-Scores.ser");
        try {
            hs.save(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            hs.load(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
