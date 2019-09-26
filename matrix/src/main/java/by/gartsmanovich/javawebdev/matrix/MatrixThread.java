package by.gartsmanovich.javawebdev.matrix;

import by.gartsmanovich.javawebdev.matrix.exception.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MatrixThread implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(
            MatrixThread.class);

    private Matrix matrix;
    private int row;

    public MatrixThread(final Matrix matrixValue, final int rowValue) {
        matrix = matrixValue;
        row = rowValue;
    }

    @Override
    public void run() {
        String start = Thread.currentThread().getName() + " started!";
        LOGGER.debug(start);
        int currentRow = row * 2;
        for (int i = currentRow; i <= currentRow + 1; i++) {
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                try {
                    matrix.setElement(i, j, i);
                } catch (MatrixException eValue) {
                    //LOGGER.error(eValue);
                }
            }
        }
        String stop = Thread.currentThread().getName() + " stopped!";
        LOGGER.debug(stop);
    }
}
