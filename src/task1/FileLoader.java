package task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    public static List<String> loadTextFromFiles(List<String> filePaths) {
        List<String> sentences = new ArrayList<>();
        for (String filePath : filePaths) {
            Path path = Paths.get(filePath);

            // Перевірка на існування файлу
            if (!Files.exists(path)) {
                System.err.printf("File not found: %s\n", filePath);
                System.exit(1); // Завершення програми з кодом помилки
            }

            try {
                sentences.addAll(Files.readAllLines(path));
            } catch (IOException e) {
                System.err.printf("Failed to read file %s: %s\n", filePath, e.getMessage());
                System.exit(1); // Завершення програми з кодом помилки
            }
        }
        return sentences;
    }
}
