package by.gartsmanovich.java_web_dev.playroom.repository.specification.find;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .FindSpecification;

public class FindByByFirstTitleLetterSpecification
        implements FindSpecification {

    /**
     * The first letter of the title.
     */
    private char letter;

    /**
     * Construct Find Specification with first title letter criteria.
     *
     * @param letterValue the first letter value
     */
    public FindByByFirstTitleLetterSpecification(final char letterValue) {
        this.letter = letterValue;
    }

    /**
     * Checks if the provided entity corresponds to specified criteria.
     *
     * @param entity the provided entity.
     * @return true if data entity corresponds to specified criteria, false -
     * otherwise.
     */
    @Override
    public boolean specified(final Toy entity) {
        return entity.getTitle().startsWith(Character.toString(letter));
    }
}
