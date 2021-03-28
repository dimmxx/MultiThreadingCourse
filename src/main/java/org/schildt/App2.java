package org.schildt;

public class App2 {

    static class MyThread implements Runnable{

        Thread thread;

        MyThread(){
            thread = new Thread(this);
            thread.setName("MyCustomThread");
            thread.setPriority(1);
            thread.start();
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
