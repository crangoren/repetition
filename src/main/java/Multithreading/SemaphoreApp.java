package Multithreading;

import java.sql.SQLOutput;
import java.util.concurrent.Semaphore;

public class SemaphoreApp {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 10; i++) {
            int number = i;

            new Thread(() -> {
            try{
                System.out.println("Thread " + number + " " + "in front of semaphore");
                semaphore.acquire();
                System.out.println("Thread " + number + " " + "after semaphore");
                Thread.sleep(500);
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread " + number + " " + "released semaphore");
                semaphore.release();
            }
            }).start();
        }
    }



}
