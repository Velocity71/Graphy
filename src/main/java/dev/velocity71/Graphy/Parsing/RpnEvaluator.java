package dev.velocity71.Graphy.Parsing;

import java.util.ArrayList;
import java.util.Stack;
public final class RpnEvaluator {

    public static String solve(ArrayList<Token> expression) {

        Stack<String> operatingStack = new Stack<String>();

        while (!expression.isEmpty()) {
            System.out.println();
            System.out.println("Expression: " + expression);
            System.out.println("Operating Stack: " + operatingStack);
            String val = expression.get(0).getValue();
            System.out.println("Value: " + val);

            switch (expression.get(0).getType()) {
                case NUMBER:
                    operatingStack.push(val);
                    break;
                case UNARY_FUNCTION:
                    operatingStack.push(
                        performUnaryOperation(
                            val,
                            operatingStack.pop()
                        )
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
        System.out.println("Answer: " + operatingStack.peek());
        return operatingStack.peek();
    }

    private static String performUnaryOperation(String operator, String operand) {
        switch (operator) {
            case "sin":
                System.out.println("Performing operation: sin(" + operand + ")");
                return Double.toString(Math.sin(Float.parseFloat(operand)));
            case "cos":
                System.out.println("Performing operation: cos(" + operand + ")");
                return Double.toString(Math.sin(Float.parseFloat(operand)));
            case "tan":
                System.out.println("Performing operation: tan(" + operand + ")");
                return Double.toString(Math.tan(Float.parseFloat(operand)));
            case "sec":
                System.out.println("Performing operation: sec(" + operand + ")");
                return Double.toString(1.0/Math.cos(Float.parseFloat(operand)));
            case "csc":
                System.out.println("Performing operation: csc(" + operand + ")");
                return Double.toString(1.0/Math.sin(Float.parseFloat(operand)));
            case "cot":
                System.out.println("Performing operation: cot(" + operand + ")");
                return Double.toString(1.0/Math.tan(Float.parseFloat(operand)));
            case "asin":
                System.out.println("Performing operation: arcsin(" + operand + ")");
                return Double.toString(Math.asin(Float.parseFloat(operand)));
            case "acos":
                System.out.println("Performing operation: arccos(" + operand + ")");
                return Double.toString(Math.acos(Float.parseFloat(operand)));
            case "atan":
                System.out.println("Performing operation: arctan(" + operand + ")");
                return Double.toString(Math.atan(Float.parseFloat(operand)));
            case "ln":
                System.out.println("Performing operation: ln(" + operand + ")");
                return Double.toString(Math.log(Float.parseFloat(operand)));
            case "log":
                System.out.println("Performing operation: log(" + operand + ")");
                return Double.toString(Math.log(Float.parseFloat(operand))/Math.log(10));
            case "sqrt":
                System.out.println("Performing operation: 2rt(" + operand + ")");
                return Double.toString(Math.sqrt(Float.parseFloat(operand)));
            case "abs":
                System.out.println("Performing operation: |" + operand + "|");
                return Double.toString(Math.abs(Float.parseFloat(operand)));
            default:
                throw new IllegalArgumentException("Illegal unary function: " + operator);
        }
    }

    private static String performBinaryOperation(String operator, String operand2, String operand1) {
        switch (operator) {
            case "+":
                System.out.println("Performing operation: " + operand1 + " + " + operand2);
                return Float.toString(Float.parseFloat(operand1) + Float.parseFloat(operand2));
            case "-":
                System.out.println("Performing operation: " + operand1 + " - " + operand2);
                return Float.toString(Float.parseFloat(operand1) - Float.parseFloat(operand2));
            case "*":
                System.out.println("Performing operation: " + operand1 + " * " + operand2);
                return Float.toString(Float.parseFloat(operand1) * Float.parseFloat(operand2));
            case "/":
                System.out.println("Performing operation: " + operand1 + " / " + operand2);
                return Float.toString(Float.parseFloat(operand1) / Float.parseFloat(operand2));
            case "pow":
                    System.out.println("Performing operation: " + operand1 + " ^ " + operand2);
                return Double.toString(Math.pow(Float.parseFloat(operand1), Float.parseFloat(operand2)));
            /*case "xrt":
                System.out.println("Performing operation: " + operand1 + "rt(" + operand2 + ")");
                return Double.toString(Float.parseFloat(operand1), Float.parseFloat(operand2)));*/
            case "logb":
                System.out.println("Performing operation: log" + operand1 + "(" + operand2 + ")");
                return Double.toString(Math.log(Float.parseFloat(operand1)) / Math.log(Float.parseFloat(operand2)));
            default:
                throw new IllegalArgumentException("Illegal binary function: " + operator);
        }
    }
}
