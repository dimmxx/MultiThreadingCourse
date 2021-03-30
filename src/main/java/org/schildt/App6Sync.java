package org.schildt;

//Как только поток исполнения входит в любой синхронизированный метод экземпляра,
//ни один другой поток исполнения не сможет войти в какой-нибудь другой
//синхронизированный метод того же экземпляра.
//Тем не менее несинхронизированные методы этого экземпляра по-прежнему остаются доступными для вызова.

class CallMe {
    synchronized void call(String message) {
        System.out.print("[" + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("]");
    }
}

class Caller implements Runnable {
    String message;
    CallMe callMe;
    Thread thread;

    public Caller(String message, CallMe callMe, int priority) {
        this.message = message;
        this.callMe = callMe;
        thread = new Thread(this);
        thread.setPriority(priority);
        thread.start();
    }

    @Override
    public void run() {
        callMe.call(message);
    }
}

class Sync {

    public static void main(String[] args) {

        CallMe callMe = new CallMe();
        Caller caller1 = new Caller("Welcome to", callMe, 10);
        Caller caller3 = new Caller("world", callMe, 1);
        Caller caller2 = new Caller("synchronized", callMe, 10);

        try {
            caller1.thread.join();
            caller2.thread.join();
            caller3.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}






