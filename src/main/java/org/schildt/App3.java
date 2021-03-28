package org.schildt;

public class App3 {

    static class MyThread extends Thread {

        MyThread () {
            super("MyCustomThread");
            this.setPriority(1);
            this.start();
        }

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

       new MyThread();

        System.out.println(Thread.currentThread().getName() + " starts");
        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName());
        }
        System.out.println(Thread.currentThread().getName() + " dies");
    }

}
