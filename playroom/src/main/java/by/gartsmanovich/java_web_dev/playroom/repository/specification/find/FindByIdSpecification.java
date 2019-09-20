package by.gartsmanovich.java_web_dev.playroom.repository.specification.find;

import by.gartsmanovich.java_web_dev.playroom.bean.toy.Toy;
import by.gartsmanovich.java_web_dev.playroom.repository.specification
        .FindSpecification;
import by.gartsmanovich.java_web_dev.playroom.repository.specification.Specification;

import java.util.List;

public class FindByIdSpecification implements Specification<Toy> {

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
     * Applies specified criteria to the provided storage.
     *
     * @param storage with provided entities.
     * @return the list of entities that correspond to specified criteria,
     * false - otherwise.
     */
    @Override
    public List<Toy> specified(final List<Toy> storage) {
        return null;
    }
}
