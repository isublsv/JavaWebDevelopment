package by.gartsmanovich.javawebdev.matrix;

import by.gartsmanovich.javawebdev.matrix.exception.MatrixException;

public class MatrixRunner {

    public static void main(String[] args) throws MatrixException,
            InterruptedException {

        final int N = 10;

        Matrix matrix = new Matrix(N);

/*        MatrixCreator matrixCreator = new MatrixCreator();
        matrixCreator.fillRandomized(matrix, 10, 99);*/

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new MatrixThread(matrix, i));
            t.setName("Thread #" + i);
            t.start();
            t.join();
        }

        System.out.println(matrix.toString());

    }
}
