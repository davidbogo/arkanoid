package levels;

import auxiliary.ColorsParser;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockParams {
    public String symbol;
    public int width;
    public int height;
    public int hitPoints;
    public String fillImage;
    public Color fillColor;
    public Color stroke;

    public BlockParams() {
        this.symbol = null;
        this.width = -1;
        this.height = -1;
        this.hitPoints = -1;
        this.fillImage = null;
        this.fillColor = null;
        this.stroke = null;
    }

    public void parseCommaSeparatedParams(String params) {
        Pattern spaceSeparatorPattern = Pattern.compile(" ");
        ColorsParser colorParser = new ColorsParser();
        String[] spaceSeparatedParams = spaceSeparatorPattern.split(params);
        for (String curParamGroup : spaceSeparatedParams) {
            Pattern colonSeparatorPattern = Pattern.compile(":");
            String[] colonSepearatedParams = colonSeparatorPattern.split(curParamGroup);
            if (colonSepearatedParams.length == 2) {
                if (colonSepearatedParams[0].equals("symbol")) {
                    this.symbol = colonSepearatedParams[1];
                } else if (colonSepearatedParams[0].equals("width")) {
                    this.width = Integer.parseInt(colonSepearatedParams[1]);
                } else if (colonSepearatedParams[0].equals("height")) {
                    this.height = Integer.parseInt(colonSepearatedParams[1]);
                } else if (colonSepearatedParams[0].equals("hit_points")) {
                    this.hitPoints = Integer.parseInt(colonSepearatedParams[1]);
                } else if (colonSepearatedParams[0].equals("stroke")) {
                    this.stroke = colorParser.colorFromString(colonSepearatedParams[1]);
                } else if (colonSepearatedParams[0].equals("fill")) {
                    if (colonSepearatedParams[1].contains("image")) {
                        Pattern regex = Pattern.compile("\\((.*?)\\)");
                        Matcher regexMatcher = regex.matcher(colonSepearatedParams[1]);
                        if (regexMatcher.find()) {
                            this.fillImage = regexMatcher.group(1);
                        }
                    } else if (colonSepearatedParams[1].contains("color")) {
                        this.fillColor = colorParser.colorFromString(colonSepearatedParams[1]);
                    }
                }
            }
        }
    }

    public boolean completeAndValidate(BlockParams defaultParams) {
        if (this.symbol == null) {
            return false;
        }
        if (this.width < 1) {
            if (defaultParams.width < 1) {
                return false;
            }
            this.width = defaultParams.width;
        }
        if (this.height < 1) {
            if (defaultParams.height < 1) {
                return false;
            }
            this.height = defaultParams.height;
        }
        if (this.stroke == null) {
            this.stroke = defaultParams.stroke;
            // Stroke is an optional parameter. It's OK if it's null
        }
        if ((this.fillImage == null) && (this.fillColor == null)) {
            if ((defaultParams.fillImage == null) && (defaultParams.fillColor == null)) {
                return false;
            }
            this.fillImage = defaultParams.fillImage;
            this.fillColor = defaultParams.fillColor;
        }
        if (this.hitPoints < 1) {
            if (defaultParams.hitPoints < 1) {
                this.hitPoints = 1; // We treat hitPoints as an optional parameter.
                                    // If it's not supplied, we'll just use 1
            } else {
                this.hitPoints = defaultParams.hitPoints;
            }
        }
        return true;
    }
}
