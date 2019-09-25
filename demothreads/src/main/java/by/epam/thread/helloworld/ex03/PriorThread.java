package by.epam.thread.helloworld.ex03;

import java.util.concurrent.TimeUnit;

public class PriorThread extends Thread {

    PriorThread(String name) {
        super(name);
    }
    public void run() {
        for (int i = 0; i <= 50; i++) {
            System.out.println(getName() + " " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
    }
}

