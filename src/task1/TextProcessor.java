package task1;

import java.util.List;
import java.util.stream.Collectors;

public class TextProcessor {
    public static List<String> removeLetters(List<String> sentences) {
        if (sentences == null || sentences.isEmpty()) {
            throw new IllegalArgumentException("Input sentences cannot be null or empty.");
        }
        return sentences.stream()
                .map(sentence -> {
                    if (sentence == null) {
                        return "";
                    }
                    return sentence.replaceAll("[a-zA-Z]", ""); // Видаляємо букви
                })
                .collect(Collectors.toList());
    }
}
