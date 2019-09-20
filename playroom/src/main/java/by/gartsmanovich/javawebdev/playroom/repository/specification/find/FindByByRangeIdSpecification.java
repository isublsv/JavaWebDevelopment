package by.gartsmanovich.javawebdev.playroom.repository.specification.find;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.repository.specification
        .Specification;

import java.util.List;
import java.util.stream.Collectors;

public class FindByByRangeIdSpecification implements Specification<Toy> {

    /**
     * The start of the ID range.
     */
    private long startId;

    /**
     * The end of the ID range.
     */
    private long endId;

    /**
     * Construct Find Specification with ID criteria.
     *
     * @param start the start of the ID range.
     * @param end   the end of the ID range.
     */
    public FindByByRangeIdSpecification(final long start, final long end) {
        this.startId = start;
        this.endId = end;
    }

    /**
     * Checks if the provided entity corresponds to specified criteria.
     *
     * @param storage the lisprovided entity.
     * @return true if data entity corresponds to specified criteria, false -
     * otherwise.
     */
    @Override
    public List<Toy> specified(final List<Toy> storage) {
        return storage.stream()
                      .filter(toy -> toy.getId() >= startId
                              && toy.getId()  <= endId)
                      .collect(Collectors.toList());
    }
}
