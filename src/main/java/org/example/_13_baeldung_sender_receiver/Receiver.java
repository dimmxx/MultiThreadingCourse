package org.example._13_baeldung_sender_receiver;

import java.util.concurrent.ThreadLocalRandom;

public class Receiver implements Runnable{
    private Data data;

    public Receiver(Data load) {
        this.data = load;
    }

    public void run() {
        System.out.println("Receiver.class: " + Thread.currentThread().getName() + " starts");
        for(String receivedMessage = data.receive();
            !"End".equals(receivedMessage);
            receivedMessage = data.receive()) {

            System.out.println("Receiver.class: " + " =================== Packet received: " + receivedMessage);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Receiver.class: " + Thread.currentThread().getName() + " dies");
    }



}
