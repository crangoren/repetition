package Generics;

public class StatsApp {

    public static void main(String[] args) {
        Stats<Integer> statsInt = new Stats<>(1, 2, 3, 4, 5);
        Stats<Integer> statsInt1 = new Stats<>(1, 2, 3, 4, 5);
        Stats<Integer> statsInt2 = new Stats<>(1, 2, 3, 4, 5, 7, 8);
        System.out.println(statsInt.avg());

        Stats<Double> statsDouble = new Stats<>(1.0, 2.0, 3.0, 4.0, 5.0);
        System.out.println(statsDouble.avg());

        Stats<Number> statsString = new Stats<>(1, 2, 3.0, 4L, 5.0f);

        System.out.println(statsInt.sameAvg(statsInt1));
        System.out.println(statsInt.sameAvg(statsInt2));
        System.out.println(statsInt.sameAvg(statsDouble));

    }
}
