package by.gartsmanovich.composite.datahandler.parser.impl;

import by.gartsmanovich.composite.bean.Component;
import by.gartsmanovich.composite.bean.ComponentType;
import by.gartsmanovich.composite.bean.Composite;
import by.gartsmanovich.composite.bean.Symbol;
import by.gartsmanovich.composite.datahandler.exception.ParseException;
import by.gartsmanovich.composite.datahandler.parser.AbstractParser;

/**
 * The SymbolParser class represents the AbstractParser class realisation.
 * Used to parse the provided string into the components such as symbols.
 *
 * @author Dmitry Gartsmanovich
 */
public class SymbolParser extends AbstractParser {


    /**
     * Returns the component that appropriate to the concrete requirements.
     *
     * @param message the provided message to parse.
     * @return the concrete component.
     * @throws ParseException if error happens during parsing.
     */
    @Override
    public Component parse(final String message) throws ParseException {
        Composite composite = new Composite(ComponentType.SYMBOL);

        for (Character character : message.toCharArray()) {
            composite.add(new Symbol(character));
        }
        return composite;
    }
}
