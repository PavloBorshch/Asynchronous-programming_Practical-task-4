package task2;

import java.util.List;

public class SequenceCalculator {
    public static double calculateMaxDifference(List<Double> sequence) {
        if (sequence.size() < 2) {
            return 0.0;
        }

        double maxDifference = 0.0;
        for (int i = 0; i < sequence.size() - 1; i++) {
            double difference = Math.abs(sequence.get(i) - sequence.get(i + 1));
            maxDifference = Math.max(maxDifference, difference);
        }

        return maxDifference;
    }
}

