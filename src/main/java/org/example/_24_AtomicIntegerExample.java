package org.example;

public class _24_AtomicIntegerExample {

    private static int counter = 0;

    public static void main(String[] args) {
        System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " starts");
        _24_AtomicIntegerExample atomicIntegerExample = new _24_AtomicIntegerExample();

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

    // does not work properly without synchronized
    private void increment() {
        System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " starts");
        for (int i = 0; i < 100000; i++) {
            counter++;
        }
        System.out.println(System.nanoTime() + " " + Thread.currentThread().getName() + " dies");
    }
}
