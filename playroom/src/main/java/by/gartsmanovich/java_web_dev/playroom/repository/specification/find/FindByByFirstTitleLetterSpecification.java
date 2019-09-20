package by.gartsmanovich.java_web_dev.playroom.repository.specification.find;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .Specification;

import java.util.List;

public class FindByByFirstTitleLetterSpecification
        implements Specification<Toy> {

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
     * Applies specified criteria to the provided storage.
     *
     * @param storage with provided entities.
     * @return the list of entities that correspond to specified criteria,
     * false - otherwise.
     */
    @Override
    public List<Toy> specified(final List<Toy> storage) {
        return null;
    }
}
