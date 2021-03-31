package org.schildt;

public class App10DeadLock {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        new MyThread1("Thread1");
        new MyThread2("Thread2");
    }

    private static class MyThread1 implements Runnable {
        public MyThread1(String name) {
            Thread thread = new Thread(this);
            thread.setName(name);
            thread.start();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started");
            System.out.println(Thread.currentThread().getName() + " is about to acquire lock1");
            synchronized (lock1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " acquired lock1");
                System.out.println(Thread.currentThread().getName() + " is about to acquire lock2");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock1 and lock2");
                }
                System.out.println(Thread.currentThread().getName() + " released lock2");
            }
            System.out.println(Thread.currentThread().getName() + " released lock1");
        }
    }

    private static class MyThread2 implements Runnable {
        public MyThread2(String name) {
            Thread thread = new Thread(this);
            thread.setName(name);
            thread.start();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started");
            System.out.println(Thread.currentThread().getName() + " is about to acquire lock2");
            synchronized (lock2) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " acquired lock2");
                System.out.println(Thread.currentThread().getName() + " is about to acquire lock1");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock1 and lock2");
                }
                System.out.println(Thread.currentThread().getName() + " released lock1");
            }
            System.out.println(Thread.currentThread().getName() + " released lock2");
        }
    }
}


