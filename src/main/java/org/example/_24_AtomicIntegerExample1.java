package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class _24_AtomicIntegerExample1 {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " starts");
        _24_AtomicIntegerExample1 atomicIntegerExample = new _24_AtomicIntegerExample1();

        // 1 ===========================================================================================================
        new Thread(new Runnable() {
            @Override
            public void run() {
                atomicIntegerExample.increment();
            }
        }).start();

        // 2 ===========================================================================================================
        new Thread(() -> atomicIntegerExample.increment()).start();

        // 3 ===========================================================================================================
        new Thread(atomicIntegerExample::increment).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
        System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " dies");
    }

    // when using AtomicInteger no need in synchronized methods
    private void increment() {
        System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " starts");
        for (int i = 0; i < 100000; i++) {
            counter.getAndIncrement();
        }
        System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " dies");
    }
}
