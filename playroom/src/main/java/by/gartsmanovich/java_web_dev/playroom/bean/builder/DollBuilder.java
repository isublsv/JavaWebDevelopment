package by.gartsmanovich.java_web_dev.playroom.bean.builder;

import by.gartsmanovich.java_web_dev.playroom.bean.param.Color;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Material;
import by.gartsmanovich.java_web_dev.playroom.bean.toy.Doll;

public class DollBuilder {

    private Doll doll;

    public DollBuilder() {
        this.doll = new Doll();
    }

    public DollBuilder setTitle(String s) {
        doll.setTitle(s);
        return this;
    }

    public DollBuilder setColor(Color colorValue) {
        doll.setColor(colorValue);
        return this;
    }

    public DollBuilder setAge(int ageValue) {
        doll.setAge(ageValue);
        return this;
    }

    public DollBuilder setPrice(double priceValue) {
        doll.setPrice(priceValue);
        return this;
    }

    public DollBuilder setMaterial(Material materialValue) {
        doll.setMaterial(materialValue);
        return this;
    }

    public DollBuilder setWeight(double weightValue) {
        doll.setWeight(weightValue);
        return this;
    }

    public Doll build(){
        return this.doll;
    }
}
