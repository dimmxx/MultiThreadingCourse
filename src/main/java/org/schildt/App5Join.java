package org.schildt;

public class App5Join {

    static class MyThread implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " starts");
            for(int i = 0 ; i < 1000; i++){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            System.out.println(Thread.currentThread().getName() + " dies");
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " starts");
        for(int i = 0 ; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }

        Thread thread = new Thread(new MyThread());

        //thread.setDaemon(true);

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " dies");

    }




}
