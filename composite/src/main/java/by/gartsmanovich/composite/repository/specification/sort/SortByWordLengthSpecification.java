package by.gartsmanovich.composite.repository.specification.sort;

import by.gartsmanovich.composite.bean.Component;
import by.gartsmanovich.composite.bean.ComponentType;
import by.gartsmanovich.composite.repository.specification.Specification;
import by.gartsmanovich.composite.service.utils.CompositeUtils;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The specification interface realisation used to sort provided component.
 *
 * @author Dmitry Gartsmanovich
 */
public class SortByWordLengthSpecification implements Specification<Component> {

    /**
     * Applies specified criteria to the provided element.
     *
     * @param component        the provided component.
     * @return the result string that correspond to specified criteria
     */
    @Override
    public String specified(final Component component) {

        CompositeUtils.clearList();

        return CompositeUtils.getComponentsByType(component,
                                                  ComponentType.WORD).stream()
                    .sorted(Comparator.comparingInt(c -> c.collect().length()))
                    .map(Component::collect)
                    .collect(Collectors.joining(System.lineSeparator()));
    }
}
