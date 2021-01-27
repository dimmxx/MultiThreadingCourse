package org.example;

class Runner3 implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i < 110; i++) {
            System.out.println(this.getClass().getSimpleName() + " " + i + "===");
        }
    }
}

class Runner4 implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i < 110; i++) {
            System.out.println(this.getClass().getSimpleName() + " " + i + "@@@" );
        }
    }
}

public class _02_RunnableRun {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runner3());
        Thread thread2 = new Thread(new Runner4());

        thread1.start();
        thread2.start();
    }
}
