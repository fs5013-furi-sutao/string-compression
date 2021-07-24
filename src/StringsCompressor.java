import java.util.ArrayList;
import java.util.List;

public class StringsCompressor {
    private static final String STOPPER = "*";
    private static final String BLANK = "";

    private static final int TOP_POSITION = 0;
    private static final int LENGTH_OF_ONE_CHAR = 1;

    public static void main(String[] args) {
        // 課題 1. 文字列圧縮
        String text = encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG");
        System.out.println(text);
    }

    private static String encode(String str) {
        List<String> continuousSameCharsList = separateToListByContinuousSameChar(
                str);
        return convertToFinalShapeForCompressing(continuousSameCharsList);
    }

    private static List<String> separateToListByContinuousSameChar(String str) {
        str += STOPPER;
        List<String> continousSameCharsList = new ArrayList<>();

        while (!isEnd(str)) {
            String[] chars = splitEveryOneCharArray(str);
            String headChar = extractHeadChar(str);
            String continuousSameChars = BLANK;

            for (int j = 0; j < str.length(); j++) {
                int currentPosition = j;
                String currentChar = chars[currentPosition];

                if (!isEquals(headChar, currentChar)) {
                    continuousSameChars = cutOutFromTopToSpecifiedPosition(str,
                            currentPosition);

                    continousSameCharsList.add(continuousSameChars);

                    String leftOvers = str.substring(currentPosition,
                            str.length());
                    str = leftOvers;
                    break;
                }
            }
        }
        return continousSameCharsList;
    }

    private static boolean isEnd(String str) {
        return str.equals(STOPPER);
    }

    private static String[] splitEveryOneCharArray(String str) {
        return str.split(BLANK);
    }

    private static String extractHeadChar(String str) {
        return str.substring(0, 1);
    }

    private static boolean isEquals(String headChar, String currentChar) {
        return headChar.equals(currentChar);
    }

    private static String cutOutFromTopToSpecifiedPosition(String str,
            int specifiedPosition) {
        return str.substring(TOP_POSITION, specifiedPosition);
    }

    private static String convertToFinalShapeForCompressing(
            List<String> continuousSameCharsList) {
        StringBuilder sb = new StringBuilder();

        for (String continuousSameChars : continuousSameCharsList) {
            String headChar = extractHeadChar(continuousSameChars);
            String numStrOfContinuousChars = BLANK;

            if (!isCharsLengthOne(continuousSameChars)) {
                numStrOfContinuousChars = String
                        .valueOf(continuousSameChars.length());
            }
            sb.append(buildFinalShapeForCompressing(headChar,
                    numStrOfContinuousChars));
        }
        return sb.toString();
    }

    private static boolean isCharsLengthOne(String chars) {
        return chars.length() == LENGTH_OF_ONE_CHAR;
    }

    private static String buildFinalShapeForCompressing(String headChar,
            String numStrOfChars) {
        return headChar + String.valueOf(numStrOfChars);
    }
}
