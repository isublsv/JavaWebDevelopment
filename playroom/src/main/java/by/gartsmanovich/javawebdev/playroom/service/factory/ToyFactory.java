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

            String material = param[IndexParam.PARAM_ADD_1.ordinal()]
                    .toUpperCase();
            String weight = param[IndexParam.PARAM_ADD_2.ordinal()];

            toy = new DollBuilder().setMaterial(Material.valueOf(material))
                                   .setWeight(Double.parseDouble(weight))
                                   .build();

        } else if ("ball".equalsIgnoreCase(code)) {

            String diameter = param[IndexParam.PARAM_ADD_1.ordinal()];

            toy = new ToyBallBuilder()
                    .setDiameter(Double.parseDouble(diameter))
                    .build();

        } else if ("car".equalsIgnoreCase(code)) {

            String size = param[IndexParam.PARAM_ADD_1.ordinal()].toUpperCase();

            toy = new ToyCarBuilder().setSize(Size.valueOf(size))
                                     .build();

        } else {

            String blockType = param[IndexParam.PARAM_ADD_1.ordinal()]
                    .toUpperCase();

            String material = param[IndexParam.PARAM_ADD_2.ordinal()]
                    .toUpperCase();

            toy = new ToyBlockBuilder()
                    .setBlockType(BlockType.valueOf(blockType))
                    .setMaterial(Material.valueOf(material))
                    .build();
        }

        String title = param[IndexParam.PARAM_TITLE.ordinal()];
        String color = param[IndexParam.PARAM_COLOR.ordinal()].toUpperCase();
        String age = param[IndexParam.PARAM_AGE.ordinal()];
        String price = param[IndexParam.PARAM_PRICE.ordinal()];

        toy.setTitle(title);
        toy.setColor(Color.valueOf(color));
        toy.setAge(Integer.parseInt(age));
        toy.setPrice(Double.parseDouble(price));

        return toy;
    }

}
