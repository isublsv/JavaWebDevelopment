package by.gartsmanovich.javawebdev.playroom.repository.specification.sort;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.repository.specification
        .Specification;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByColorAndPriceSpecification implements Specification<Toy> {

    /**
     * A comparison function, which imposes a total ordering on provided
     * storage of entities.
     */
    private Comparator<Toy> comparator;

    /**
     * Construct Sort Specification with provided comparator.
     *
     * @param comp the provided comparator.
     */
    public SortByColorAndPriceSpecification(final Comparator<Toy> comp) {
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
