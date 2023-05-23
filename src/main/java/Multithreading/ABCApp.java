package Multithreading;

public class ABCApp {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Object locker = new Object();

        Thread printA = new Thread(() -> {
            synchronized (locker) {
                if (sb.isEmpty()) {
                    sb.append("A");
                    System.out.println(sb);
                }
                while (sb.length()<15) {
                    if (sb.charAt(sb.length() - 1) == 'C') {
                        sb.append('A');
                        locker.notifyAll();
                        System.out.println(sb);
                    } else {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        });

        Thread printB = new Thread(() -> {
                synchronized (locker) {
                    while (sb.length()<15) {
                    if (sb.charAt(sb.length() - 1) == 'A') {
                        sb.append('B');
                        locker.notifyAll();
                        System.out.println(sb);
                    } else {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread printC = new Thread(() -> {
                synchronized (locker) {
                    while (sb.length()<15) {
                    if (sb.charAt(sb.length() - 1) == 'B') {
                        sb.append('C');
                        locker.notifyAll();
                        System.out.println(sb);
                    } else {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });

        printA.start();
        printB.start();
        printC.start();

    }




}
