package by.gartsmanovich.javawebdev.composite.datahandler.interpreter.expression;

import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .AbstractBitwiseExpression;
import by.gartsmanovich.javawebdev.composite.datahandler.interpreter.Context;

/**
 * The realisation of AbstractBitwiseExpression interface that defines the
 * processing the numbers.
 *
 * @author Dmitry Gartsmanovich
 */
public class NonterminalExpressionNumber implements AbstractBitwiseExpression {

    /**
     * The found number in the expression.
     */
    private int number;

    /**
     * Constructs the instance with specific parameter.
     *
     * @param numberValue the found number in the expression.
     */
    public NonterminalExpressionNumber(final int numberValue) {
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
