package Multithreading.race;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT = 0;



    private Race race;
    private int speed;
    private String name;
    private static boolean isWinner;

    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }


    public Car(Race race, int speed, CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cyclicBarrier = cyclicBarrier;
        this.countDownLatch = countDownLatch;

    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
                countDownLatch.countDown();
            }
            countDownLatch.await();
            checkWinner(this);
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    private static void checkWinner(Car c) {
        if (!isWinner) {
            System.out.println(c.name + " - победитель!");
            isWinner = true;
        }
    }

}
