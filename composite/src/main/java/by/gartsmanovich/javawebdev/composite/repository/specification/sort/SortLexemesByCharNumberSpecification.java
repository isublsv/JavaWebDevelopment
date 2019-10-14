package by.gartsmanovich.javawebdev.composite.repository.specification.sort;

import by.gartsmanovich.javawebdev.composite.bean.Component;
import by.gartsmanovich.javawebdev.composite.repository.specification
        .Specification;

import java.util.Comparator;

/**
 * The specification interface realisation used to sort provided component.
 *
 * @author Dmitry Gartsmanovich
 */
public class SortLexemesByCharNumberSpecification implements
        Specification<Component> {

    /**
     * The first letter of the title.
     */
    private char letter;

    /**
     * A comparison function, which imposes a total ordering on provided
     * storage of entities.
     */
    private Comparator<Component> comparator;

    /**
     * Construct Sort Specification with provided comparator.
     *
     * @param c  the provided symbol.
     * @param comp the provided comparator.
     */
    public SortLexemesByCharNumberSpecification(final char c,
            final Comparator<Component> comp) {
        letter = c;
        comparator = comp;
    }

    /**
     * Applies specified criteria to the provided element.
     *
     * @param type        the provided type of elements.
     * @return the result string that correspond to specified criteria
     */
    @Override
    public String specified(final Component type) {
        return null;
    }
}
