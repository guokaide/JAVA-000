package java0.homework;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Homework {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        long start = System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> result.set(Homework.sum()));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("异步计算结果为：" + result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public static void test2() {
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> result = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return Homework.sum();
            }
        });
        executor.shutdown();
        try {
            System.out.println("异步计算结果为：" + result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public static void test3() {
        long start = System.currentTimeMillis();
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return Homework.sum();
            }
        });
        new Thread(task).start();
        try {
            System.out.println("异步计算结果为：" + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public static void test4() {
        long start = System.currentTimeMillis();
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return Homework.sum();
            }
        });
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(task);
        executor.shutdown();
        try {
            System.out.println("异步计算结果为：" + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public static void test5() {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(Homework::sum);
        try {
            System.out.println("异步计算结果为：" + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public static void test6() {
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(1);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        cs.submit(Homework::sum);
        executor.shutdown();
        try {
            System.out.println("异步计算结果为：" + cs.take().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public static void test7() {
        long start = System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(1);
        executor.submit(() -> {
            result.set(Homework.sum());
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        try {
            System.out.println("异步计算结果为：" + result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
