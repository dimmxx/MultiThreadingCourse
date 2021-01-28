package org.example;

class Runner7 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getClass().getSimpleName() + " " + i + " ===");
        }
    }
}

class Runner8 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getClass().getSimpleName() + " " + i + " +++");
        }
    }
}

public class _06_JoinExample {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Runner7();
        Thread thread2 = new Runner8();

        thread1.start();
        thread2.start();
        thread1.join();

        System.out.println("Main dies...");
    }
}
