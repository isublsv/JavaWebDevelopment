package by.gartsmanovich.java_web_dev.playroom.repository.specification.find;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .FindSpecification;

public class FindByTitleSpecification implements FindSpecification {

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
     * Checks if the provided entity corresponds to specified criteria.
     *
     * @param entity the provided entity.
     * @return true if data entity corresponds to specified criteria, false -
     * otherwise.
     */
    @Override
    public boolean specified(final Toy entity) {
        return entity.getTitle().equals(this.title);
    }
}
