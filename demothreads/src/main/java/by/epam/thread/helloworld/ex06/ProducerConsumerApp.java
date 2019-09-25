package by.epam.thread.helloworld.ex06;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProducerConsumerApp {
    public static void main(String[] args) {

        Store store = new Store();
        new Producer(store).start();
        new Consumer(store).start();
    }
}

class Store {

    private static final Logger LOGGER = LogManager.getLogger(Store.class);

    private int product = 0;

    synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error(e);
                Thread.currentThread().interrupt();
            }
        }
        product++;
        String message = String.format(
                "Производитель добавил 1 товар.%nТоваров на складе: %d",
                product);
        LOGGER.debug(message);
        notifyAll();
    }

    synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error(e);
                Thread.currentThread().interrupt();
            }
        }
        product--;
        String message = String.format(
                "Покупатель купил 1 товар.%nТоваров на складе: %d", product);
        LOGGER.debug(message);
        notifyAll();
    }
}

class Producer extends Thread {

    private Store store;

    Producer(final Store storeValue) {
        store = storeValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            store.put();
        }
    }
}

class Consumer extends Thread {
    private Store store;

    Consumer(final Store storeValue) {
        store = storeValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            store.get();
        }
    }
}
