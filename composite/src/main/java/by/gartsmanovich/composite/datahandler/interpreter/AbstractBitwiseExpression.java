package by.gartsmanovich.composite.datahandler.interpreter;

/**
 * The functional interface defines the base rules for interacting with the
 * client.
 *
 * @author Dmitry Gartsmanovich
 */
@FunctionalInterface
public interface AbstractBitwiseExpression {

    /**
     * Evaluates expression that was stored in the context depending on provided
     * type.
     *
     * @param context the provided context.
     */
    void interpret(Context context);
}
