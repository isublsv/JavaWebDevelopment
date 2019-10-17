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
public class InfixToPostfixConverter {

    /**
     * The regular expression used to determine the bitwise expressions in the
     * provided message.
     */
    private static final String EXPRESSION_REGEX = "[\\d]+|[\\^]+|[<]+|[>]+|[()]+|[|]+|[&]+|[~]+";

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    /**
     * The deque used to store the string data.
     */
    private ArrayDeque<String> values;

    private InfixToPostfixConverter() {
        validator = new Validator();
        values = new ArrayDeque<>();
    }

    private int precedence(String s) {
        return switch (s) {
            case "|" -> 1;
            case "^" -> 2;
            case "&" -> 3;
            case ">>", "<<", ">>>" -> 4;
            case "~" -> 5;
            default -> -1;
        };
    }

    /**
     * Converts given infix expression to postfix expression.
     *
     * @param expression the provided expression.
     * @return the expression in the postfix form.
     * @throws ParseException if error happens during parsing.
     */
    public String infixToPostfix(final String expression) throws
            ParseException {
        StringBuilder result = new StringBuilder();

        String invalidExpression = "Invalid Expression";
        Matcher matcher = Pattern.compile(EXPRESSION_REGEX).matcher(
                expression);
        while (matcher.find()) {
            String temp = matcher.group();

            if (validator.isNumber(temp)) {
                result.append(temp);
            } else if (temp.equals("(")) {
                values.push(temp);
            } else if (temp.equals(")")) {

                while (!values.isEmpty() && !values.peek().equals("(")) {
                    result.append(values.pop());
                }

                if (!values.isEmpty() && !values.peek().equals("(")) {
                    throw new ParseException(invalidExpression);
                } else {
                    values.pop();
                }
            } else // an operator is encountered 
            {
                while (!values.isEmpty() && precedence(temp) <= precedence(
                        values.peek())) {
                    if (values.peek().equals("(")) {
                        throw new ParseException(invalidExpression);
                    }
                    result.append(values.pop());
                }
                values.push(temp);
            }

        }

        // pop all the operators from the stack 
        while (!values.isEmpty()) {
            if (values.peek().equals("(")) {
                throw new ParseException(invalidExpression);
            }
            result.append(values.pop());
        }
        return result.toString();
    }
}
