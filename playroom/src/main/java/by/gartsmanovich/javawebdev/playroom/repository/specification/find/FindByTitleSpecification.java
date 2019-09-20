package by.gartsmanovich.javawebdev.playroom.repository.specification.find;

import by.gartsmanovich.javawebdev.playroom.bean.toy.Toy;
import by.gartsmanovich.javawebdev.playroom.repository.specification
        .Specification;

import java.util.List;
import java.util.stream.Collectors;

public class FindByTitleSpecification implements Specification<Toy> {

    /**
     * The title value.
     */
    private String title;

    /**
     * Construct Find Specification with title criteria.
     *
     * @param titleValue the title value
     */
    public FindByTitleSpecification(final String titleValue) {
        this.title = titleValue;
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
        return storage.stream().filter(toy -> toy.getTitle().equals(title))
                .collect(Collectors.toList());
    }
}
