package task2;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class RandomSequenceGenerator {
    public static List<Double> generateSequence(int size) {
        Random random = new Random();
        return DoubleStream.generate(random::nextDouble)
                .limit(size)
                .map(d -> Math.round(d * 1000.0) / 10.0) // округлення для зручності
                .boxed()
                .collect(Collectors.toList());
    }
}
