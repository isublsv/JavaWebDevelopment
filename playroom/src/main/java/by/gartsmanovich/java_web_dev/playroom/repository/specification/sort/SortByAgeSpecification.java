package by.gartsmanovich.java_web_dev.playroom.repository.specification.sort;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .Specification;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByAgeSpecification implements Specification<Toy> {

    private Comparator<Toy> comparator;

    public SortByAgeSpecification(final Comparator<Toy> comp) {
        this.comparator = comp;
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
        return storage.stream().sorted(comparator).collect(Collectors.toList());
    }
}
