package org.example;
import java.util.concurrent.Semaphore;

class Data {
    int n;
    // начать с недоступного семафора потребителя
    static Semaphore semCon = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);

    void consume() {
        try {
            semCon.acquire();
            //System.out.println(Thread.currentThread().getName() + " " + semCon.availablePermits());
            System.out.println(" Получено: " + n);
            Thread.sleep(500);
        } catch(InterruptedException e) {
            System.out.println("Перехвачено исключение типа InterruptedException");
        }
        semProd.release();
    }

    void produce(int n) {
        try {
            semProd.acquire();
            this.n = n;
            System.out.println("Отправлено: " + n);
            Thread.sleep(500);
        } catch(InterruptedException e) {
            System.out.println("Перехвачено исключение типа InterruptedException");
        }
        semCon.release();
    }
}

class Producer implements Runnable {
    Data data;

    Producer(Data data) {
        this.data = data;
        new Thread(this, "Producer").start();
    }

    public void run() {
        for(int i = 0; i < 7; i++) {
            data.produce(i);
        }
    }
}

class Consumer implements Runnable {
    Data data;

    Consumer(Data data) {
        this.data = data;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        for(int i = 0; i < 7; i++) {
            data.consume();
        }
    }
}

public class _28_SemaphoreExample {
    public static void main(String args[]) {
        Data data = new Data();
        new Consumer(data);
        new Producer(data);
    }
}