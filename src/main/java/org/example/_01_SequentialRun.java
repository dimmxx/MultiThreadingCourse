package org.example;

class Runner1 {
    public void execute(){
        for(int i = 0; i < 11; i++) {
            System.out.println(this.getClass().getSimpleName() + " " + i);
        }
    }
}

class Runner2 {
    public void execute(){
        for(int i = 0; i < 11; i++) {
            System.out.println(this.getClass().getSimpleName() + " " + i);
        }
    }
}

public class _01_SequentialRun {

    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();
        runner1.execute();
        runner2.execute();
    }
}
