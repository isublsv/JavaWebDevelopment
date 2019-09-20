package by.gartsmanovich.javawebdev.playroom.service.factory;

import by.gartsmanovich.javawebdev.playroom.bean.builder.DollBuilder;
import by.gartsmanovich.javawebdev.playroom.bean.builder.ToyBallBuilder;
import by.gartsmanovich.javawebdev.playroom.bean.builder.ToyBlockBuilder;
import by.gartsmanovich.javawebdev.playroom.bean.builder.ToyCarBuilder;
import by.gartsmanovich.javawebdev.playroom.bean.param.BlockType;
import by.gartsmanovich.javawebdev.playroom.bean.param.Color;
import by.gartsmanovich.javawebdev.playroom.bean.param.Material;
import by.gartsmanovich.javawebdev.playroom.bean.param.Size;
import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;

public final class ToyFactory {

    /**
     * Describes index for the title parameter.
     */
    private static final int PARAM_TITLE = 0;

    /**
     * Describes index for the color parameter.
     */
    private static final int PARAM_COLOR = 1;

    /**
     * Describes index for the age parameter.
     */
    private static final int PARAM_AGE = 2;

    /**
     * Describes index for the price parameter.
     */
    private static final int PARAM_PRICE = 3;

    /**
     * Describes index for the first additional parameter.
     */
    private static final int PARAM_ADD_1 = 4;

    /**
     * Describes index for the second additional parameter.
     */
    private static final int PARAM_ADD_2 = 5;

    /**
     * Creates a Factory instance at the start of the class initialisation.
     */
    private static final ToyFactory INSTANCE = new ToyFactory();

    private ToyFactory() {
    }

    /**
     * Global point for access to factory method.
     *
     * @return the instance of Toy factory.
     */
    public static ToyFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a toy entity with provided parameters.
     *
     * @param param an array of parameters.
     * @return the instance of a toy.
     */
    public Toy createToy(final String[] param) {
        String code = param[0];
        Toy toy;

        if ("doll".equalsIgnoreCase(code)) {
            toy = new DollBuilder()
                    .setMaterial(Material.valueOf(param[PARAM_ADD_1]))
                    .setWeight(Double.parseDouble(param[PARAM_ADD_2]))
                    .build();
        } else if ("ball".equalsIgnoreCase(code)) {
            toy = new ToyBallBuilder()
                    .setDiameter(Double.parseDouble(param[PARAM_ADD_1]))
                    .build();
        } else if ("car".equalsIgnoreCase(code)) {
            toy = new ToyCarBuilder()
                    .setSize(Size.valueOf(param[PARAM_ADD_1]))
                    .build();
        } else {
            toy = new ToyBlockBuilder()
                    .setBlockType(BlockType.valueOf(param[PARAM_ADD_1]))
                    .setMaterial(Material.valueOf(param[PARAM_ADD_2]))
                    .build();
        }

        toy.setTitle(param[PARAM_TITLE]);
        toy.setColor(Color.valueOf(param[PARAM_COLOR]));
        toy.setAge(Integer.parseInt(param[PARAM_AGE]));
        toy.setPrice(Double.parseDouble(param[PARAM_PRICE]));

        return toy;
    }

}
