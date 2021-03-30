package org.schildt;

//Блок оператора synchronized гарантирует,
//что вызов метода, являющегося членом того же класса, что и синхронизируемый объект,
//на который делается указанная ссылка_на_объект, произойдет только тогда,
//когда текущий поток исполнения успешно войдет в монитор данного объекта.

class CallMe1 {
    void call(String message) {
        System.out.print("[" + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("]");
    }
}

class Caller1 implements Runnable {
    String message;
    final CallMe1 callMe1;
    Thread thread;

    public Caller1(String message, CallMe1 callMe1, int priority) {
        this.message = message;
        this.callMe1 = callMe1;
        thread = new Thread(this);
        thread.setPriority(priority);
        thread.start();
    }

    @Override
    public void run() {
        synchronized (callMe1){
            callMe1.call(message);
        }
    }
}

class Sync1 {

    public static void main(String[] args) {

        CallMe1 callMe1 = new CallMe1();
        Caller1 caller1 = new Caller1("Welcome to", callMe1, 10);
        Caller1 caller3 = new Caller1("world", callMe1, 1);
        Caller1 caller2 = new Caller1("synchronized", callMe1, 10);

        try {
            caller1.thread.join();
            caller2.thread.join();
            caller3.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}