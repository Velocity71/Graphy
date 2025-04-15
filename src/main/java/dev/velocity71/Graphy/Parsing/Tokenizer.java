package dev.velocity71.Graphy.Parsing;

import java.util.Set;
import java.util.ArrayList;

/**
 * Breaks down the mathematical expression into a sequence of tokens.
 *
 * @since 0.2.14
 * @author Velocity71
 * @version 0.1
 */
public final class Tokenizer {

	/**
	 * Set of all known operators to be used in expressions.
	 *
	 * @since 0.2.14
	 */
	private static final Set<Character> KNOWN_OPERATORS = Set.of(
		'+', '-', '*', '/'
	);

	/**
	 * Set of all known unary functions to be used in expressions.
	 *
	 * @since 0.2.14
	 */
	private static final Set<String> KNOWN_UNARY_FUNCTIONS = Set.of(
	    "sin", "cos", "tan", "sec", "csc", "cot", "asin", "acos", "atan",
		"log", "ln", "sqrt", "abs"
	);

	/**
	 * Set of all known binary functions to be used in expressions.
	 *
	 * @since 0.2.14
	 */
	private static final Set<String> KNOWN_BINARY_FUNCTIONS = Set.of(
		"pow", "xrt", "logb"
	);

	/**
	 * Tokenizes the input mathematical expression.
	 *
	 * @param expression The mathematical expression string.
	 * @return An list of Tokens representing the expression.
	 * @throws IllegalArgumentException If the expression contains invalid
	 * characters or structure.
	 * @version 0.1
	 * @author Velocity71
	 * @since 0.2.14
	 */
	public static ArrayList<Token> tokenize(String expression)
	  throws IllegalArgumentException {
		ArrayList<Token> tokens = new ArrayList<>();
		char[] chars = expression.toCharArray();
		String numStr = "";

		System.out.println("----TOKENIZER FUNCTION----");
		System.out.println("Function: " + expression);

		for (int pos = 0; pos < chars.length; pos++) {
			char currentChar = chars[pos];

			System.out.println();
			System.out.println("List of tokens: " + tokens);
			System.out.println("Current selected character: '" + currentChar + "'");

			// Skip whitespace.
			if (Character.isWhitespace(currentChar)) {
				System.out.println("Character identified as whitespace.");
				continue;
			}

			Token currentToken;

			/* If the character is a number or a decimal with a number after it, build
			   a string that includes the number(s) (and decimal). */
			if (
				Character.isDigit(currentChar) ||
				(currentChar == '.' &&
			    (pos + 1 < chars.length && Character.isDigit(chars[pos + 1])))
			) {
				System.out.println("Character identified as a digit or '.'");
				boolean hasDecimal = false;

				/* While the character is a digit or a period (and there has been no
				   previous period) append it to the new string being created. */
				while (
					pos < chars.length && (Character.isDigit(chars[pos]) ||
					(chars[pos] == '.' && !hasDecimal))
				) {
					System.out.println("Inside integer-creation sub-loop");
					System.out.println(chars[pos] + " identified as a digit or decimal");
					// Show that a decimal has been found.
					if (chars[pos] == '.') hasDecimal = true;
					numStr += chars[pos];
					pos++;
				}
				pos--;
			// Once number is finished being created construct a token from it.
			System.out.println("Finished number: " + numStr);
			currentToken = new Token(TokenType.NUMBER, numStr);
			numStr = "";

			/* If the character is a letter try to find a function or variable from it.
			   If invalid throw an error. */
			} else if (Character.isLetter(currentChar)) {
				System.out.println("Character identified as a letter.");
				String letterStr = "";

				while (pos < chars.length && Character.isLetter(chars[pos])) {
					System.out.println("Inside string creation sub-loop");
					System.out.println(chars[pos] + " identified as a letter");
					letterStr += chars[pos];
					pos++;
				}
				pos--;

				if (KNOWN_UNARY_FUNCTIONS.contains(letterStr)) {
					System.out.println("Set of letters identified as a unary function");
					currentToken = new Token(TokenType.UNARY_FUNCTION, letterStr);
				} else if (KNOWN_BINARY_FUNCTIONS.contains(letterStr)) {
					System.out.println("Set of letters identified as a binary function");
					currentToken = new Token(TokenType.BINARY_FUNCTION, letterStr);
				} else {
					throw new IllegalArgumentException("Unknown sequence: " + letterStr);
				}
			letterStr = "";

			// If the character is an operator.
			} else if (KNOWN_OPERATORS.contains(currentChar)) {
				System.out.println("Character identified as an operator");
				if (currentChar == '-') {
					if (
						tokens.isEmpty() ||
					    tokens.get(tokens.size()-1).getType() == TokenType.OPERATOR ||
						tokens.get(tokens.size()-1).getValue().equals("(") ||
						tokens.get(tokens.size()-1).getType() == TokenType.COMMA
					) {
						System.out.println("Appending negative symbol to letterStr");
                        numStr = "-";
                        continue;
					}
				}
				currentToken = new Token(TokenType.OPERATOR, String.valueOf(currentChar));

			// If the character is a parenthesis.
			} else if (currentChar == '(' || currentChar == ')') {
				System.out.println("Character identified as a parenthesis.");
				currentToken = new Token(TokenType.PARENTHESIS, String.valueOf(currentChar));

			} else if (currentChar == ',') {
				currentToken = new Token(TokenType.COMMA, String.valueOf(currentChar));

			// Throw error if nothing matches.
			} else {
				throw new IllegalArgumentException("Invalid character in expression: " + currentChar);
			}

			tokens.add(currentToken);
		}

		System.out.println("Finished tokens: " + tokens);
		return tokens;
	}
}
