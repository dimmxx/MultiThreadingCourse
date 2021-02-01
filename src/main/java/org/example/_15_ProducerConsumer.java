package org.example;

import java.util.ArrayList;
import java.util.List;

class Processor {

    private List<Integer> list = new ArrayList<>();
    private int value = 1;

    private final static int UPPER_LIMIT = 5;
    private final static int LOWER_LIMIT = 0;

    public void produce() throws InterruptedException{
        synchronized (this){
            while (true){
                if(list.size() == UPPER_LIMIT){
                    System.out.println(this.getClass().getName()
                            + " : " + Thread.currentThread().getName()
                            + ": list size: " + list.size() + " - PRODUCER is waiting for adding numbers...");
                    wait();
                } else {
                    System.out.println(this.getClass().getName()
                            + " : " + Thread.currentThread().getName()
                            + ": list size: " + list.size() + " - PRODUCER is adding numbers and notifies...");
                    list.add(value);
                    System.out.println("+" + value++);
                    notify();
                }
                Thread.sleep(1500);
            }
        }
    }

    public void consume() throws InterruptedException{
        synchronized (this){
            while (true){
                if(list.size() == LOWER_LIMIT){
                    value = 1;
                    System.out.println(this.getClass().getName()
                            + " : " + Thread.currentThread().getName()
                            + ": list size: " + list.size() + " - CONSUMER is waiting for consuming numbers...");
                    wait();
                } else {
                    System.out.println(this.getClass().getName()
                            + " : " + Thread.currentThread().getName()
                            + ": list size: " + list.size() + " - CONSUMER is consuming numbers and notifies...");
                    System.out.println("-" + list.get(list.size() - 1));
                    list.remove(list.size()  - 1);
                    notify();
                }
                Thread.sleep(1500);
            }
        }
    }
}

public class _15_ProducerConsumer {

    public static void main(String[] args) {

        Processor processor = new Processor();

        Thread threadProducer = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadConsumer = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadConsumer.start();
        threadProducer.start();
    }
}
