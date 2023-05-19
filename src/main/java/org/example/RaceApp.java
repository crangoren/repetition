package org.example;

public class RaceApp {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread thread = new Thread (new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }

            }
        });


        Thread thread1 = new Thread (() -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        System.out.println(counter.getCount()) ;
    }
    private static class  Counter {
        private int count = 0;

        public int getCount() {
            return count;
        }

        public synchronized void increment() {
            count++;
        }
        public  void decrement() {
            count--;
        }
    }

}
