package org.schildt;

public class App4 {

    static class MyThread implements Runnable{

        Thread thread;

        MyThread(String name){
            thread = new Thread(this, name);
            thread.start();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " starts");
            for (int i = 0; i < 100; i++){
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
            }
            System.out.println(Thread.currentThread().getName() + " dies");
        }
    }

    public static void main(String[] args) {

       for (int j = 0; j < 5; j++){
           new MyThread("Thread" + String.valueOf(j));
       }

        System.out.println(Thread.currentThread().getName() + " starts");
        for (int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + ": i = " + i);
        }
        System.out.println(Thread.currentThread().getName() + " dies");
    }

}
