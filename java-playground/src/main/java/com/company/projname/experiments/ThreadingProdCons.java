package com.company.projname.experiments;

public class ThreadingProdCons {
    public static void main(String[] args) {
        final var q = new Queue();
        final var p = new Publisher(q);
        final var c = new Consumer(q);

        p.start();
        c.start();

        System.out.println("Stop me");
    }
}

class Queue {
    private int n;
    private boolean valueSet = false;

    synchronized void put(final int i) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        n = i;
        valueSet = true;
        notify();
    }

    synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        valueSet = false;
        notify();
        return n;
    }
}

class Publisher {
    final Queue queue;

    Publisher(final Queue queue) {
        this.queue = queue;
    }

    void start() {
        final var t = new Thread(() -> {
            int i = 0;

            while(true) {
                queue.put(++i);
                System.out.println("put");
            }
        });

        t.start();
    }
}


class Consumer {
    final Queue queue;

    Consumer(final Queue queue) {
        this.queue = queue;
    }

    void start() {
        final var t = new Thread(() -> {
            while(true) {
                final var i = queue.get();
                System.out.println("get");
            }
        });

        t.start();
    }
}