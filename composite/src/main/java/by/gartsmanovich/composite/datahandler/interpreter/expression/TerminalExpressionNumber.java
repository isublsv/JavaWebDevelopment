package by.gartsmanovich.composite.datahandler.interpreter.expression;

import by.gartsmanovich.composite.datahandler.interpreter
        .AbstractBitwiseExpression;
import by.gartsmanovich.composite.datahandler.interpreter.Context;

/**
 * The realisation of AbstractBitwiseExpression interface that defines the
 * processing the numbers.
 *
 * @author Dmitry Gartsmanovich
 */
public class TerminalExpressionNumber implements AbstractBitwiseExpression {

    /**
     * The found number in the expression.
     */
    private int number;

    /**
     * Constructs the instance with specific parameter.
     *
     * @param numberValue the found number in the expression.
     */
    public TerminalExpressionNumber(final int numberValue) {
        number = numberValue;
    }

    /**
     * Inserts the found number to the context.
     *
     * @param context the provided context.
     */
    @Override
    public void interpret(final Context context) {
        context.pushValue(number);
    }
}
