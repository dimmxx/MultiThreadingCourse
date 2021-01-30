package org.example;

//using synchronized block - but all the same the whole class is blocked

import java.time.Duration;
import java.time.LocalTime;

public class _11_SynchronizedExample {

    private static int iterations = 100000000;
    private static int counter1 = 0;
    private static int counter2 = 0;

    private static void incrementCounter1(){
        synchronized (_11_SynchronizedExample.class){
            counter1++;
        }
    }
    private static void incrementCounter2(){
        synchronized (_11_SynchronizedExample.class){
            counter2++;
        }
    }

    public static void process(){
        System.out.println("Process method starts...");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " starts");
                for(int i = 0; i < iterations; i++){
                    incrementCounter1();
                }
                System.out.println(Thread.currentThread().getName() + " dies");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " starts");
                for(int i = 0; i < iterations; i++){
                    incrementCounter2();
                }
                System.out.println(Thread.currentThread().getName() + " dies");
            }
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
        LocalTime start = LocalTime.now();
        System.out.println(Thread.currentThread().getName() + " starts");
        process();
        System.out.println("Counter1: " + counter1);
        System.out.println("Counter2: " + counter2);
        System.out.println(Thread.currentThread().getName() + " ends");
        System.out.println(Duration.between(start, LocalTime.now()).toMillis());
    }
}
