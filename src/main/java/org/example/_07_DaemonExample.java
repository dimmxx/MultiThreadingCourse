package org.example;

class MyThread extends Thread {
    private long millis;
    private int iterations;

    MyThread(long millis, int iterations){
        this.millis = millis;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starts...");
        for (int i = 0; i < iterations; i++) {
            try {
                //System.out.println(Thread.currentThread().getName() + " before sleep");
                Thread.sleep(millis);
                //System.out.println(Thread.currentThread().getName() + " after sleep");
            } catch (Exception ex) {
                System.out.println("Exception has been caught" + ex);
            }
            System.out.println(Thread.currentThread().getName() + " output: " + i);
        }
        System.out.println(Thread.currentThread().getName() + " dies");
    }
}

public class _07_DaemonExample {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " starts");
        // creating threads
        Thread t1 = new MyThread(10, 100);
        t1.setDaemon(false);
        Thread t2 = new MyThread(10, 10);

        t1.start();
        t2.start();

        System.out.println(Thread.currentThread().getName() + " ends");
    }
}
