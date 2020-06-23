package com.company.projname.experiments;

public class ThreadLocalTest {
    final ThreadLocal<Integer> var = new ThreadLocal<Integer>();


    public static void main(final String[] args) throws InterruptedException {
        final var sharedObj = new ThreadLocalTest();
        sharedObj.var.set(1);

        final var t1 = new Thread(() -> {
            final var tid = (int) Thread.currentThread().getId();
            final var sharedVal = sharedObj.var.get();
            System.out.println(sharedVal);

            sharedObj.var.set(tid);
        });

        final var t2 = new Thread(() -> {
            final var tid = (int) Thread.currentThread().getId();
            final var sharedVal = sharedObj.var.get();
            System.out.println(sharedVal);

            sharedObj.var.set(tid);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(sharedObj.var.get());
    }

}
