package by.gartsmanovich.java_web_dev.helloworld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HelloWorld {

    /**
     * Logger for HelloWorld class.
     */
    private static Logger logger = LogManager.getLogger(HelloWorld.class
            .getName());

    /**
     * The main method of HelloWorld utility class.
     *
     * @param args the list of parameters that will be
     *             used to start the program.
     */
    public static void main(final String[] args) {

        String s = "Hello World!";
        logger.debug(s);
        logger.info(s);
        logger.error(s);

        HelloWorld helloWorld = new HelloWorld();
        logger.info(helloWorld.sum(Integer.MAX_VALUE, Integer
                .MAX_VALUE));
    }

    /**
     * Simple example for testing.
     *
     * @param a the first addend.
     * @param b the second addend.
     * @return the sum of a and b values.
     */
    int sum(final int a, final int b) {
        return a + b;
    }
}
