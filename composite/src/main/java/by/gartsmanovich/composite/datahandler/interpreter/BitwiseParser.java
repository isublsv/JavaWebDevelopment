package by.gartsmanovich.composite.datahandler.interpreter;

import java.util.ArrayList;

/**
 * Class has a role to initiating and representing the
 * {@link AbstractBitwiseExpression} types of object for creating concrete type
 * of the expression.
 *
 * @author Dmitry Gartsmanovich
 */
public class BitwiseParser {

    /**
     * The list of provided operators used to evaluate the expression.
     */
    private ArrayList<AbstractBitwiseExpression> listExpression;

    /**
     * Constructs the instance of a parser for provided string.
     *
     * @param expression the provided expression to parse.
     */
    public BitwiseParser(final String expression) {
        listExpression = new ArrayList<>();
        parse(expression);
    }

    /**
     * Parses the provided expression. Splits the provided string by whitespace
     * and creates the different kinds of terminal expressions that depends on
     * found value.
     *
     * @param expression the provided string to parse.
     */
    private void parse(final String expression) {
        //The character class \p{Blank} matches a space or tab character.
        for (String lexeme : expression.split("\\p{Blank}+")) {
            if (lexeme.isEmpty()) {
                continue;
            }

            char temp = lexeme.charAt(0);

            switch (temp) {
                case '&' -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(a & b);
                });
                case '|' -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(a | b);
                });
                case '^' -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(a ^ b);
                });
                case '~' -> listExpression.add(
                        context -> context.pushValue(~context.popValue()));
                case '>' -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(a >> b);
                });
                case '<' -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(a << b);
                });
                case 'u' -> listExpression.add(context -> {
                    Integer a = context.popValue();
                    Integer b = context.popValue();
                    context.pushValue(a >>> b);
                });
                default -> listExpression.add(context -> context.pushValue(
                        Character.getNumericValue(temp)));
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
