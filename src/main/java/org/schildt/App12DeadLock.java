package org.schildt;

public class App12DeadLock {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new MyThread1(lock1, lock2, "Thread1");
        new MyThread2(lock1, lock2, "Thread2");
    }
}

class MyThread1 implements Runnable {
    final Object lock1;
    final Object lock2;

    public MyThread1(Object lock1, Object lock2, String name) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        Thread thread = new Thread(this);
        thread.setName(name);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started");
        System.out.println(Thread.currentThread().getName() + ": lock1=" + lock1);
        System.out.println(Thread.currentThread().getName() + ": lock2=" + lock2);
        System.out.println(Thread.currentThread().getName() + " is about to acquire lock1");
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " acquired lock1");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " is about to acquire lock2");
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock1 and lock2");
            }
            System.out.println(Thread.currentThread().getName() + " released lock2");
        }
        System.out.println(Thread.currentThread().getName() + " released lock1");
    }
}

class MyThread2 implements Runnable {
    final Object lock1;
    final Object lock2;

    public MyThread2(Object lock1, Object lock2, String name) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        Thread thread = new Thread(this);
        thread.setName(name);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started");
        System.out.println(Thread.currentThread().getName() + ": lock1=" + lock1);
        System.out.println(Thread.currentThread().getName() + ": lock2=" + lock2);
        System.out.println(Thread.currentThread().getName() + " is about to acquire lock2");
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + " acquired lock2");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " is about to acquire lock1");
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " acquired lock1 and lock2");
            }
            System.out.println(Thread.currentThread().getName() + " released lock1");
        }
        System.out.println(Thread.currentThread().getName() + " released lock2");
    }
}