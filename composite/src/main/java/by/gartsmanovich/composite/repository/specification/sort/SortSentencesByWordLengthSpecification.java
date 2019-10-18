package by.gartsmanovich.composite.repository.specification.sort;

import by.gartsmanovich.composite.bean.Component;
import by.gartsmanovich.composite.bean.ComponentType;
import by.gartsmanovich.composite.repository.specification.Specification;
import by.gartsmanovich.composite.service.util.CompositeUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The specification interface realisation used to sort provided component.
 *
 * @author Dmitry Gartsmanovich
 */
public class SortSentencesByWordLengthSpecification
        implements Specification<Component> {

    /**
     * Applies specified criteria to the provided element.
     *
     * @param component        the provided component.
     * @return the result string that correspond to specified criteria
     */
    @Override
    public String specified(final Component component) {

        CompositeUtils.clearList();
        List<Component> sentences = new ArrayList<>(
                CompositeUtils.getComponentsByType(component,
                                                   ComponentType.SENTENCE));

        StringBuilder result = new StringBuilder();
        CompositeUtils.clearList();

        for (Component sentence : sentences) {
            List<Component> words = new ArrayList<>(
                    CompositeUtils.getComponentsByType(sentence,
                                                       ComponentType.WORD));
            CompositeUtils.clearList();
            words.sort(Comparator.comparingInt(c -> c.collect().length()));
            for (Component word : words) {
                result.append(word.collect()).append(" ");
            }
            result.append(System.lineSeparator());
        }

        return result.toString();
    }
}
