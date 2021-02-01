package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker{

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " produce method starts...");
        condition.await();
        System.out.println(Thread.currentThread().getName() + " produce method again...");
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " produce method stops...");
    }

    public void consume() throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " consume method starts...");
        condition.signal();
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " consume method stops...");
    }
}

public class _18_ReentrantLock {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " starts");

        Worker worker = new Worker();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " starts");
            try {
                worker.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " dies");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " starts");
            try {
                worker.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " dies");
        });

        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread().getName() + " ends");
    }
}