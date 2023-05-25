package Multithreading;

import java.util.concurrent.*;

public class ExecutorApp {

    public static void main(String[] args) throws InterruptedException {

        //ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(10);
        //ExecutorService cachedExecutorService = Executors.newCachedThreadPool();

        fixedExecutorService.execute(() -> System.out.println("abc"));
        Future<?> abc = fixedExecutorService.submit(() -> System.out.println("abc"));
        Future<String> submit = fixedExecutorService.submit(() -> "callable string");

        Future<Integer> intFuture = fixedExecutorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 10/0;
            }
        });

        try {
            Integer integer = intFuture.get();
        } catch (ExecutionException e) {
            e.getCause().printStackTrace();
            throw new RuntimeException(e.getCause());
        }

        fixedExecutorService.shutdown();
    }
}
