package by.gartsmanovich.composite.datahandler.interpreter;

import by.gartsmanovich.composite.datahandler.exception.ParseException;
import by.gartsmanovich.composite.service.validator.Validator;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class used to convert infix bitwise expression to the postfix form.
 *
 * @author Dmitry Gartsmanovich
 */
public final class InfixToPostfixConverter {

    /**
     * The regular expression used to determine the bitwise expressions in the
     * provided message.
     */
    private static final String EXPRESSION_REGEX =
            "[\\d]+|[\\^]+|[<]{2}|[>]{2,3}|[()]+|[|]+|[&]+|[~]+";

    /**
     * The precedence of the bitwise or operation.
     */
    private static final int OR = 1;

    /**
     * The precedence of the bitwise xor operation.
     */
    private static final int XOR = 2;

    /**
     * The precedence of the bitwise and operation.
     */
    private static final int AND = 3;

    /**
     * The precedence of the bitwise shift operation.
     */
    private static final int SHIFT = 4;

    /**
     * The precedence of the bitwise compliment operation.
     */
    private static final int COMPLIMENT = 5;

    /**
     * The precedence of the default operation.
     */
    private static final int DEFAULT = -1;

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    /**
     * The deque used to store the string data.
     */
    private ArrayDeque<String> values;

    /**
     * Constructs the instance.
     */
    public InfixToPostfixConverter() {
        validator = new Validator();
        values = new ArrayDeque<>();
    }

    /**
     * Gets precedence of the bitwise operator.
     *
     * @param operator the provided operator or number.
     * @return the integer value of precedence.
     */
    private int getPrecedence(final String operator) {
        return switch (operator) {
            case "|" -> OR;
            case "^" -> XOR;
            case "&" -> AND;
            case ">>", "<<", ">>>" -> SHIFT;
            case "~" -> COMPLIMENT;
            default -> DEFAULT;
        };
    }

    /**
     * Converts given infix expression to postfix expression.
     *
     * @param expression the provided expression.
     * @return the expression in the postfix form.
     * @throws ParseException if error happens during parsing.
     */
    public String convert(final String expression) throws
            ParseException {
        StringBuilder result = new StringBuilder();

        String invalidExpression = "Invalid Expression";
        Matcher matcher = Pattern.compile(EXPRESSION_REGEX).matcher(
                expression);
        while (matcher.find()) {
            String temp = matcher.group();

            if (validator.isNumber(temp)) {
                result.append(temp).append(" ");
            } else if (temp.equals("(")) {
                values.push(temp);
            } else if (temp.equals(")")) {

                while (!values.isEmpty() && !values.peek().equals("(")) {
                    result.append(values.pop()).append(" ");
                }

                if (!values.isEmpty() && !values.peek().equals("(")) {
                    throw new ParseException(invalidExpression);
                } else {
                    values.pop();
                }
            } else {
                while (!values.isEmpty() 
                       && getPrecedence(temp) <= getPrecedence(values.peek())) {
                    if (values.peek().equals("(")) {
                        throw new ParseException(invalidExpression);
                    }
                    result.append(values.pop()).append(" ");
                }
                values.push(temp);
            }
        }

        while (!values.isEmpty()) {
            if (values.peek().equals("(")) {
                throw new ParseException(invalidExpression);
            }
            result.append(values.pop()).append(" ");
        }
        return result.toString();
    }
}
