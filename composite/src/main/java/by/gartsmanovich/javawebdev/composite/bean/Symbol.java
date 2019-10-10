package by.gartsmanovich.javawebdev.composite.bean;

/**
 * The class describes indivisible structural elements of the composite.
 *
 * @author Dmitry Gartsmanovich
 */
public class Symbol implements Component {

    /**
     * Any symbol that was received during parsing.
     */
    private char s;

    /**
     * Constructs an instance with the specified symbol.
     *
     * @param symbol the provided symbol.
     */
    public Symbol(final char symbol) {
        s = symbol;
    }

    /**
     * Gets symbol.
     *
     * @return value of symbol.
     */
    public char getSymbol() {
        return s;
    }

    /**
     * Returns type of the component.
     *
     * @return type of the component.
     */
    @Override
    public ComponentType getType() {
        return ComponentType.SYMBOL;
    }
}
