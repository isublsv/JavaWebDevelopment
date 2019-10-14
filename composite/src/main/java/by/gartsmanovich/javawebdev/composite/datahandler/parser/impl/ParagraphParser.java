package by.gartsmanovich.javawebdev.composite.datahandler.parser.impl;

import by.gartsmanovich.javawebdev.composite.bean.Component;
import by.gartsmanovich.javawebdev.composite.bean.ComponentType;
import by.gartsmanovich.javawebdev.composite.bean.Composite;
import by.gartsmanovich.javawebdev.composite.datahandler.exception
        .ParseException;
import by.gartsmanovich.javawebdev.composite.datahandler.parser.AbstractParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ParagraphParser class represents the AbstractParser class realisation.
 * Used to parse the provided string into the components such as paragraph
 * using regular expression.
 *
 * @author Dmitry Gartsmanovich
 */
public class ParagraphParser extends AbstractParser {

    /**
     * The regular expression used to determine the paragraphs in the provided
     * message.
     */
    private static final String PARAGRAPH_REGEX = "([A-Z].+(?=\\s)|[\\n]$)";
    //(\n?\s+(.*))
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
        Composite composite = new Composite(ComponentType.PARAGRAPH);

        Matcher matcher = Pattern.compile(PARAGRAPH_REGEX, Pattern.MULTILINE)
                                 .matcher(message);
        while (matcher.find()) {
            String paragraph = matcher.group();
            composite.add(getNext().parse(paragraph));
        }
        return composite;
    }
}
