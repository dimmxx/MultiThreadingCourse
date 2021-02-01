package org.example;

class Process {

    public void produce() throws InterruptedException{
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + ": enter produce method");
            wait();
            System.out.println(Thread.currentThread().getName() + ": resume produce method");
            System.out.println(Thread.currentThread().getName() + ": end of produce method");
        }
    }

    public void consume() throws InterruptedException{
        Thread.sleep(1000);
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + ": enter consume method");
            notify();
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + ": resume produce method");
            System.out.println(Thread.currentThread().getName() + ": end of produce method");
        }
    }
}

public class _14_WaitNotifyExample {

    public static void main(String[] args) {

        Process process = new Process();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
