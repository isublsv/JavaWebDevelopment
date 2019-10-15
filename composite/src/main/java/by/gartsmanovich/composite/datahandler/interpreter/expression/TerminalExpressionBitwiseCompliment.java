package by.gartsmanovich.composite.datahandler.interpreter.expression;

import by.gartsmanovich.composite.datahandler.interpreter.AbstractBitwiseExpression;
import by.gartsmanovich.composite.datahandler.interpreter.Context;

/**
 * The realisation of AbstractBitwiseExpression interface that defines the
 * processing the "bitwise compliment" operator.
 *
 * @author Dmitry Gartsmanovich
 */
public class TerminalExpressionBitwiseCompliment implements
        AbstractBitwiseExpression {

    /**
     * Finds the result of bitwise compliment binary operation between two
     * popped values. Inserts the result to the context.
     *
     * @param context the provided context.
     */
    @Override
    public void interpret(final Context context) {
        context.pushValue(~context.popValue());
    }
}
