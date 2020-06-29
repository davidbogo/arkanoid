package miscellaneous;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

public class HighScores {
private int highScores;
    public HighScores() {
        this.highScores = 0;
        try {
            File file = new File("highscores");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String highScoreLine = bufferedReader.readLine();
            if (highScoreLine != null) {
                Pattern pattern = Pattern.compile(":");
                String[] split;
                if (highScoreLine.contains("The highest score so far is")) {
                    split = pattern.split(highScoreLine);
                    if (split.length == 2) {
                        this.highScores = Integer.parseInt(split[1]);
                    }
                }
            }
        } catch (IOException ioException) {
            System.err.println(ioException);
        }
    }

    public int getHighScore() {
        return this.highScores;
    }

    public void updateHighScore(int newScore) {
        if (this.highScores < newScore) {
            this.highScores = newScore;
        }
    }

    public void save() {
        try {
            File file = new File("highscores");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String val = String.valueOf(this.highScores);
            String highScoreLine = "The highest score so far is: " + val;
            bufferedWriter.write(highScoreLine);
        } catch (IOException ioException) {
            System.err.println(ioException);
        }
    }
}