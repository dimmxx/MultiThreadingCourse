package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _18_ReentrantLockExample1 {
    private final int TIME_WAIT = 2000;
    private final int TIME_SLEEP = 5000;

    private String resource = "Hello, World!";
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS ");
    private Lock lock;

    _18_ReentrantLockExample1() {
        lock = new ReentrantLock();
        Thread thread1;
        Thread thread2;

        String thread1Name = "first_thread";
        String thread2Name = "second_thread";

        thread1 = new Thread(new LockClass(thread1Name,
                "text: the first thread"));
        thread2 = new Thread(new LockClass(thread2Name,
                "text: the second thread"));

        thread1.setName(thread1Name);
        thread2.setName(thread2Name);

        thread1.start();
        thread2.start();

        printMessage(null);

        while (thread1.isAlive() || thread2.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sdf.format(new Date()) + "[" + System.nanoTime() + "] " + "Завершение работы примера");
        System.out.println(">>> " + sdf.format(new Date()) + "[" + System.nanoTime() + "] "
                + Thread.currentThread().getName() + " dies");
        System.exit(0);
    }

    //-----------------------------------------------------
    public static void main(String[] args) {
        System.out.println(">>> " + sdf.format(new Date()) + "[" + System.nanoTime() + "] "
                + Thread.currentThread().getName() + " starts");
        new _18_ReentrantLockExample1();
    }

    //-----------------------------------------------------
    void printMessage(final String msg) {
        String time = sdf.format(new Date()) + "[" + System.nanoTime() + "] ";
        if (msg == null)
            time += resource;
        else
            time += msg;
        System.out.println(time);
    }

    //-----------------------------------------------------
    class LockClass implements Runnable {
        private String name;
        private String text;

        public LockClass(String name, String text) {
            this.name = name;
            this.text = text;
        }

        @Override
        public void run() {

            System.out.println(">>> " + sdf.format(new Date()) + "[" + System.nanoTime() + "] "
                    + Thread.currentThread().getName() + " starts");
            boolean acquiredLock = false;
            System.out.println(">>> " + sdf.format(new Date()) + "[" + System.nanoTime() + "] "
                    + Thread.currentThread().getName() + " locked check1: " + acquiredLock);
            try {
                // Получение блокировки в течение TIME_WAIT
                acquiredLock = lock.tryLock(TIME_WAIT,
                        TimeUnit.MILLISECONDS);
                System.out.println(">>> " + sdf.format(new Date()) + "[" + System.nanoTime() + "] "
                        + Thread.currentThread().getName() + " locked check2: " + acquiredLock);
                if (acquiredLock) {
                    resource = text + " acquired lock!!!";
                    printMessage(null);
                }
                Thread.sleep(TIME_SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Убираем блокировку
                String text = name + " : завершил работу";
                printMessage(text);
                System.out.println(">>> " + sdf.format(new Date()) + "[" + System.nanoTime() + "] "
                        + Thread.currentThread().getName() + " locked check3: " + acquiredLock);
                if (acquiredLock)
                    lock.unlock();
            }
            System.out.println(">>> " + sdf.format(new Date()) + "[" + System.nanoTime() + "] "
                    + Thread.currentThread().getName() + " dies");
        }
    }
}
