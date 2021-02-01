package org.example;

public class _20_VolatileRunner {

    private static int number;
    private static boolean ready;

    public static void main(String[] args) {
        new Reader().start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number = 42;
        ready = true;
    }

    private static class Reader extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
                System.out.println("yield");
            }

            System.out.println(number);
        }
    }
}
