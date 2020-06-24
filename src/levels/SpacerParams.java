package levels;

import auxiliary.ColorsParser;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpacerParams {
    public String symbol;
    public int width;

    public SpacerParams() {
        this.symbol = null;
        this.width = -1;
    }

    public void parseCommaSeparatedParams(String params) {
        Pattern spaceSeparatorPattern = Pattern.compile(" ");
        String[] spaceSeparatedParams = spaceSeparatorPattern.split(params);
        for (String curParamGroup : spaceSeparatedParams) {
            Pattern colonSeparatorPattern = Pattern.compile(":");
            String[] colonSepearatedParams = colonSeparatorPattern.split(curParamGroup);
            if (colonSepearatedParams.length == 2) {
                if (colonSepearatedParams[0].equals("symbol")) {
                    this.symbol = colonSepearatedParams[1];
                } else if (colonSepearatedParams[0].equals("width")) {
                    this.width = Integer.parseInt(colonSepearatedParams[1]);
                }
            }
        }
    }
}
