package by.gartsmanovich.javawebdev.playroom.repository.specification.find;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.repository.specification
        .Specification;

import java.util.List;

public class FindAllSpecification implements Specification<Toy> {

    /**
     * Applies specified criteria to the provided storage.
     *
     * @param storage with provided entities.
     * @return the list of entities that correspond to specified criteria,
     * false - otherwise.
     */
    @Override
    public List<Toy> specified(final List<Toy> storage) {
        return storage;
    }
}
