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
import by.gartsmanovich.javawebdev.playroom.service.factory.param.IndexParam;

public final class ToyFactory {

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
                    .setMaterial(Material.valueOf(
                            param[IndexParam.PARAM_ADD_1.ordinal()]))
                    .setWeight(Double.parseDouble(
                            param[IndexParam.PARAM_ADD_2.ordinal()]))
                    .build();
        } else if ("ball".equalsIgnoreCase(code)) {
            toy = new ToyBallBuilder()
                    .setDiameter(Double.parseDouble(
                            param[IndexParam.PARAM_ADD_1.ordinal()]))
                    .build();
        } else if ("car".equalsIgnoreCase(code)) {
            toy = new ToyCarBuilder()
                    .setSize(Size.valueOf(param[
                            IndexParam.PARAM_ADD_1.ordinal()]))
                    .build();
        } else {
            toy = new ToyBlockBuilder()
                    .setBlockType(BlockType.valueOf(
                            param[IndexParam.PARAM_ADD_1.ordinal()]))
                    .setMaterial(Material.valueOf(
                            param[IndexParam.PARAM_ADD_2.ordinal()]))
                    .build();
        }

        toy.setTitle(param[IndexParam.PARAM_TITLE.ordinal()]);
        toy.setColor(Color.valueOf(param[IndexParam.PARAM_COLOR.ordinal()]));
        toy.setAge(Integer.parseInt(param[IndexParam.PARAM_AGE.ordinal()]));
        toy.setPrice(Double.parseDouble(param[IndexParam.PARAM_PRICE
                .ordinal()]));

        return toy;
    }

}
