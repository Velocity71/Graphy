package dev.velocity71.Graphy.Parsing;

import dev.velocity71.Graphy.InvokeStackTraceFormattedString;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * Class to convert a tokenized infix expression to computationally-easier
 * postfix notation using the Shunting-Yard Algorithm.
 *
 * @since 0.1
 * @author Velocity71
 * @version 0.2
 */
final class ShuntingYardConverter {

    /**
     * The logger for this class.
     *
     * @since 0.2
     */
    private static final Logger LOGGER = Logger.getLogger(
        Token.class.getName()
    );

    /**
     * Converts infix token arraylist to RPN token queue using the Shunting-Yard
     * Algorithm. See: <https://en.wikipedia.org/wiki/Shunting_yard_algorithm>
     *
     * @param tokens The list of tokens inputted as an infix expression.
     * @return The output of tokens in postfix notation.
     * @throws IllegalArgumentException If parentheses are mismatched.
     * @version 0.2
     * @author Velocity71
     * @since 0.1
     */
    static ArrayList<Token> convert(final ArrayList<Token> tokens)
        throws IllegalArgumentException {
        LOGGER.finest(new InvokeStackTraceFormattedString().toString());

        LOGGER.finer("----SHUNTING YARD CONVERTER----");

        // The output of the algorithm
        final ArrayList<Token> outputQueue = new ArrayList<Token>();

        // Holding the operators during the conversion
        final Stack<Token> operatorStack = new Stack<Token>();

        // For each token in the expression:
        for (int i = 0; i < tokens.size(); i++) {
            LOGGER.finer("Output Queue: " + outputQueue);
            LOGGER.finer("Operator Stack: " + operatorStack);
            LOGGER.finer("Token Value: " + tokens.get(i).getType());

            switch (tokens.get(i).getType()) {
                // If the token is a number add it to the output queue.
                case NUMBER:
                    outputQueue.add(tokens.get(i));
                    break;
                /* If the token is a unary or binary function, push it to the
                   operator stack.*/
                case UNARY_FUNCTION:
                case BINARY_FUNCTION:
                    operatorStack.push(tokens.get(i));
                    break;
                /*
                 * If the token is an operator, while:
                 * - the stack is not empty, and:
                 * - the top item of the operator stack is not a right
                 *   parenthesis, and:
                 * - the top item has a higher precedence
                 *   than the token or:
                 *   - the top item and the token have the same precedence and:
                 *   - the token is a left parenthesis,
                 *  pop the top item off the stack and add it to the
                 *  output queue.
                 *
                 *  Then push the token to the operator stack.
                 */
                case OPERATOR:
                    while (
                        !operatorStack.isEmpty() &&
                        !operatorStack.peek().getValue().equals(")") &&
                        (isPrecedent(operatorStack.peek(), tokens.get(i)) ||
                            (samePrecedence(
                                    operatorStack.peek(),
                                    tokens.get(i)
                                ) &&
                                tokens
                                    .get(i)
                                    .getAssociativity()
                                    .equals("left")))
                    ) {
                        outputQueue.add(operatorStack.pop());
                    }
                    operatorStack.push(tokens.get(i));
                    break;
                /*
                 * If the token is a comma, pop the top item off the operator
                 * stack and add it to the output queue until a left
                 * parenthesis is found. The comma is discarded.
                 */
                case COMMA:
                    while (!operatorStack.peek().getValue().equals("(")) {
                        outputQueue.add(operatorStack.pop());
                    }
                    break;
                /*
                 * If the token is a left parenthesis, push it to the operator
                 * stack.
                 *
                 * If the token is a right parenthesis, while the top item on
                 * the stack is not a left parenthesis, pop the top item off
                 * the stack and push it to the output. If no left parenthesis
                 * can be found, there must be mismatched parentheses. Both
                 * parentheses are discarded.
                 */
                case PARENTHESIS:
                    if (tokens.get(i).getAssociativity().equals("left")) {
                        operatorStack.push(tokens.get(i));
                    } else {
                        while (!operatorStack.peek().getValue().equals("(")) {
                            if (operatorStack.isEmpty()) {
                                LOGGER.severe("Mismatched Parentheses.");
                                throw new IllegalArgumentException(
                                    "Mismatched parentheses."
                                );
                            }
                            outputQueue.add(operatorStack.pop());
                        }

                        operatorStack.pop();
                    }
                    break;
                default:
                    LOGGER.severe("Unknown token type.");
                    throw new IllegalArgumentException("Unknown token type.");
            }
        }

        // Ensure there are no mismatched parentheses.
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().getType() == TokenType.PARENTHESIS) {
                LOGGER.severe("Mismatched parentheses.");
                throw new IllegalArgumentException("Mismatched parentheses.");
            }

            outputQueue.add(operatorStack.pop());
        }

        return outputQueue;
    }

    /**
     * Checks the precedence of two tokens. Returns true if token one has
     * more precedence than token 2 and false otherwise.
     *
     * @param t1 The first token, usually the top of the operator stack when
     * this method is called.
     * @param t2 The second token, usually the token being read from the
     * expression when this method is called.
     * @return True or false: t1 has more precedence than t2.
     * @since 0.2.14
     * @author Velocity71
     * @version 0.1
     */
    private static boolean isPrecedent(final Token t1, final Token t2) {
        if (t1.getPrecedence() > t2.getPrecedence()) {
            return true;
        }
        return false;
    }

    /**
     * Checks the precedence of two tokens. Returns true if both tokens have
     * the same precedence.
     *
     * @param t1 The first token, usually the top of the operator stack when
     * this method is called.
     * @param t2 The second token, usually the token being read from the
     * expression when this method is called.
     * @return True or false: t1 and t2 have the same precedence.
     * @since 0.2.14
     * @author Velocity71
     * @version 0.1
     */
    private static boolean samePrecedence(final Token t1, final Token t2) {
        if (t1.getPrecedence() == t2.getPrecedence()) {
            return true;
        }
        return false;
    }
}
