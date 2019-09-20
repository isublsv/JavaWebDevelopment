package by.gartsmanovich.javawebdev.playroom.repository.specification.find;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.repository.specification.Specification;

import java.util.List;
import java.util.stream.Collectors;

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
        return storage.stream()
                      .filter(toy -> toy.getId() == id)
                .collect(Collectors.toList());
    }
}
