package by.epam.thread.helloworld.ex03;

import java.util.ArrayList;

public class PriorityRunner {
    public static void main(String[ ] args) throws InterruptedException {

        System.out.println("The Runner has started!");
        PriorThread min = new PriorThread("Min");
        PriorThread max = new PriorThread("Max");
        PriorThread norm = new PriorThread("Norm");
        min.setPriority(Thread.MIN_PRIORITY); // 1
        max.setPriority(Thread.MAX_PRIORITY); // 10
        norm.setPriority(Thread.NORM_PRIORITY); // 5
        min.start();
        norm.start();
        max.start();


        norm.join(100);
        System.out.println("The Runner has stopped!");


    }

}

