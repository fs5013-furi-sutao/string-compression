##  圧縮された文字列の展開

以下に [StringsExpander.java](./src/StringsExpander.java) で行った、[StringsCompressor.java](./src/StringsCompressor.java) にて圧縮された文字列の展開ロジックや実装を説明します

### main メソッド

``` java
public static void main(String[] args) {
    // 課題 2. 圧縮された文字列展開
    String text = decode("A5B10CD9E2F5G");
    System.out.println(text);
}
```

#### 実行結果

``` console
AAAAABBBBBBBBBBCDDDDDDDDDEEFFFFFG
```

### decode メソッド

[StringsCompressor.java](./src/StringsCompressor.java) の処理で圧縮された文字列を展開する

``` java
private static String decode(String str) {
    List<String> shapeOfCompressingList = separateByShapeOfCompressing(str);
    for (String shapeOfCompressing : shapeOfCompressingList) {
        System.out.println(shapeOfCompressing);
    }
    return convertToFinalShapeForExpanding(shapeOfCompressingList);
}
```

## separateToListByOneUnitOfCompressing メソッド

１ユニットの圧縮形態ごとにリストに分割する

``` java 
private static final String STOPPER = "*";

private static List<String> separateToListByOneUnitOfCompressing(String str) {
    str += STOPPER;
    List<String> oneUnitOfCompressingList = new ArrayList<>();

    while (!isEnd(str)) {
        String oneUnitOfCompressingList = BLANK;
        String[] chars = splitEveryOneCharArray(str);
        String headChar = extractHeadChar(str);

        for (int j = 1; j < str.length(); j++) {
            int currentPosition = j;
            String currentChar = chars[currentPosition];

            if (!isNum(currentChar)) {
                String partOfnum = str.substring(1, j);
                shapeOfCompressing = headChar + partOfnum;
                oneUnitOfCompressingList.add(oneUnitOfCompressing);
                String leftOvers = str.substring(currentPosition,
                        str.length());
                str = leftOvers;
                break;
            }
        }
    }
    return shapeOfCompressingList;
}
```

### convertToFinalShapeForExpanding メソッド

圧縮される前の状態の文字列に変換する

``` java
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
```
