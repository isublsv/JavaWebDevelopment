package by.gartsmanovich.java_web_dev.playroom.bean.builder;

import by.gartsmanovich.java_web_dev.playroom.bean.param.Color;
import by.gartsmanovich.java_web_dev.playroom.bean.toy.ToyBall;

public class ToyBallBuilder {

    private ToyBall ball;

    public ToyBallBuilder() {
        this.ball = new ToyBall();
    }

    public ToyBallBuilder setTitle(String s) {
        ball.setTitle(s);
        return this;
    }

    public ToyBallBuilder setColor(Color colorValue) {
        ball.setColor(colorValue);
        return this;
    }

    public ToyBallBuilder setAge(int ageValue) {
        ball.setAge(ageValue);
        return this;
    }

    public ToyBallBuilder setPrice(double priceValue) {
        ball.setPrice(priceValue);
        return this;
    }

    public ToyBallBuilder setDiameter(double diameterValue) {
        ball.setDiameter(diameterValue);
        return this;
    }


    public ToyBall build(){
        return this.ball;
    }
}
