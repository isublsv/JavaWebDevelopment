package by.gartsmanovich.composite.datahandler.interpreter;

import by.gartsmanovich.composite.datahandler.interpreter.expression
        .TerminalExpressionNumber;
import by.gartsmanovich.composite.datahandler.interpreter.expression
        .NonTerminalExpressionBitwiseAnd;
import by.gartsmanovich.composite.datahandler.interpreter.expression
        .NonTerminalExpressionBitwiseCompliment;
import by.gartsmanovich.composite.datahandler.interpreter.expression
        .NonTerminalExpressionBitwiseLeftShift;
import by.gartsmanovich.composite.datahandler.interpreter.expression
        .NonTerminalExpressionBitwiseOr;
import by.gartsmanovich.composite.datahandler.interpreter.expression
        .NonTerminalExpressionBitwiseRightShift;
import by.gartsmanovich.composite.datahandler.interpreter.expression
        .NonTerminalExpressionBitwiseUnsignedRightShift;
import by.gartsmanovich.composite.datahandler.interpreter.expression
        .NonTerminalExpressionBitwiseXor;

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
                case '&':
                    listExpression.add(new NonTerminalExpressionBitwiseAnd());
                    break;
                case '|':
                    listExpression.add(new NonTerminalExpressionBitwiseOr());
                    break;
                case '^':
                    listExpression.add(new NonTerminalExpressionBitwiseXor());
                    break;
                case '~':
                    listExpression.add(
                            new NonTerminalExpressionBitwiseCompliment());
                    break;
                case '>':
                    listExpression.add(
                            new NonTerminalExpressionBitwiseRightShift());
                    break;
                case '<':
                    listExpression.add(
                            new NonTerminalExpressionBitwiseLeftShift());
                    break;
                case 'u':
                    listExpression.add(
                          new NonTerminalExpressionBitwiseUnsignedRightShift());
                    break;
                default:
                    listExpression.add(new TerminalExpressionNumber(temp));
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

        for (AbstractBitwiseExpression expression : listExpression) {
            expression.interpret(context);
        }

        return context.popValue();
    }
}
