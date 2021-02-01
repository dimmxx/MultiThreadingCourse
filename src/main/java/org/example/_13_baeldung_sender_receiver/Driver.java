package org.example._13_baeldung_sender_receiver;

public class Driver {

    public static void main(String[] args) {

        String[] packets = {
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "End"
        };

        Data data = new Data();
        Thread sender = new Thread(new Sender(data, packets));
        Thread receiver = new Thread(new Receiver(data));

        sender.start();
        receiver.start();

        System.out.println(Thread.currentThread().getName() + " dies");
    }
}
