package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _22_Deadlock {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {

        _22_Deadlock deadlock = new _22_Deadlock();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                deadlock.worker1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                deadlock.worker2();
            }
        });

        thread1.start();
        thread2.start();
    }

    public void worker1(){
        lock1.lock();
        System.out.println("Worker1 acquires the lock1...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("Worker1 acquires the lock2...");

        lock1.unlock();
        lock2.unlock();

    }

    public void worker2(){
        lock2.lock();
        System.out.println("Worker2 acquires the lock2...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock1.lock();
        System.out.println("Worker2 acquires the lock1...");

        //lock1.unlock();
        //lock2.unlock();
    }
}
