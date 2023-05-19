package org.example;

public class WaitApp {
    private final Object lock = new Object();
    String current = "A";
    public static void main(String[] args) {

        WaitApp printApp = new WaitApp();
        Thread t1 = new Thread(() -> printApp.printA());
        Thread t2 = new Thread(() -> printApp.printB());
        t1.start();
        t2.start();
    }
    public void printA(){
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                System.out.println("A");
            }
        }
    }

    public void printB() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                System.out.println("B");
            }
        }
    }

}
