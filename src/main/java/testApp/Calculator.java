package testApp;

public class Calculator {
    static int a;
    static int b;

    public static void main(String[] args) {
        a= 10;
        b = 5;

        sum(a, b);
        sub(a, b);
        multiplication(a, b);
        div(a, b);
    }

    public static int sum(int a, int b) {
        System.out.println(a+b);
      return a+b;

    }
    public static int sub (int a, int b) {
        System.out.println(a-b);
        return a-b;
    }

    public static int multiplication (int a, int b) {
        System.out.println(a*b);
        return a*b;
    }

    public static int div (int a, int b) {
        System.out.println(a/b);
        return a/b;
    }






}
