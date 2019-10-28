package by.gartsmanovich.webparsing.bean.builder;

import by.gartsmanovich.webparsing.bean.Drug;
import by.gartsmanovich.webparsing.bean.Group;

/**
 * @author Dmitry Gartsmanovich
 */
public class DrugBuilder {

    /**
     * The instance of a drug.
     */
    private Drug drug;

    /**
     * Constructs an "empty" instance of a drug entity.
     */
    public DrugBuilder() {
        this.drug = new Drug();
    }

    /**
     * Sets the ID value for a drug.
     *
     * @param idValue the string value for a drug.
     * @return the builder instance.
     */
    public DrugBuilder setID(final long idValue) {
        drug.setId(idValue);
        return this;
    }

    /**
     * Sets the name value for a drug.
     *
     * @param nameValue the name value for a drug.
     * @return the builder instance.
     */
    public DrugBuilder setName(final String nameValue) {
        drug.setName(nameValue);
        return this;
    }

    /**
     * Sets the group value for a drug.
     *
     * @param groupValue the group value for a drug.
     * @return the builder instance.
     */
    public DrugBuilder setGroup(final Group groupValue) {
        drug.setGroup(groupValue);
        return this;
    }

    /**
     * Returns the complete instance of a drug.
     *
     * @return the drug instance.
     */
    public Drug build() {
        return this.drug;
    }
}
