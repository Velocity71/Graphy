package dev.velocity71.Graphy.Parsing;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class to convert a tokenized infix expression to computationally-easier
 * postfix notation using the Shunting-Yard Algorithm.
 *
 * @since 0.2.14
 * @author Velocity71
 * @version 0.1
 */
public final class ShuntingYardConverter {

	/**
	 * Converts infix token arraylist to RPN token queue
	 * @param tokens The list of tokens inputted as an infix expression.
	 * @return outputQueue The output of tokens in postfix notation.
	 * @throws IllegalArgumentException If parentheses are mismatched.
	 * @version 0.1
	 * @author Velocity71
	 * @since 0.2.14
	 */
	public static ArrayList<Token> convert(ArrayList<Token> tokens)
	throws IllegalArgumentException {
		ArrayList<Token> outputQueue = new ArrayList<Token>();
		Stack<Token> operatorStack = new Stack<Token>();

        for (int i = 0; i < tokens.size(); i++) {
            System.out.println("Output Queue: " + outputQueue);
            System.out.println("Operator Stack: " + operatorStack);
            System.out.println("Token Value: " + tokens.get(i).getType());

            switch (tokens.get(i).getType()) {
                case NUMBER:
                    System.out.println("Recognized token as a number.");
                    outputQueue.add(tokens.get(i));
                    break;
                case UNARY_FUNCTION:
                case BINARY_FUNCTION:
                    System.out.println("Recognized token as a function.");
                    operatorStack.push(tokens.get(i));
                    break;
                case OPERATOR:
                    System.out.println("Recognized token as a binary function or operator.");
                    while (
                    !operatorStack.isEmpty() &&
                    !operatorStack.peek().getValue().equals(")") &&
                    (isPrecedent(operatorStack.peek(), tokens.get(i)) ||
                    (samePrecedence(operatorStack.peek(), tokens.get(i)) &&
                    tokens.get(i).getAssociativity().equals("left")))
                    ) {
                        outputQueue.add(operatorStack.pop());
                    }
                    operatorStack.push(tokens.get(i));
                    break;
                case COMMA:
                    while (!operatorStack.peek().getValue().equals("(")) {
                        outputQueue.add(operatorStack.pop());
                    }
                    break;
                case PARENTHESIS:
                    if (tokens.get(i).getAssociativity().equals("left")) operatorStack.push(tokens.get(i));
                    else {
                        while(!operatorStack.peek().getValue().equals("(")) {
                            if (operatorStack.isEmpty()) {
                                throw new IllegalArgumentException(
                                    "Mismatched Parentheses."
                                );
                            }
                            outputQueue.add(operatorStack.pop());
                        }

                        operatorStack.pop();
                    }
                    break;
            }

        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.isEmpty()) {
                throw new IllegalArgumentException(
                    "Mismatched Parentheses"
                );
            }

            outputQueue.add(operatorStack.pop());
        }

        return outputQueue;
	}

	private static boolean isPrecedent(Token t1, Token t2) {
		if (t1.getPrecedence() > t2.getPrecedence()) return true;
		return false;
	}

	private static boolean samePrecedence(Token t1, Token t2) {
		if (t1.getPrecedence() == t2.getPrecedence()) return true;
		return false;
	}
}
