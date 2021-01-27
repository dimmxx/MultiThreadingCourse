package org.example;

class Runner5 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 110; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getClass().getSimpleName() + " " + i + " ===");
        }
    }
}

class Runner6 extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 110; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getClass().getSimpleName() + " " + i + " +++");
        }
    }
}

public class _04_ExtendThreadRun {

    public static void main(String[] args) {

        Thread thread1 = new Runner5();
        Thread thread2 = new Runner6();

        thread1.start();
        thread2.start();
    }
}
