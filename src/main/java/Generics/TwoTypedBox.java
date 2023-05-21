package Generics;

public class TwoTypedBox <T, U>{
    private final T first;
    private final U second;

    public TwoTypedBox(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void showTypes() {
        System.out.println("type of T " + first.getClass().getName());
        System.out.println("type of U " + second.getClass().getName());

    }
}
