package dev.velocity71.Graphy.Parsing;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class to solve expressions formatted in Reverse Polish Notation.
 *
 * @since 0.1
 * @author Velocity71
 * #version 0.2
 */
final class RpnEvaluator {

    /**
     * The logger for this class.
     *
     * @since 0.2
     */
    private static final Logger LOGGER = Logger.getLogger(
        RpnEvaluator.class.getName()
    );

    /**
     * Solve an expression formatted in Reverse Polish Notation and return the
     * output.
     *
     * @param expression The list of RPN formatted Tokens.
     * @return The answer to the equation.
     * @version 0.2
     * @author Velocity71
     * @since 0.1
     */
    static String solve(ArrayList<Token> expression) {
        Stack<String> operatingStack = new Stack<String>();

        while (!expression.isEmpty()) {
            LOGGER.finer("Expression: " + expression);
            LOGGER.finer("Operating Stack: " + operatingStack);
            String val = expression.get(0).getValue();
            LOGGER.finer("Value: " + val);

            switch (expression.get(0).getType()) {
                case NUMBER:
                    operatingStack.push(val);
                    break;
                case UNARY_FUNCTION:
                    operatingStack.push(
                        performUnaryOperation(val, operatingStack.pop())
                    );
                    break;
                case BINARY_FUNCTION:
                case OPERATOR:
                    operatingStack.push(
                        performBinaryOperation(
                            val,
                            operatingStack.pop(),
                            operatingStack.pop()
                        )
                    );
                    break;
                default:
            }
            expression.remove(0);
        }
        //System.out.println("Answer: " + operatingStack.peek());
        return operatingStack.peek();
    }

    /**
     * Perform a unary operation with a given operator and operand.
     *
     * @param operator
     * @param operand
     * @return The result of the equation.
     * @since 0.1
     * @author Velocity71
     * @version 0.2
     */
    private static String performUnaryOperation(
        String operator,
        String operand
    ) {
        switch (operator) {
            case "sin":
                LOGGER.finest("Performing operation: sin(" + operand + ")");
                return Double.toString(Math.sin(Float.parseFloat(operand)));
            case "cos":
                LOGGER.finest("Performing operation: cos(" + operand + ")");
                return Double.toString(Math.sin(Float.parseFloat(operand)));
            case "tan":
                LOGGER.finest("Performing operation: tan(" + operand + ")");
                return Double.toString(Math.tan(Float.parseFloat(operand)));
            case "sec":
                LOGGER.finest("Performing operation: sec(" + operand + ")");
                return Double.toString(
                    1.0 / Math.cos(Float.parseFloat(operand))
                );
            case "csc":
                LOGGER.finest("Performing operation: csc(" + operand + ")");
                return Double.toString(
                    1.0 / Math.sin(Float.parseFloat(operand))
                );
            case "cot":
                LOGGER.finest("Performing operation: cot(" + operand + ")");
                return Double.toString(
                    1.0 / Math.tan(Float.parseFloat(operand))
                );
            case "asin":
                LOGGER.finest("Performing operation: arcsin(" + operand + ")");
                return Double.toString(Math.asin(Float.parseFloat(operand)));
            case "acos":
                LOGGER.finest("Performing operation: arccos(" + operand + ")");
                return Double.toString(Math.acos(Float.parseFloat(operand)));
            case "atan":
                LOGGER.finest("Performing operation: arctan(" + operand + ")");
                return Double.toString(Math.atan(Float.parseFloat(operand)));
            case "ln":
                LOGGER.finest("Performing operation: ln(" + operand + ")");
                return Double.toString(Math.log(Float.parseFloat(operand)));
            case "log":
                LOGGER.finest("Performing operation: log(" + operand + ")");
                return Double.toString(
                    Math.log(Float.parseFloat(operand)) / Math.log(10)
                );
            case "sqrt":
                LOGGER.finest("Performing operation: 2rt(" + operand + ")");
                return Double.toString(Math.sqrt(Float.parseFloat(operand)));
            case "abs":
                LOGGER.finest("Performing operation: |" + operand + "|");
                return Double.toString(Math.abs(Float.parseFloat(operand)));
            default:
                LOGGER.severe("Illegal unary function: " + operator);
                throw new IllegalArgumentException(
                    "Illegal unary function: " + operator
                );
        }
    }

    /**
     * Perform a binary operation with a given operator and operands.
     *
     * @param operator
     * @param operand1
     * @param operand2
     * @return The result of the equation.
     * @since 0.1
     * @author Velocity71
     * @version 0.2
     */
    private static String performBinaryOperation(
        String operator,
        String operand2,
        String operand1
    ) {
        switch (operator) {
            case "+":
                LOGGER.finest(
                    "Performing operation: " + operand1 + " + " + operand2
                );
                return Float.toString(
                    Float.parseFloat(operand1) + Float.parseFloat(operand2)
                );
            case "-":
                LOGGER.finest(
                    "Performing operation: " + operand1 + " - " + operand2
                );
                return Float.toString(
                    Float.parseFloat(operand1) - Float.parseFloat(operand2)
                );
            case "*":
                LOGGER.finest(
                    "Performing operation: " + operand1 + " * " + operand2
                );
                return Float.toString(
                    Float.parseFloat(operand1) * Float.parseFloat(operand2)
                );
            case "/":
                LOGGER.finest(
                    "Performing operation: " + operand1 + " / " + operand2
                );
                return Float.toString(
                    Float.parseFloat(operand1) / Float.parseFloat(operand2)
                );
            case "pow":
                LOGGER.finest(
                    "Performing operation: " + operand1 + " ^ " + operand2
                );
                return Double.toString(
                    Math.pow(
                        Float.parseFloat(operand1),
                        Float.parseFloat(operand2)
                    )
                );
            /*case "xrt":
                System.out.println("Performing operation: " + operand1 + "rt(" + operand2 + ")");
                return Double.toString(Float.parseFloat(operand1), Float.parseFloat(operand2)));*/
            case "logb":
                LOGGER.finest(
                    "Performing operation: log" +
                    operand1 +
                    "(" +
                    operand2 +
                    ")"
                );
                return Double.toString(
                    Math.log(Float.parseFloat(operand1)) /
                    Math.log(Float.parseFloat(operand2))
                );
            default:
                LOGGER.severe("Illegal binary function: " + operator);
                throw new IllegalArgumentException(
                    "Illegal binary function: " + operator
                );
        }
    }
}
