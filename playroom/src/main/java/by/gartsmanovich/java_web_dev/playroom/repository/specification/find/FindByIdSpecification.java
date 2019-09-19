package by.gartsmanovich.java_web_dev.playroom.repository.specification.find;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .FindSpecification;

public class FindByIdSpecification implements FindSpecification {

    /**
     * The ID value.
     */
    private long id;

    /**
     * Construct Find Specification with ID criteria.
     *
     * @param idValue the ID value
     */
    public FindByIdSpecification(final long idValue) {
        this.id = idValue;
    }

    /**
     * Checks if provided entity ID is equals to current.
     *
     * @param entity the provided entity.
     * @return true if IDs are equal, false - otherwise.
     */
    @Override
    public boolean specified(final Toy entity) {
        return entity.getId() == id;
    }
}
