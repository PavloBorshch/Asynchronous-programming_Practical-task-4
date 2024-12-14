package task1;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        List<String> filePaths = List.of(
                "src\\task1\\file1.txt",
                "src\\task1\\file2.txt",
                "src\\task1\\file3.txt");

        long startTime = System.currentTimeMillis();

        // Завантаження тексту з файлів
        CompletableFuture<List<String>> textLoadingFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Loading text from files...");
            long taskStartTime = System.currentTimeMillis();
            List<String> sentences = FileLoader.loadTextFromFiles(filePaths);

            if (sentences.isEmpty()) {
                System.err.println("No sentences found in the files.");
                System.exit(1); // Завершення програми, якщо файли порожні
            }

            System.out.println("Loaded sentences: " + sentences); // Додано для відладки
            System.out.printf("Text loading completed in %d ms\n", System.currentTimeMillis() - taskStartTime);
            return sentences;
        });

        // Обробка тексту
        CompletableFuture<List<String>> processedTextFuture = textLoadingFuture.thenApplyAsync(sentences -> {
            System.out.println("Processing text...");
            long taskStartTime = System.currentTimeMillis();
            List<String> processed = TextProcessor.removeLetters(sentences);

            System.out.println("Processed sentences: " + processed); // Додано для відладки
            System.out.printf("Text processing completed in %d ms\n", System.currentTimeMillis() - taskStartTime);
            return processed;
        });

        // Виведення результату
        processedTextFuture.thenAcceptAsync(processed -> {
            System.out.println("Printing results...");
            long taskStartTime = System.currentTimeMillis();

            if (processed.isEmpty()) {
                System.err.println("No processed sentences available.");
            } else {
                printResults(processed);
            }

            System.out.printf("Result printing completed in %d ms\n", System.currentTimeMillis() - taskStartTime);
        }).thenRunAsync(() -> {
            System.out.printf("All tasks completed in %d ms\n", System.currentTimeMillis() - startTime);
        });

        processedTextFuture.join();
    }

    public static void printResults(List<String> processed) {
        System.out.println("Processed Sentences:");
        for (String line : processed) {
            System.out.println(line.isBlank() ? "<empty>" : line);
        }
    }
}
