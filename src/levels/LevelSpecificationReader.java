package levels;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class LevelSpecificationReader {
    private int horizontalBound;
    private int verticalBound;

    public LevelSpecificationReader(int horBound, int vertBound) {
        this.horizontalBound = horBound;
        this.verticalBound = vertBound;
    }

    public List<LevelInformation> fromReader(java.io.Reader reader) throws java.io.IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<LevelInformation> listOfLevels = new ArrayList<LevelInformation>();
        SingleLevelProcessor curLevelProcessor = null;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (!line.startsWith("#")) {
                if (curLevelProcessor == null) {
                    if (line.contains("START_LEVEL")) {
                        curLevelProcessor = new SingleLevelProcessor(this.horizontalBound, this.verticalBound);
                    }
                } else {
                    if (line.contains("END_LEVEL")) {
                        LevelInformation levelInfo = curLevelProcessor.buildLevelInfo();
                        if (levelInfo != null) {
                            listOfLevels.add(levelInfo);
                        }
                        curLevelProcessor = null;
                    } else {
                        curLevelProcessor.addLine(line);
                    }
                }
            }
        }
        return listOfLevels;
    }
}