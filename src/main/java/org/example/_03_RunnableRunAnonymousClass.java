package org.example;

public class _03_RunnableRunAnonymousClass {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    for(int i = 0; i < 1000; i++) {
                        System.out.println(this.getClass().getSimpleName() + " " + i + "===");
                    }
                }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1000; i++) {
                    System.out.println(this.getClass().getSimpleName() + " " + i + "###");
                }
            }
        });

        Runnable runnable = () -> {
            for(int i = 0; i < 1000; i++) {
                System.out.println("lambda " + i + "###");
            }
        };
        Thread thread3 = new Thread(runnable);

        thread2.start();
        thread1.start();
        thread3.start();
    }
}
