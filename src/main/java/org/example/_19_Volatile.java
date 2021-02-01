package org.example;

class WorkerVolatile implements Runnable{

    private boolean terminated;

    @Override
    public void run() {
        while (!terminated){
            System.out.println(System.nanoTime() + ": " + Thread.currentThread().getName() + " is running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}

public class _19_Volatile {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": starts");
        WorkerVolatile worker = new WorkerVolatile();
        Thread thread = new Thread(worker);
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.setTerminated(true);
        System.out.println(Thread.currentThread().getName() + ": worker is set to terminated");

        System.out.println(Thread.currentThread().getName() + ": ends");
    }
}