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

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Symbol symbol = (Symbol) o;

        return s == symbol.s;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return s;
    }
}
