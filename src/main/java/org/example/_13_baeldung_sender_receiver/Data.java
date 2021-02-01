package org.example._13_baeldung_sender_receiver;

public class Data {
    private String packet;

    private boolean sendingMode = true;

    public synchronized void send(String packet) {
        System.out.println("Data.class: " + Thread.currentThread().getName() + " SEND method starts");
        while (!sendingMode) {
            try {
                wait();
                System.out.println("Data.class: " + Thread.currentThread().getName() + " wait from SEND method");
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
            }
        }

        this.packet = packet;
        sendingMode = false;
        notifyAll();
        System.out.println("Data.class: " + Thread.currentThread().getName() + " notifyAll from SEND method");
    }

    public synchronized String receive() {
        System.out.println("Data.class: " + Thread.currentThread().getName() + " RECEIVE method starts");
        while (sendingMode) {
            try {
                wait();
                System.out.println("Data.class: " + Thread.currentThread().getName() + " wait from RECEIVE method");
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt();
            }
        }
        sendingMode = true;

        notifyAll();
        System.out.println("Data.class: " + Thread.currentThread().getName() + " notifyAll from RECEIVE method");
        return packet;
    }
}
