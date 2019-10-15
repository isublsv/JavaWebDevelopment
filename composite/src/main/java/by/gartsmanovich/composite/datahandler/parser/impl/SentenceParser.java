package by.gartsmanovich.composite.datahandler.parser.impl;

import by.gartsmanovich.composite.bean.Component;
import by.gartsmanovich.composite.bean.ComponentType;
import by.gartsmanovich.composite.bean.Composite;
import by.gartsmanovich.composite.datahandler.exception.ParseException;
import by.gartsmanovich.composite.datahandler.parser.AbstractParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The SentenceParser class represents the AbstractParser class realisation.
 * Used to parse the provided string into the components such as sentence
 * using regular expression.
 *
 * @author Dmitry Gartsmanovich
 */
public class SentenceParser extends AbstractParser {

    /**
     * The regular expression used to determine the sentences in the provided
     * message.
     */
    private static final String SENTENCE_REGEX = "[\\S][^.!?]*[.!?]{0,3}";

    /**
     * Returns the component that appropriate to the concrete requirements.
     *
     * @param message the provided message to parse.
     * @return the concrete component.
     * @throws ParseException if error happens during parsing.
     */
    @Override
    public Component parse(final String message) throws ParseException {
        if (getNext() == null) {
            throw new ParseException("There is no the next parser!");
        }
        Composite composite = new Composite(ComponentType.SENTENCE);

        Matcher matcher = Pattern.compile(SENTENCE_REGEX).matcher(message);
        while (matcher.find()) {
            String sentence = matcher.group();
            composite.add(getNext().parse(sentence));
        }
        return composite;
    }
}
