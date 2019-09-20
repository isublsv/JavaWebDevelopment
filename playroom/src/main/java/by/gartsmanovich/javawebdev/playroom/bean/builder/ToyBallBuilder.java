package by.gartsmanovich.javawebdev.playroom.bean.builder;

import by.gartsmanovich.javawebdev.playroom.bean.param.Color;
import by.gartsmanovich.javawebdev.playroom.bean.toy.ToyBall;

public class ToyBallBuilder {

    /**
     * The instance of a toy ball.
     */
    private ToyBall ball;

    /**
     * Constructs an "empty" instance of a toy ball entity.
     */
    public ToyBallBuilder() {
        this.ball = new ToyBall();
    }

    /**
     * Sets the title value for a toy ball.
     *
     * @param titleValue the string value for a toy ball
     * @return the builder instance
     */
    public ToyBallBuilder setTitle(final String titleValue) {
        ball.setTitle(titleValue);
        return this;
    }

    /**
     * Sets the color value for a toy ball.
     *
     * @param colorValue the color value for a toy ball.
     * @return the builder instance.
     */
    public ToyBallBuilder setColor(final Color colorValue) {
        ball.setColor(colorValue);
        return this;
    }

    /**
     * Sets the age value for a toy ball.
     *
     * @param ageValue the age value for a toy ball.
     * @return the builder instance.
     */
    public ToyBallBuilder setAge(final int ageValue) {
        ball.setAge(ageValue);
        return this;
    }

    /**
     * Sets the price for a toy ball.
     *
     * @param priceValue the price value for a toy ball.
     * @return the builder instance.
     */
    public ToyBallBuilder setPrice(final double priceValue) {
        ball.setPrice(priceValue);
        return this;
    }

    /**
     * Sets the material of a toy ball.
     *
     * @param diameterValue the material value of a toy ball.
     * @return the builder instance.
     */
    public ToyBallBuilder setDiameter(final double diameterValue) {
        ball.setDiameter(diameterValue);
        return this;
    }

    /**
     * Returns the complete instance of a toy ball.
     *
     * @return the toy ball instance.
     */
    public ToyBall build() {
        return this.ball;
    }
}
