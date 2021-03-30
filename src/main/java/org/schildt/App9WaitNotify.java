package org.schildt;

public class App9WaitNotify {

    public static void main(String[] args) {

        Printer printer = new Printer();
        new Thread(new MyThreadA(printer)).start();
        new Thread(new MyThreadB(printer)).start();

    }
}

class Printer{
    volatile int flag = 1;
}

class MyThreadA implements Runnable {
    final Printer printer;

    MyThreadA(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        synchronized (printer) {
            for (int i = 0; i < 100; i++) {
                if (printer.flag != 1) {
                    try {
                       printer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A");
                printer.flag = 2;
                printer.notify();
            }
        }
    }
}


class MyThreadB implements Runnable {
    final Printer printer;

    MyThreadB(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        synchronized (printer) {
            for (int i = 0; i < 100; i++) {
                if (printer.flag != 2) {
                    try {
                        printer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B");
                printer.flag = 1;
                printer.notify();
            }
        }
    }
}

