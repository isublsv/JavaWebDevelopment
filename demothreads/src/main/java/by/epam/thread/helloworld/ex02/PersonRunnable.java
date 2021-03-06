package by.epam.thread.helloworld.ex02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonRunnable extends Person implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(
            PersonRunnable.class);

    PersonRunnable(final String name) {
        super(name);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            String message = getName() + ": Hello world";
            LOGGER.debug(message);
        }
    }

    public static void main(String[] args) {
        Thread alice = new Thread(new PersonRunnable("Alice"));
        alice.setPriority(1);
        alice.start();

        Thread bob = new Thread(new PersonRunnable("Bob"));
        bob.setPriority(10);
        bob.start();
    }
}
