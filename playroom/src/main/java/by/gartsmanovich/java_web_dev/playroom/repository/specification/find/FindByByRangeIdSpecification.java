package by.gartsmanovich.java_web_dev.playroom.repository.specification.find;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .FindSpecification;

public class FindByByRangeIdSpecification implements FindSpecification {

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
     * @param end the end of the ID range.
     */
    public FindByByRangeIdSpecification(final long start, final long end) {
        this.startId = start;
        this.endId = end;
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
        return entity.getId() >= startId && entity.getId() <= endId;
    }
}
