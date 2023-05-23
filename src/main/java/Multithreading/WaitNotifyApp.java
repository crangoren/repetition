package Multithreading;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WaitNotifyApp {

    public static void main(String[] args) throws InterruptedException {

        Object locker = new Object();
        int listSize = 10;
        List<String> list = new CopyOnWriteArrayList<>();

        Thread producer = new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (locker) {
                    while (list.size() >= listSize) {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                list.add("string");
            }
        });


        Thread consumer = new Thread(() ->{
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (locker) {
                    String first = list.remove(0);
                    System.out.println(first);
                    if (list.size() < listSize) {
                        locker.notifyAll();
                    }
                }
            }
        });

        consumer.start();
        producer.start();

        while (true) {
            Thread.sleep(1000);
            System.out.println("list size = " + list.size());
        }

    }
}
