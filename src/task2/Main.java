package task2;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        // Генерація послідовності
        CompletableFuture<List<Double>> sequenceFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Generating sequence...");
            return RandomSequenceGenerator.generateSequence(20);
        });

        // Обчислення максимальної різниці, по модулю
        CompletableFuture<Double> resultFuture = sequenceFuture.thenApplyAsync(sequence -> {
            System.out.println("Calculating max absolute difference...");
            return SequenceCalculator.calculateMaxDifference(sequence);
        });

        // Відображення послідовності
        CompletableFuture<Void> displaySequenceFuture = sequenceFuture.thenAcceptAsync(sequence -> {
            System.out.println("Sequence: " + sequence);
        });

        // Відображення результату
        CompletableFuture<Void> displayResultFuture = resultFuture.thenAcceptAsync(result -> {
            System.out.println("Result: " + result);
        });

        // Відображення загального часу виконання
        CompletableFuture<Void> finalAction = resultFuture.thenRunAsync(() -> {
            long endTime = System.currentTimeMillis();
            System.out.println("\nAll tasks completed in " + (endTime - startTime) + " ms.");
        });

        // Очікування завершення всіх задач
        CompletableFuture.allOf(displaySequenceFuture, displayResultFuture, finalAction).join();
    }
}
