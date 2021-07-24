import java.util.ArrayList;
import java.util.List;

public class StringsExpander {

    private static final String STOPPER = "*";
    private static final String BLANK = "";

    public static void main(String[] args) {
        // 課題 2. 文字列展開
        String text = decode("A5B10CD9E2F5G");
        System.out.println(text);
    }

    private static String decode(String str) {
        List<String> shapeOfCompressingList = separateToListByOneUnitOfCompressing(
                str);

        for (String shapeOfCompressing : shapeOfCompressingList) {
            System.out.println(shapeOfCompressing);
        }
        return convertToFinalShapeForExpanding(shapeOfCompressingList);
    }

    private static List<String> separateToListByOneUnitOfCompressing(
            String str) {
        str += STOPPER;
        List<String> oneUnitOfCompressingList = new ArrayList<>();

        while (!isEnd(str)) {
            String oneUnitOfCompressing = BLANK;
            String[] chars = splitEveryOneCharArray(str);
            String headChar = extractHeadChar(str);

            for (int j = 1; j < str.length(); j++) {
                int currentPosition = j;
                String currentChar = chars[currentPosition];

                if (!isNum(currentChar)) {
                    String partOfnum = str.substring(1, j);
                    oneUnitOfCompressing = headChar + partOfnum;
                    oneUnitOfCompressingList.add(oneUnitOfCompressing);
                    String leftOvers = str.substring(currentPosition,
                            str.length());
                    str = leftOvers;
                    break;
                }

            }
        }

        return oneUnitOfCompressingList;
    }

    private static boolean isEnd(String str) {
        return str.equals(STOPPER);
    }

    private static boolean isNum(String str) {
        try {
            parseToInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static String[] splitEveryOneCharArray(String str) {
        return str.split(BLANK);
    }

    private static String extractHeadChar(String str) {
        return str.substring(0, 1);
    }

    private static int parseToInt(String str) {
        return Integer.parseInt(str);
    }

    private static String convertToFinalShapeForExpanding(
            List<String> oneUnitOfCompressingList) {
        StringBuilder sb = new StringBuilder();

        for (String oneUnitOfCompressing : oneUnitOfCompressingList) {
            String headChar = extractHeadChar(oneUnitOfCompressing);
            int partOfNum = extractPartOfNum(oneUnitOfCompressing);

            for (int i = 1; i <= partOfNum; i++) {
                sb.append(headChar);
            }
        }
        return sb.toString();
    }

    private static int extractPartOfNum(String shapeOfCompressing) {
        String partOfNumStr = shapeOfCompressing.substring(1,
                shapeOfCompressing.length());

        if (BLANK.equals(partOfNumStr)) {
            return 1;
        }
        return parseToInt(partOfNumStr);
    }
}
