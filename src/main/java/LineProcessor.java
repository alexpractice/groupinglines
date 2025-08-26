import java.util.*;
import java.util.regex.Pattern;

public class LineProcessor {

    private static final String CORRECT_WORD_REGEXP = "[\"]\\d*[\"]+(?:([;][\"]\\d*[\"])*)";
    private static final Pattern PATTERN = Pattern.compile(CORRECT_WORD_REGEXP);
    private static final String SPLITERATOR = ";";

    private static final Map<Integer, Map<String, Set<String>>> keyMap = new HashMap<>();

    public static void process(
            String line
    ) {
        var isMatch = PATTERN.matcher(line).matches();

        if (isMatch) {
            var wordsArray = line.split(SPLITERATOR);

            Set<String> foundedGroup = null;
            boolean hasOnlyEmptyWords = true;

            for (int i = 0; i < wordsArray.length; i++) {
                var currentWord = wordsArray[i];
                if (currentWord.length() > 2) {
                    hasOnlyEmptyWords = false;

                    var currentPositionMap = keyMap.get(i);
                    if (Objects.nonNull(currentPositionMap)) {
                        var currentPositionMatch = currentPositionMap.get(currentWord);
                        if (Objects.nonNull(currentPositionMatch)) {
                            foundedGroup = currentPositionMatch;
                            break;
                        }
                    }
                }
            }

            if (!hasOnlyEmptyWords) {
                if (foundedGroup != null) {
                    foundedGroup.add(line);
                } else {
                    foundedGroup = new HashSet<>();
                    foundedGroup.add(line);
                }
                for (int i = 0; i < wordsArray.length; i++) {
                    var currentWord = wordsArray[i];
                    if (currentWord.length() > 2) {
                        var currentPositionMap = keyMap.getOrDefault(i, new HashMap<>());
                        currentPositionMap.put(currentWord, foundedGroup);
                        keyMap.put(i, currentPositionMap);
                    }
                }
            }

        }
    }

    public static Map<Integer, Map<String, Set<String>>> getKeyMap() {
        return keyMap;
    }
}
