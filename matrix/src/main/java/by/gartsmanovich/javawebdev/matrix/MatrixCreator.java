package by.gartsmanovich.javawebdev.matrix;

import by.gartsmanovich.javawebdev.matrix.datahandler.DataReader;
import by.gartsmanovich.javawebdev.matrix.datahandler.exception
        .DataHandlerException;
import by.gartsmanovich.javawebdev.matrix.datahandler.impl.DataReaderImpl;
import by.gartsmanovich.javawebdev.matrix.datahandler.validator.Validator;
import by.gartsmanovich.javawebdev.matrix.exception.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class MatrixCreator {

    private static final Logger LOGGER = LogManager.getLogger(
            MatrixCreator.class);

    private Random random;

    private Validator validator;

    public MatrixCreator() {
         random = new Random();
         validator = new Validator();
    }


    public void fillRandomized(Matrix matrix, int start, int end) {

        int v = matrix.getVerticalSize();
        int h = matrix.getHorizontalSize();
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < h; j++) {
                try {
                    int value = random.nextInt((end - start) + 1) + start;
                    matrix.setElement(i, j, value);
                } catch (MatrixException e) {
                    LOGGER.debug(e);
                }
            }
        }
    }

    public void fillFromFile(Matrix matrix, String path) throws
            MatrixException {
        DataReader dataReader = new DataReaderImpl();

        try {
            List<String> strings = dataReader.readFile(path);

            int v = matrix.getVerticalSize();
            int h = matrix.getHorizontalSize();

            for (String s : strings){
                String[] numbers = s.split(" ");

                for (int i = 0; i < v; i++) {
                    for (int j = 0; j < h; j++) {
                        matrix.setElement(i, j, Integer.parseInt(numbers[j]));
                    }
                }
            }

        } catch (DataHandlerException e) {
            LOGGER.error(e);
            throw new MatrixException(e.getMessage(), e);
        }
    }
}
