package miscellaneous;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * The type High scores.
 */
public class HighScores {
private int highScores;

    /**
     * Instantiates a new High scores.
     */
    public HighScores() {
        this.highScores = 0;
        try {
            File file = new File("highscores");
            if (file != null) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String highScoreLine = bufferedReader.readLine();
                if (highScoreLine != null) {
                    Pattern pattern = Pattern.compile(":");
                    String[] split;
                    if (highScoreLine.contains("The highest score so far is")) {
                        split = pattern.split(highScoreLine);
                        if (split.length == 2) {
                            this.highScores = Integer.parseInt(split[1].trim());
                        }
                    }
                }
                fileReader.close();
            }
        } catch (IOException ioException) {
            System.err.println(ioException);
        }
    }

    /**
     * Gets high score.
     *
     * @return the high score
     */
    public int getHighScore() {
        return this.highScores;
    }

    /**
     * Update high score.
     *
     * @param newScore the new score
     */
    public void updateHighScore(int newScore) {
        if (this.highScores < newScore) {
            this.highScores = newScore;
        }
    }

    /**
     * Save.
     */
    public void save() {
        try {
            File file = new File("highscores");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String val = String.valueOf(this.highScores);
            String highScoreLine = "The highest score so far is: " + val;
            bufferedWriter.write(highScoreLine);
            bufferedWriter.close();
        } catch (IOException ioException) {
            System.err.println(ioException);
        }
    }
}