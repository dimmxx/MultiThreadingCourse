package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _23_LiveLock {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        _23_LiveLock liveLock = new _23_LiveLock();

        Thread thread1 = new Thread(liveLock::worker1, "worker1");
        Thread thread2 = new Thread(liveLock::worker2, "worker2");

        thread1.start();
        thread2.start();
    }

    public void worker1(){
        while (true){
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Worker1 acquires the lock1...");
            System.out.println("Worker1 tries to get lock2...");

            if(lock2.tryLock()){
                System.out.println("Worker1 acquires the lock2...");
                lock2.unlock();
            } else {
                System.out.println("Worker1 can not acquire lock2...");
                continue;
            }
            System.out.println("Worker1 breaks free......................................");
            break;
        }

        lock1.unlock();
        lock2.unlock();
        System.out.println("Worker1 dies :-(");
    }

    public void worker2(){
        while (true){
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker2 acquires the lock2...");

            System.out.println("Worker2 tries to get lock1...");
            if(lock1.tryLock()){
                System.out.println("Worker2 acquires the lock1...");
                lock1.unlock();
            } else {
                System.out.println("Worker2 can not acquire lock1...");
                continue;
            }
            System.out.println("Worker2 breaks free......................................");
            break;
        }

        lock1.unlock();
        lock2.unlock();
        System.out.println("Worker2 dies :-(");
    }
}
