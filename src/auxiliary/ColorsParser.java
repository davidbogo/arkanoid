package auxiliary;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * Color from string java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
// parse color definition and return the specified color.
    public java.awt.Color colorFromString(String s) {
        Color result = Color.decode("#000000"); // Black is the default
        Pattern regex = Pattern.compile("\\((.*?)\\)");
        Matcher regexMatcher;
        if (s.contains("RGB(")) {
            int indexRGB = s.indexOf("RGB(");
            regexMatcher = regex.matcher(s.substring(indexRGB));
            if (regexMatcher.find()) {
                String comaSeparatedRGB = regexMatcher.group(1);
                Pattern separatorPattern = Pattern.compile(",");
                String[] split = separatorPattern.split(comaSeparatedRGB);
                result = new Color(
                        Integer.parseInt(split[0]),
                        Integer.parseInt(split[1]),
                        Integer.parseInt(split[2]));
            }
        } else {
            regexMatcher = regex.matcher(s);
            if (regexMatcher.find()) {
                String colorDef = regexMatcher.group(1);
                switch (colorDef) {
                    case "black":
                        result = Color.BLACK;
                        break;
                    case "blue":
                        result = Color.BLUE;
                        break;
                    case "cyan":
                        result = Color.CYAN;
                        break;
                    case "gray":
                        result = Color.DARK_GRAY;
                        break;
                    case "lightGray":
                        result = Color.LIGHT_GRAY;
                        break;
                    case "green":
                        result = Color.GREEN;
                        break;
                    case "orange":
                        result = Color.ORANGE;
                        break;
                    case "pink":
                        result = Color.PINK;
                        break;
                    case "red":
                        result = Color.RED;
                        break;
                    case "white":
                        result = Color.WHITE;
                        break;
                    case "yellow":
                        result = Color.YELLOW;
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }
}
