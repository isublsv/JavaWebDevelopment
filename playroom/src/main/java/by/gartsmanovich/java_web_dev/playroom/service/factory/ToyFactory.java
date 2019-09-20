package by.gartsmanovich.java_web_dev.playroom.service.factory;

import by.gartsmanovich.java_web_dev.playroom.bean.builder.DollBuilder;
import by.gartsmanovich.java_web_dev.playroom.bean.builder.ToyBallBuilder;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Color;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Material;
import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;

public class ToyFactory {

    public static final ToyFactory INSTANCE = new ToyFactory();

    public static ToyFactory getInstance() {
        return INSTANCE;
    }

    public Toy createToy(String[] p) {
        String code = p[0];
        switch (code.toLowerCase()) {
            case "doll":
                return new DollBuilder().setTitle(p[0])
                                        .setColor(Color.valueOf(p[1]))
                                        .setAge(Integer.parseInt(p[2]))
                                        .setPrice(Double.parseDouble(p[3]))
                                        .setMaterial(Material.valueOf(p[4]))
                                        .setWeight(Double.parseDouble(p[5]))
                                        .build();
            case "ball":
                return new ToyBallBuilder().setColor(Color.valueOf(p[1]))
                                           .setAge(Integer.parseInt(p[2]))
                                           .setPrice(Double.parseDouble(p[3]))
                                           .setDiameter(Double.parseDouble(p[4]))
                                           .build();

            default:

                throw new IllegalArgumentException();
        }
    }


}
