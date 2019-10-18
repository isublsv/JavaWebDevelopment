package by.gartsmanovich.composite.datahandler.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class has a role to initiating and representing the
 * {@link AbstractBitwiseExpression} types of object for creating concrete type
 * of the expression.
 *
 * @author Dmitry Gartsmanovich
 */
public class BitwiseExpressionParser {

    /**
     * The list of provided operators used to evaluate the expression.
     */
    private List<AbstractBitwiseExpression> listExpression;

    /**
     * The regular expression used to determine the bitwise expressions in the
     * provided message.
     */
    private static final String EXPRESSION_REGEX = "\\p{Blank}+";

    /**
     * Constructs the instance of a parser for provided string.
     *
     * @param expression the provided expression to parse.
     */
    public BitwiseExpressionParser(final String expression) {
        listExpression = new ArrayList<>();
        parse(expression);
    }

    /**
     * Parses the provided expression. Creates the different kinds of non- or
     * terminal expressions that depends on found value.
     *
     * @param expression the provided string to parse.
     */
    private void parse(final String expression) {

        String[] lexemes = expression.split(EXPRESSION_REGEX);
        for (String temp : lexemes) {            
            if (temp.isEmpty()) {
                continue;
            }

            switch (temp) {
                case "&" -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(b & a);
                });
                case "|" -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(b | a);
                });
                case "^" -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(b ^ a);
                });
                case "~" -> listExpression.add(
                        context -> context.pushValue(~context.popValue()));
                case ">>" -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(b >> a);
                });
                case "<<" -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(b << a);
                });
                case ">>>" -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(b >>> a);
                });
                default -> listExpression.add(
                        context -> context.pushValue(Integer.parseInt(temp)));
            }
        }
    }

    /**
     * Calculates the final expression that was parsed.
     *
     * @return the result of the expression.
     */
    public Number calculate() {
        Context context = new Context();
        listExpression.forEach(expression -> expression.interpret(context));
        return context.popValue();
    }
}
