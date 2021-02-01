package org.example;

import java.util.ArrayList;
import java.util.List;

class ProcessorThreads {

    private Object lock = new Object();
    private List<Integer> list = new ArrayList<>();
    private int value = 1;

    private final static int UPPER_LIMIT = 5;
    private final static int LOWER_LIMIT = 0;

    public void producer() throws InterruptedException{
        synchronized (lock){
            while (true){
                if(list.size() == UPPER_LIMIT){
                    System.out.println("Waiting for removing items...");
                    lock.wait();
                } else {
                    System.out.println("Adding value " + value);
                    list.add(value);
                    value++;
                    lock.notify(); // !!! java does not notify immediately but does further operations until the end of synchronized block or wait() call
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException{
        synchronized (lock){
            while (true){
                if(list.size() == LOWER_LIMIT){
                    System.out.println("Waiting for adding items...");
                    value = 1;
                    lock.wait();
                } else {
                    System.out.println("Removing value " + list.remove(list.size() - 1));
                    lock.notify(); // !!! java does not notify immediately but does further operations until the end of synchronized block or wait() call
                }
                Thread.sleep(500);
            }
        }
    }
}

public class _16_ProducerConsumer {

    public static void main(String[] args) {

        ProcessorThreads processor = new ProcessorThreads();

        Thread threadProducer = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadConsumer = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadConsumer.start();
        threadProducer.start();
    }
}
