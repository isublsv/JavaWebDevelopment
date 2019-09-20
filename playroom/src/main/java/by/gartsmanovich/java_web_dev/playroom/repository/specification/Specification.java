package by.gartsmanovich.java_web_dev.playroom.repository.specification;

import by.gartsmanovich.java_web_dev.playroom.bean.Entity;

import java.util.List;

public interface Specification<T extends Entity> {

    /**
     * Applies specified criteria to the provided storage.
     *
     * @param storage with provided entities.
     * @return the list of entities that correspond to specified criteria,
     * false - otherwise.
     */
    List<T> specified(List<T> storage);
}
