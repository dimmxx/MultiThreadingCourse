package org.example;

public class _08_SynchronizedExample {

    private static int counter = 0;
    private static void incrementCounter(){
        counter++;
    }

    public static void process(){
        System.out.println("Process method starts...");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " starts");
                for(int i = 0; i < 100000; i++){
                    incrementCounter();
                    //System.out.println(Thread.currentThread().getName() + ": i =  " + i);
                }
                System.out.println(Thread.currentThread().getName() + " dies");
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " starts");
            for(int i = 0; i < 100000; i++){
                incrementCounter();
                //System.out.println(Thread.currentThread().getName() + ": i =  " + i);
            }
            System.out.println(Thread.currentThread().getName() + " dies");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " starts");
        process();
        System.out.println(counter);
        System.out.println(Thread.currentThread().getName() + " ends");
    }
}
