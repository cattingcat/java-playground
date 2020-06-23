package com.company.projname.experiments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyTests {
    public static void main(final String[] args) throws Exception {
//        exchangerTest();
//        poolsTest2();
//        forkJoinTest();
        forkJoinTest2();
    }


    static void poolsTests() throws Exception {
        final var q = new ArrayBlockingQueue<Runnable>(50);
        final var e1 = new ThreadPoolExecutor(5, 15, 20, TimeUnit.SECONDS, q);
        final var e2 = new ScheduledThreadPoolExecutor(20);
        final var e3 = new ForkJoinPool(20);

//        Executors.callable()

        for(int i = 0; i < 60; ++i) {
            e1.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(100);
        System.out.println(q.remainingCapacity());

        Thread.sleep(2000);
        System.out.println(q.remainingCapacity());

        e1.shutdown();
    }

    static void semaphoreTest() {
        final var semProd = new Semaphore(1);
        final var semCons = new Semaphore(1);
        AtomicInteger shared = new AtomicInteger();
        final var t1 = new Thread(() -> {
            try {
                System.out.println("Inc: before acquire");
                semProd.acquire();
                System.out.println("Inc: after acquire");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < 5; ++i) {
                shared.getAndIncrement();
                System.out.println("Inc " + shared.get());
            }
            semCons.release();
            System.out.println("Inc: after release");
        });

        final var t2 = new Thread(() -> {
            try {
                System.out.println("Dec: before acquire");
                semCons.acquire();
                System.out.println("Dec: after acquire");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0; i < 5; ++i) {
                shared.getAndDecrement();
                System.out.println("Dec " + shared.get());
            }
            semProd.release();
            System.out.println("Dec: after release");
        });

        t1.start();
        t2.start();
    }

    static void exchangerTest() {
        final var e = new Exchanger<String>();

        final var t1 = new Thread(() -> {
            try {
                System.out.println("Thread A puts A and receive " + e.exchange("a"));
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        final var t2 = new Thread(() -> {
            try {
                System.out.println("Thread B puts B and receive " + e.exchange("b"));
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

    static void poolsTest2() throws ExecutionException, InterruptedException, TimeoutException {
        final var pool = Executors.newFixedThreadPool(3);
        final Callable<Integer> heavyCalculations = () -> {
            var s = 0;
            for (long i = 0; i < 5000000000L; ++i) {
                s += i;
            }
            return s;
        };

        final var future = pool.submit(heavyCalculations);
        final var future2 = pool.submit(heavyCalculations);

//        final var isTerminated = pool.awaitTermination(1, TimeUnit.MILLISECONDS);
//        System.out.println("is terminated " + isTerminated);

        final var r1 = future.get(1, TimeUnit.MILLISECONDS);
        final var r2 = future2.get();

        System.out.println(r1 + " " + r2);

        pool.shutdown();
    }

    static void concurrentCollections() {
        // synchronized inside
        final var a = new CopyOnWriteArrayList<>();
        final var b = new CopyOnWriteArraySet<>();

        final var c = new ConcurrentHashMap<Integer, String>();
        final var d = new ConcurrentSkipListMap<Integer, String>();
        final var e = new ConcurrentSkipListSet<String>();

        final var f = new ConcurrentLinkedQueue();
    }

    static void forkJoinTest() {
        final var pool = ForkJoinPool.commonPool();

        class SqrtArrTask extends RecursiveTask<double[]> {
            final double[] val;
            final int start;
            final int end;

            SqrtArrTask(final double[] val, final int start, final int end) {
                this.val = val;
                this.start = start;
                this.end = end;
            }

            @Override
            protected double[] compute() {
                for(int i = start; i < end; ++i) {
                    final var tmp = val[i];
                    val[i] = Math.sqrt(tmp);
                }
                return val;
            }
        }

        final var rt = new RecursiveTask<String>() {
            @Override
            protected String compute() {
                final var arr = new double[] {1,2,3,4,5,6,7,89,12,3,123,12,3,12,31,23,1,23,12,3,12,31,23,1,453,645,7,567};
                final var middle = arr.length / 2;

                invokeAll(
                    new SqrtArrTask(arr, 0, middle),
                    new SqrtArrTask(arr, middle, arr.length));

                for(var i : arr) {
                    System.out.println(i);
                }

                return "";
            };
        };

        final var res = pool.invoke(rt);

        System.out.println(res);
    }

    static void forkJoinTest2() {
        final var pool = ForkJoinPool.commonPool();

        class Sum  extends RecursiveTask<Double> {
            private final double[] vals;

            Sum(final double[] vals) {
                this.vals = vals;
            }

            @Override
            protected Double compute() {

                if(vals.length < 10) {
                    var s = 0.0;
                    for(var i : vals) {
                        s += i;
                    }

                    return s;
                } else {
                    final var middle = vals.length / 2;
                    final var a = Arrays.copyOfRange(vals, 0, middle);
                    final var b = Arrays.copyOfRange(vals, middle, vals.length);

                    final var s1 = new Sum(a);
                    final var s2 = new Sum(b);

                    s1.fork();
                    s2.fork();

                    return s1.join() + s2.join();
                }
            }
        }

        final var data = new double[500];
        Arrays.fill(data, 4.0);
        final var res = pool.invoke(new Sum(data));

        System.out.println(res);
    }
}
