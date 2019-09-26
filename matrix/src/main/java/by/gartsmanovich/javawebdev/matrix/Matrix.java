package by.gartsmanovich.javawebdev.matrix;

import by.gartsmanovich.javawebdev.matrix.exception.MatrixException;

public class Matrix {

    private int[][] matrix;

    public Matrix(final int n) throws MatrixException {
        if (n < 1) {
            throw new MatrixException();
        }
        matrix = new int[n][n];
    }

    public int getVerticalSize() {
        return matrix.length;
    }

    public int getHorizontalSize() {
        return matrix[0].length;
    }

    public int getElement(int i, int j) throws MatrixException {
        if (checkRange(i, j)) {
            return matrix[i][j];
        }
        throw new MatrixException();
    }

    public void setElement(final int i, final int j, final int value) throws
            MatrixException {
        if (checkRange(i, j)) {
            matrix[i][j] = value;
        }
        throw new MatrixException();
    }

    private boolean checkRange(int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(
                "\nMatrix : " + matrix.length + "x" + matrix[0].length + "\n");
        for (int[] row : matrix) {
            for (int value : row) {
                s.append(value).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
