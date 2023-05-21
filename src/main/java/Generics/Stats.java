package Generics;

import java.util.Arrays;
import java.util.List;

public class Stats <T extends Number>{
    private final T[] numbers;
    public static final double EPSILON = 0.00001;

    public Stats(T... numbers) {
        this.numbers = numbers;
    }

    public T[] getNumbers() {
        return numbers;
    }

    double avg() {
        double sum = 0.0;
        for (int i = 0; i < numbers.length; i++) {
            sum+= numbers[i].doubleValue();
        }
        return sum/ numbers.length;
    }

    public boolean sameAvg(Stats<? extends  Number> another) {
        return Math.abs(this.avg()- another.avg()) < EPSILON;
    }

    public List<?> getElements() {
        return Arrays.asList(numbers);
    }
}
