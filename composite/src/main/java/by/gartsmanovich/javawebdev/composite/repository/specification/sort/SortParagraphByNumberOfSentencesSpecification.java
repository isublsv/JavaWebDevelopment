package by.gartsmanovich.javawebdev.composite.repository.specification.sort;

import by.gartsmanovich.javawebdev.composite.bean.Component;
import by.gartsmanovich.javawebdev.composite.repository.specification
        .Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The specification interface realisation used to sort provided component.
 *
 * @author Dmitry Gartsmanovich
 */
public class SortParagraphByNumberOfSentencesSpecification implements
        Specification<Component> {

    /**
     * A comparison function, which imposes a total ordering on provided
     * storage of entities.
     */
    private Comparator<Component> comparator;

    /**
     * Construct Sort Specification with provided comparator.
     *
     * @param comp the provided comparator.
     */
    public SortParagraphByNumberOfSentencesSpecification(
            final Comparator<Component> comp) {
        comparator = comp;
    }

    /**
     * Applies specified criteria to the provided element.
     *
     * @param component        the provided type of elements.
     * @return the result string that correspond to specified criteria
     */
    @Override
    public String specified(final Component component) {

        List<Component> paragraphs = new ArrayList<>(
                component.getComponents());

        return paragraphs.stream()
                         .sorted(comparator)
                         .map(Component::collect)
                         .collect(Collectors.joining(System.lineSeparator()));
    }
}
