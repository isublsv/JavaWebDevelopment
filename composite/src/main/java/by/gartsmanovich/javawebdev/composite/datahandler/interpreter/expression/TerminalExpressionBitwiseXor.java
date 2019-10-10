package by.gartsmanovich.javawebdev.composite.datahandler.interpreter.expression;

import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .AbstractBitwiseExpression;
import by.gartsmanovich.javawebdev.composite.datahandler.interpreter.Context;

/**
 * The realisation of AbstractBitwiseExpression interface that defines the
 * processing the "bitwise XOR" operator.
 *
 * @author Dmitry Gartsmanovich
 */
public class TerminalExpressionBitwiseXor implements
        AbstractBitwiseExpression {

    /**
     * Finds the result of bitwise XOR binary operation between two
     * popped values. Inserts the result to the context.
     *
     * @param context the provided context.
     */
    @Override
    public void interpret(final Context context) {
        Integer a = context.popValue();
        Integer b = context.popValue();
        context.pushValue(a ^ b);
    }
}