package com.company.projname.experiments;

public class Threading {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread());
        final var t1 = new Thread(new MyRunnable("1"), "MyRunnable thread");
        final var t2 = new Thread(new MyRunnable("2"), "MyRunnable thread");

        t1.start();
        t2.start();

        System.out.println(t1.isAlive());
        t1.join();
        t2.join();
        System.out.println(t1.isAlive());
    }
}


class MyRunnable implements Runnable {
    final String name;

    MyRunnable(final String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 5; ++i) {
                Thread.sleep(500);
                System.out.println("MyRunnable " + name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class MyThread extends Thread {

}

class MySynchronizedClass {
    final String text;

    MySynchronizedClass(final String text) {
        this.text = text;
    }

    // Check with and without synchronized
    synchronized void call(final String text2) {
        try {
            System.out.print("[");
            Thread.sleep(100);
            System.out.print(text);
            System.out.print(" ");
            System.out.print(text2);
            Thread.sleep(100);
            System.out.println("]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) throws InterruptedException {
        final var o = new MySynchronizedClass("1");
        final var t1 = new Thread(() -> o.call("t1"));
        final var t2 = new Thread(() -> o.call("t2"));
        final var t3 = new Thread(() -> o.call("t3"));

        t1.start();
        t2.start();
        t3.start();
    }
}
