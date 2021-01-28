package org.example;

// Java program to explain the
// concept of joining a thread.

class ThreadJoining extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " starts...");
        for (int i = 0; i < 2; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " before sleep");
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + " after sleep");
            } catch (Exception ex) {
                System.out.println("Exception has been caught" + ex);
            }
            System.out.println(Thread.currentThread().getName() + " output: " + i);
        }
        System.out.println(Thread.currentThread().getName() + " dies");
    }
}

public class _05_JoinExample {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " starts");
        // creating threads
        ThreadJoining t1 = new ThreadJoining();
        ThreadJoining t2 = new ThreadJoining();

        // thread t1 starts
        t1.start();
        // starts second thread after when
        // first thread t1 has died.
        try {
            System.out.println(Thread.currentThread().getName() + " before join");
            t1.join();
            System.out.println(Thread.currentThread().getName() + " after join");
        } catch (Exception ex) {
            System.out.println("Exception has " + "been caught" + ex);
        }
        t2.start();
        System.out.println(Thread.currentThread().getName() + " ends");
    }
}
