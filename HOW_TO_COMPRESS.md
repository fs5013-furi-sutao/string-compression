##  文字列の圧縮

以下に [StringsCompressor.java](./src/StringsCompressor.java) で行った文字列の圧縮ロジックや実装を説明します

### main メソッド

``` java
public static void main(String[] args) {
    // 課題 1. 文字列圧縮
    String text = encode("AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG");
    System.out.println(text);
}
```

#### 実行結果

``` console
A5B10CD9E2F5G
```

### encode メソッド

文字列を求める形態に圧縮する

``` java
private static String encode(String str) {
    List<String> continuousSameCharsList = separateByContinuousSameChar(
            str);
    return convertToFinalShapeForCompressing(continuousSameCharsList);
}
```

## separateToListByContinuousSameChar メソッド

連続する文字の連なりごとにリストに分割する

``` java 
private static final String STOPPER = "*";

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
```

### convertToFinalShapeForCompressing メソッド

連続する同じ文字の連なりごとのリストを、求める圧縮形態の文字列に変換する

``` java
private static Stringc convertToFinalShapeForCompressing(
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
```
