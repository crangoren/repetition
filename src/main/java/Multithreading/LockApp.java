package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockApp {
    static Lock lock = new ReentrantLock();
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
           write();
           checkAndRelease();
        });
        executorService.submit(() -> {
            write();
            checkAndRelease();
        });
        executorService.submit(() -> {
            write();
            checkAndRelease();
        });
        executorService.submit(() -> {
            write();
            checkAndRelease();
        });
        executorService.submit(() -> {
            write();
            checkAndRelease();
        });
        executorService.shutdown();

    }

    static void write() {
        System.out.println("Try to get " + lock.tryLock());
        lock.lock();
        list.add("String");
    }

    static void checkAndRelease() {
        System.out.println(list.contains("String"));
        lock.unlock();
    }
}
