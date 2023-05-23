package Multithreading;

public class ThreadApp {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable());
        Thread thread1 = new Thread(new MyRunnable());

        System.out.println(Thread.currentThread().getName());

        Thread thread2 = new MyThread();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "from anonymous");
            }
        });

        Thread thread4 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "from lambda"));

        thread3.start();
        thread3.sleep(1000);
        thread2.start();
        thread4.start();
        thread1.start();
        thread.start();

        printDoubleString(string -> string+string, "test string");
    }

    public static class MyThread extends Thread {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "from MyThread");
        }
    }

    public static class  MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "from MyRunnable");
        }
    }

    public static void printDoubleString(SimpleInterface simpleInterface, String testString) {
        System.out.println(simpleInterface.doubleString(testString));
    }
}
