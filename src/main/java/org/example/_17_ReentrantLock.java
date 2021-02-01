package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _17_ReentrantLock {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    public static void increment(){
        System.out.println(Thread.currentThread().getName() + ": Increment method starts...");
        lock.lock();
        try {
            for(int i = 0; i < 1000000; i++){
                counter++;
            }
            System.out.println(Thread.currentThread().getName() + ": Increment method ends...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " starts");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " starts");
                increment();
                System.out.println(Thread.currentThread().getName() + " dies");
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " starts");
            increment();
            System.out.println(Thread.currentThread().getName() + " dies");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("======== " + counter);
        System.out.println(Thread.currentThread().getName() + " ends");
    }
}
