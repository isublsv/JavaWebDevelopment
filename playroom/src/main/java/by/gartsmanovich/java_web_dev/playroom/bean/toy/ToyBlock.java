package by.gartsmanovich.java_web_dev.playroom.bean.toy;

import by.gartsmanovich.java_web_dev.playroom.bean.param.BlockType;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Color;
import by.gartsmanovich.java_web_dev.playroom.bean.param.Material;

public class ToyBlock extends Toy {

    /**
     * The type of a toy block.
     */
    private BlockType blockType;

    /**
     * The material a toy block.
     */
    private Material material;

    /**
     * Default toy block constructor.
     */
    public ToyBlock() {
    }

    /**
     * Constructs an instance of a toy block with several parameters.
     *
     * @param idValue        the id of a toy block.
     * @param titleValue     the title of a toy block.
     * @param colorValue     the color of a toy block.
     * @param ageValue       the age of a kid.
     * @param priceValue     the price of a toy block.
     * @param blockTypeValue the type of a toy block.
     * @param materialValue  the material of a toy block.
     */
    public ToyBlock(final long idValue, final String titleValue, final Color
            colorValue, final int ageValue, final double priceValue, final
    BlockType blockTypeValue, final Material materialValue) {
        super(idValue, titleValue, colorValue, ageValue, priceValue);
        this.blockType = blockTypeValue;
        this.material = materialValue;
    }

    /**
     * Gets blockType.
     *
     * @return value of blockType.
     */
    public BlockType getBlockType() {
        return blockType;
    }

    /**
     * Sets blockType.
     *
     * @param blockTypeValue value of blockType.
     */
    public void setBlockType(final BlockType blockTypeValue) {
        this.blockType = blockTypeValue;
    }

    /**
     * Gets material.
     *
     * @return value of material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets material.
     *
     * @param materialValue value of material.
     */
    public void setMaterial(final Material materialValue) {
        this.material = materialValue;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        ToyBlock toyBlock = (ToyBlock) o;

        if (blockType != toyBlock.blockType) {
            return false;
        }
        return material == toyBlock.material;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + (blockType != null ? blockType.hashCode()
                : 0);
        result = PRIME * result + (material != null ? material.hashCode() : 0);
        return result;
    }

    /**
     * Represents an instance of an entity as a string value.
     *
     * @return a string representation of an entity.
     */
    @Override
    public String toString() {
        return "ToyBlock{" + "id=" + getId() + "title=" + getTitle() + ", "
                + "color=" + getColor() + ", age=" + getAge() + ", price="
                + getPrice() + ", " + "blockType=" + blockType + ", material="
                + material + '}';
    }
}
