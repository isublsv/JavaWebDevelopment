package by.gartsmanovich.java_web_dev.playroom.repository.specification.find;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .Specification;

import java.util.List;

public class FindByTitleSpecification implements Specification<Toy> {

    /**
     * The title value.
     */
    private String title;

    /**
     * Construct Find Specification with title criteria.
     *
     * @param titleValue the title value
     */
    public FindByTitleSpecification(final String titleValue) {
        this.title = titleValue;
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
