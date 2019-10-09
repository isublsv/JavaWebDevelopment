package by.gartsmanovich.javawebdev.composite.datahandler.interpreter;

import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .expression.NonterminalExpressionNumber;
import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .expression.TerminalExpressionBitwiseAnd;
import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .expression.TerminalExpressionBitwiseCompliment;
import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .expression.TerminalExpressionBitwiseLeftShift;
import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .expression.TerminalExpressionBitwiseOr;
import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .expression.TerminalExpressionBitwiseRightShift;
import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .expression.TerminalExpressionBitwiseUnsignedRightShift;
import by.gartsmanovich.javawebdev.composite.datahandler.interpreter
        .expression.TerminalExpressionBitwiseXor;

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
                    listExpression.add(new TerminalExpressionBitwiseAnd());
                    break;
                case '|':
                    listExpression.add(new TerminalExpressionBitwiseOr());
                    break;
                case '^':
                    listExpression.add(new TerminalExpressionBitwiseXor());
                    break;
                case '~':
                    listExpression.add(
                            new TerminalExpressionBitwiseCompliment());
                    break;
                case '>':
                    listExpression.add(
                            new TerminalExpressionBitwiseRightShift());
                    break;
                case '<':
                    listExpression.add(
                            new TerminalExpressionBitwiseLeftShift());
                    break;
                case 'u':
                    listExpression.add(
                            new TerminalExpressionBitwiseUnsignedRightShift());
                    break;
                default:
                    listExpression.add(new NonterminalExpressionNumber(temp));
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
