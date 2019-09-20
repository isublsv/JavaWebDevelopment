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
    private Comparator<Toy> firstComp;

    /**
     * A comparison function, which imposes a total ordering on provided
     * storage of entities.
     */
    private Comparator<Toy> secondComp;

    /**
     * Construct Sort Specification with provided comparators.
     *
     * @param firstCompValue the first provided comparator.
     * @param secondCompValue the second provided comparator.
     */
    public SortByColorAndPriceSpecification(
            final Comparator<Toy> firstCompValue,
            final Comparator<Toy> secondCompValue) {
        this.firstComp = firstCompValue;
        this.secondComp = secondCompValue;
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
        return storage.stream().sorted(firstComp.thenComparing(secondComp))
                      .collect(Collectors.toList());
    }
}
