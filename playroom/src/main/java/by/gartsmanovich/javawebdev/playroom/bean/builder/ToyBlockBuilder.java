package by.gartsmanovich.javawebdev.playroom.bean.builder;

import by.gartsmanovich.javawebdev.playroom.bean.param.BlockType;
import by.gartsmanovich.javawebdev.playroom.bean.param.Color;
import by.gartsmanovich.javawebdev.playroom.bean.param.Material;
import by.gartsmanovich.javawebdev.playroom.bean.toy.ToyBlock;

public class ToyBlockBuilder {

    /**
     * The instance of a toy block.
     */
    private ToyBlock toyBlock;

    /**
     * Constructs an "empty" instance of a toy block entity.
     */
    public ToyBlockBuilder() {
        this.toyBlock = new ToyBlock();
    }

    /**
     * Sets the title value for a toy block.
     *
     * @param titleValue the string value for a toy block
     * @return the builder instance
     */
    public ToyBlockBuilder setTitle(final String titleValue) {
        toyBlock.setTitle(titleValue);
        return this;
    }

    /**
     * Sets the color value for a toy block.
     *
     * @param colorValue the color value for a toy block.
     * @return the builder instance.
     */
    public ToyBlockBuilder setColor(final Color colorValue) {
        toyBlock.setColor(colorValue);
        return this;
    }

    /**
     * Sets the age value for a toy block.
     *
     * @param ageValue the age value for a toy block.
     * @return the builder instance.
     */
    public ToyBlockBuilder setAge(final int ageValue) {
        toyBlock.setAge(ageValue);
        return this;
    }

    /**
     * Sets the price for a toy block.
     *
     * @param priceValue the price value for a toy block.
     * @return the builder instance.
     */
    public ToyBlockBuilder setPrice(final double priceValue) {
        toyBlock.setPrice(priceValue);
        return this;
    }

    /**
     * Sets the material of a toy block.
     *
     * @param materialValue the material value of a toy block.
     * @return the builder instance.
     */
    public ToyBlockBuilder setMaterial(final Material materialValue) {
        toyBlock.setMaterial(materialValue);
        return this;
    }

    /**
     * Sets the block type of a toy block.
     *
     * @param blockTypeValue the block type value of a toy block.
     * @return the builder instance.
     */
    public ToyBlockBuilder setBlockType(final BlockType blockTypeValue) {
        toyBlock.setBlockType(blockTypeValue);
        return this;
    }

    /**
     * Returns the complete instance of a toy block.
     *
     * @return the toy block instance.
     */
    public ToyBlock build() {
        return this.toyBlock;
    }
}
