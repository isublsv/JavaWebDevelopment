package by.gartsmanovich.javawebdev.playroom.repository.specification.find;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.repository.specification
        .Specification;

import java.util.List;
import java.util.stream.Collectors;

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
        return storage.stream()
                      .filter(toy -> toy.getTitle()
                                        .startsWith(Character.toString(letter)
                                                             .toLowerCase()))
                      .collect(Collectors.toList());
    }
}
