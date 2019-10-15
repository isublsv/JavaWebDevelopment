package by.gartsmanovich.composite.datahandler.interpreter.expression;

import by.gartsmanovich.composite.datahandler.interpreter.AbstractBitwiseExpression;
import by.gartsmanovich.composite.datahandler.interpreter.Context;

/**
 * The realisation of AbstractBitwiseExpression interface that defines the
 * processing the "bitwise OR" operator.
 *
 * @author Dmitry Gartsmanovich
 */
public class NonTerminalExpressionBitwiseOr
        implements AbstractBitwiseExpression {

    /**
     * Finds the result of bitwise OR binary operation between two
     * popped values. Inserts the result to the context.
     *
     * @param context the provided context.
     */
    @Override
    public void interpret(final Context context) {
        Integer a = context.popValue();
        Integer b = context.popValue();
        context.pushValue(a | b);
    }
}
