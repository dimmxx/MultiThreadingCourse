package org.example._13_baeldung_sender_receiver;

import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable{
    private Data data;
    private String[] packets;

    public Sender(Data data, String[] packets) {
        this.data = data;
        this.packets = packets;
    }

    public void run() {
        System.out.println("Sender.class: " + Thread.currentThread().getName() + " starts");
        for (String packet : packets) {
            data.send(packet);
            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Sender.class: " + Thread.currentThread().getName() + " dies");
    }
}
