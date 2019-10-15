package by.gartsmanovich.composite.repository.specification.sort;

import by.gartsmanovich.composite.bean.Component;
import by.gartsmanovich.composite.bean.ComponentType;
import by.gartsmanovich.composite.repository.specification.Specification;
import by.gartsmanovich.composite.service.utils.CompositeUtils;
import javafx.util.Pair;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
     * Construct Sort Specification with provided comparator.
     *
     * @param c  the provided symbol.
     */
    public SortLexemesByCharNumberSpecification(final char c) {
        letter = c;
    }

    /**
     * Applies specified criteria to the provided element.
     *
     * @param component        the provided component.
     * @return the result string that correspond to specified criteria
     */
    @Override
    public String specified(final Component component) {

        List<Pair<Component, Long>> pairs = CompositeUtils
                .getComponentsByType(component, ComponentType.LEXEME)
                .stream()
                .map(c -> new Pair<>(c, getLetterNumber(c.collect())))
                .collect(Collectors.toList());

        Comparator<Pair<Component, Long>> comp1 = Comparator
                .comparing(Pair::getValue, Comparator.reverseOrder());

        Comparator<Pair<Component, Long>> comp2 = Comparator
                .comparing(p -> p.getKey().collect());

        pairs.sort(comp1.thenComparing(comp2));

        return pairs.stream()
                    .map(Pair::getKey)
                    .map(Component::collect)
                    .collect(Collectors.joining(System.lineSeparator()));

    }

    /**
     * Returns the number of letter in the lexeme.
     *
     * @param lexeme the provided lexeme.
     * @return the number of provided letters.
     */
    private long getLetterNumber(final String lexeme) {
        return lexeme.chars().filter(c -> c == letter).count();
    }
}
