package by.gartsmanovich.javawebdev.matrix.service.validator;

import java.util.List;

public class Validator {

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final String value) {
        return value != null && !value.isEmpty();
    }

    /**
     * Checks if the value is a number.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isNumber(final String value) {
        if (isValidValue(value)) {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the list of string is a square matrix.
     *
     * @param list      the list to valid.
     * @param delimiter the delimiter that splits the string if matches.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isSquareMatrix(final List<String> list,
            final String delimiter) {

        int verticalSize = list.size();
        int horizontalSize = list.get(0).split(delimiter).length;

        for (String s : list) {
            String[] row = s.split(delimiter);
            if (row.length != verticalSize) {
                return false;
            }
        }
        return verticalSize == horizontalSize;
    }
}
