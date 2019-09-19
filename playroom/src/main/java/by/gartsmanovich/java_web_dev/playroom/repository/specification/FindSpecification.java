package by.gartsmanovich.java_web_dev.playroom.repository.specification;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;

public interface FindSpecification extends Specification {

    /**
     * Checks if the provided entity corresponds to specified criteria.
     *
     * @param entity the provided entity.
     * @return true if data entity corresponds to specified criteria, false -
     * otherwise.
     */
    boolean specified(Toy entity);
}
