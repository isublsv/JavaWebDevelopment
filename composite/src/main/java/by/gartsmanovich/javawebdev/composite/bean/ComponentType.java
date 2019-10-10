package by.gartsmanovich.javawebdev.composite.bean;

/**
 * Enum-class represents the list of components that exists in the application.
 *
 * @author Dmitry Gartsmanovich
 */
public enum ComponentType {

    /**
     * The singleton instance for the paragraph.
     * This has the numeric value of {@code 0}.
     */
    PARAGRAPH,

    /**
     * The singleton instance for the sentence.
     * This has the numeric value of {@code 1}.
     */
    SENTENCE,

    /**
     * The singleton instance for the lexeme.
     * This has the numeric value of {@code 2}.
     */
    LEXEME,

    /**
     * The singleton instance for the word.
     * This has the numeric value of {@code 3}.
     */
    WORD,

    /**
     * The singleton instance for the word.
     * This has the numeric value of {@code 4}.
     */
    EXPRESSION,

    /**
     * The singleton instance for the symbol.
     * This has the numeric value of {@code 5}.
     */
    SYMBOL;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
