package Multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierApp {
    public static void main(String[] args) {
        int threadCount = 5;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);
        for (int i = 0; i < threadCount; i++) {
            int count = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + count + " is getting ready");
                    Thread.sleep(2000 + 500 * count);
                    System.out.println("Thread " + count + " is ready");
                    cyclicBarrier.await();
                    System.out.println("Thread " + count + " is running");
                    Thread.sleep(2000 + 500 * count);
                    System.out.println("Thread " + count + " is finished");
                    cyclicBarrier.await();
                    System.out.println("All finished");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
