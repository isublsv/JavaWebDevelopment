package by.gartsmanovich.javawebdev.matrix;

import by.gartsmanovich.javawebdev.matrix.exception.MatrixException;

import java.util.Random;

public class MatrixCreator {

    private Random random = new Random();

    public void fillRandomized(Matrix t, int start, int end) {

        int v = t.getVerticalSize();
        int h = t.getHorizontalSize();
        for(int i = 0; i < v; i++) {
            for(int j = 0; j < h; j++) {
                try {
                    int value = random.nextInt((end - start) + 1) + start;
                    t.setElement(i, j, value);
                } catch (MatrixException ignored) {

                }
            }
        }
    }
}
