package Multithreading;

import java.util.concurrent.CountDownLatch;

public class SimpleCDL {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 5;
        CountDownLatch cdl = new CountDownLatch(5);
        System.out.println("Begin");
        for (int i = 0; i < threadCount; i++) {
            int count = i;
            new Thread(() -> {
                try {

                    Thread.sleep(500 + 200 * count);
                    cdl.countDown();
                    System.out.println("Thread " + count + " is ready");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                }
            }).start();
        }
        cdl.await();

        System.out.println("Completed");
    }
}
