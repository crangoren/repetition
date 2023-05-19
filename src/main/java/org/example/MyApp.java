package org.example;

public class MyApp {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("3" + " " + i);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

    }
    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println(i);
            }
        }
    }
}
