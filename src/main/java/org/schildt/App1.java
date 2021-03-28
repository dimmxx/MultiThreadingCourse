package org.schildt;

public class App1 {

    static class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " starts");
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName());
            }
            System.out.println(Thread.currentThread().getName() + " dies");
        }
    }

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.setPriority(1);
        thread.start();

        System.out.println(Thread.currentThread().getName() + " starts");
        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName());
        }
        System.out.println(Thread.currentThread().getName() + " dies");
    }

}
