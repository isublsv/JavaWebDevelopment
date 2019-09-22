package by.gartsmanovich.javawebdev.playroom.service.validator;

import by.gartsmanovich.javawebdev.playroom.bean.param.BlockType;
import by.gartsmanovich.javawebdev.playroom.bean.param.Color;
import by.gartsmanovich.javawebdev.playroom.bean.param.Material;
import by.gartsmanovich.javawebdev.playroom.bean.param.Size;
import by.gartsmanovich.javawebdev.playroom.service.factory.param.IndexParam;

import java.util.Arrays;

public class Validator {

    /**
     * Describes the number of parameters for the toy car and toy ball entities.
     */
    private static final int CAR_BALL_PARAM_NUM = 5;

    /**
     * Describes the number of parameters for the doll and toy block entities.
     */
    private static final int DOLL_BLOCK_PARAM_NUM = 6;

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final double value) {
        return value > 0 && value < Double.MAX_VALUE;
    }

    /**
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final long value) {
        return value > 0 && value < Long.MAX_VALUE;
    }

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
     * Checks if the value is valid.
     *
     * @param value the value to valid.
     * @return true if value is valid, false - otherwise.
     */
    public boolean isValidValue(final char value) {
        return Character.isLetter(value);
    }

    /**
     * Checks if the values are valid.
     *
     * @param startId the first value to valid.
     * @param endId   the second value to valid.
     * @return true if values are valid, false - otherwise.
     */
    public boolean isValidValue(final long startId, final long endId) {
        long diff = endId - startId;
        return (startId > 0) && (startId < Long.MAX_VALUE) && (endId > 0) && (
                endId < Long.MAX_VALUE) && (diff >= 0);
    }

    /**
     * Checks if string array has valid data.
     *
     * @param param the string array.
     * @return true if values are valid, false - otherwise.
     */
    public boolean isValidEntityParams(final String[] param) {

        String title = param[0];
        if (isValidValue(title)) {
            switch (title) {
                case "car":
                    return isToyCarEntity(param);
                case "doll":
                    return isDollEntity(param);
                case "ball":
                    return isToyBallEntity(param);
                case "block":
                    return isToyBlockEntity(param);
                default:
                    return false;
            }
        } else {
            return false;
        }
    }


    private boolean isToyBlockEntity(final String[] param) {
        if (param.length == DOLL_BLOCK_PARAM_NUM && isToy(param)) {
            String blockType = param[IndexParam.PARAM_ADD_1.ordinal()];
            String material = param[IndexParam.PARAM_ADD_2.ordinal()];
            return isBlockTypeValue(blockType) && isMaterialValue(material);
        } else {
            return false;
        }
    }

    private boolean isToyBallEntity(final String[] param) {
        if (param.length == CAR_BALL_PARAM_NUM && isToy(param)) {
            try {
                String diameter = param[IndexParam.PARAM_ADD_1.ordinal()];
                return isValidValue(Double.parseDouble(diameter));
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isDollEntity(final String[] param) {
        if (param.length == DOLL_BLOCK_PARAM_NUM && isToy(param)) {
            try {
                String material = param[IndexParam.PARAM_ADD_1.ordinal()];
                String weight = param[IndexParam.PARAM_ADD_2.ordinal()];
                return isMaterialValue(material) && isValidValue(
                        Double.parseDouble(weight));
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isToyCarEntity(final String[] param) {
        if (param.length == CAR_BALL_PARAM_NUM && isToy(param)) {
            String size = param[IndexParam.PARAM_ADD_1.ordinal()];
            return isSizeValue(size);
        } else {
            return false;
        }
    }

    private boolean isToy(final String[] param) {
        String color = param[IndexParam.PARAM_COLOR.ordinal()];
        String age = param[IndexParam.PARAM_AGE.ordinal()];
        String price = param[IndexParam.PARAM_PRICE.ordinal()];
        try {
            return isColorValue(color) && isValidValue(Integer.parseInt(age))
                   && isValidValue(Double.parseDouble(price));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBlockTypeValue(final String blockType) {
        return Arrays.stream(BlockType.values()).anyMatch(
                t -> t.name().equalsIgnoreCase(blockType));
    }

    private boolean isMaterialValue(final String material) {
        return Arrays.stream(Material.values()).anyMatch(
                t -> t.name().equalsIgnoreCase(material));
    }

    private boolean isSizeValue(final String size) {
        return Arrays.stream(Size.values()).anyMatch(
                t -> t.name().equalsIgnoreCase(size));
    }


    private boolean isColorValue(final String color) {
        return Arrays.stream(Color.values()).anyMatch(
                t -> t.name().equalsIgnoreCase(color));
    }
}
