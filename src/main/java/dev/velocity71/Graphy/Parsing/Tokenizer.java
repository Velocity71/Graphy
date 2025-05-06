package dev.velocity71.Graphy.Parsing;

import dev.velocity71.Graphy.InvokeStackTraceFormattedString;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Breaks down the mathematical expression into a sequence of tokens.
 *
 * @since 0.1
 * @author Velocity71
 * @version 0.2
 */
final class Tokenizer {

    /**
     * The logger for this class.
     * @since 0.2
     */
    private static final Logger LOGGER = Logger.getLogger(
        Tokenizer.class.getName()
    );

    /**
     * Set of all known operators to be used in expressions.
     *
     * @since 0.1
     */
    private static final Set<Character> KNOWN_OPERATORS = Set.of(
        '+',
        '-',
        '*',
        '/'
    );

    /**
     * Set of all known unary functions to be used in expressions.
     *
     * @since 0.1
     */
    private static final Set<String> KNOWN_UNARY_FUNCTIONS = Set.of(
        "sin",
        "cos",
        "tan",
        "sec",
        "csc",
        "cot",
        "asin",
        "acos",
        "atan",
        "log",
        "ln",
        "sqrt",
        "abs"
    );

    /**
     * Set of all known binary functions to be used in expressions.
     *
     * @since 0.1
     */
    private static final Set<String> KNOWN_BINARY_FUNCTIONS = Set.of(
        "pow",
        "xrt",
        "logb"
    );

    /**
     * Tokenizes the input mathematical expression.
     *
     * @param expression The mathematical expression string.
     * @return A list of Tokens representing the expression.
     * @throws IllegalArgumentException If the expression contains invalid
     * characters or structure.
     * @version 0.2
     * @author Velocity71
     * @since 0.1
     */
    static ArrayList<Token> tokenize(final String expression)
        throws IllegalArgumentException {
        LOGGER.finest(new InvokeStackTraceFormattedString().toString());

        LOGGER.info("Tokenizing expression: '" + expression + "'.");

        // List of tokens to return.
        final ArrayList<Token> tokens = new ArrayList<>();

        final char[] chars = expression.toCharArray();
        String numStr = "";

        LOGGER.finer("----TOKENIZER FUNCTION----");

        // For each character in the expression:
        for (int pos = 0; pos < chars.length; pos++) {
            final char currentChar = chars[pos];

            LOGGER.finer("Expression: '" + expression + "'.");
            LOGGER.finer("Current selected character: '" + currentChar + "'.");
            LOGGER.finer("List of tokens: " + tokens + ".");

            // Skip whitespace.
            if (Character.isWhitespace(currentChar)) {
                LOGGER.finer("Character identified as whitespace.");
                continue;
            }

            Token currentToken;

            /*
             * If the character is a number or a decimal with a number after it,
             * build a string that includes the number(s) (and decimal).
             */
            if (
                Character.isDigit(currentChar) ||
                (currentChar == '.' &&
                    (pos + 1 < chars.length &&
                        Character.isDigit(chars[pos + 1])))
            ) {
                boolean hasDecimal = false;

                /*
                 * While the character is a digit or a period (and there has
                 * been no previous period) append it to the new string being
                 * created.
                 */
                while (
                    pos < chars.length &&
                    (Character.isDigit(chars[pos]) ||
                        (chars[pos] == '.' && !hasDecimal))
                ) {
                    LOGGER.finest("Inside number creation sub-loop.");
                    LOGGER.finest(
                        "Character: '" +
                        chars[pos] +
                        "' identified as a digit or decimal."
                    );

                    // Show that a decimal has been found.
                    if (chars[pos] == '.') {
                        LOGGER.finest("Decimal Found.");
                        hasDecimal = true;
                    }
                    numStr += chars[pos];
                    LOGGER.finest("Updated number: '" + numStr + "'.");
                    pos++;
                }
                pos--;
                // Once number is finished being created construct a token from it.
                LOGGER.finest("Completed number: '" + numStr + "'.");
                currentToken = new Token(TokenType.NUMBER, numStr);
                numStr = "";
                /*
                 * If the character is a letter try to find a function or variable
                 * from it. If invalid throw an error.
                 */
            } else if (Character.isLetter(currentChar)) {
                LOGGER.finer("Character identified as a letter.");
                String letterStr = "";

                while (pos < chars.length && Character.isLetter(chars[pos])) {
                    LOGGER.finest("Inside string creation sub-loop");
                    LOGGER.finest(
                        "Character: '" +
                        chars[pos] +
                        "' identified as a letter."
                    );
                    letterStr += chars[pos];
                    LOGGER.finest("Updated string: '" + letterStr + "'.");
                    pos++;
                }
                pos--;

                if (KNOWN_UNARY_FUNCTIONS.contains(letterStr)) {
                    LOGGER.finer(
                        "Identified unary function '" + letterStr + "'."
                    );
                    currentToken = new Token(
                        TokenType.UNARY_FUNCTION,
                        letterStr
                    );
                } else if (KNOWN_BINARY_FUNCTIONS.contains(letterStr)) {
                    LOGGER.finer(
                        "Identified binary function '" + letterStr + "'."
                    );
                    currentToken = new Token(
                        TokenType.BINARY_FUNCTION,
                        letterStr
                    );
                } else {
                    LOGGER.severe(
                        "Unknown character sequence: '" + letterStr + "'."
                    );
                    throw new IllegalArgumentException(
                        "Unknown character sequence: '" + letterStr + "'."
                    );
                }
                letterStr = "";
                // If the character is an operator.
            } else if (KNOWN_OPERATORS.contains(currentChar)) {
                LOGGER.finer("Character identified as an operator.");
                if (currentChar == '-') {
                    if (
                        tokens.isEmpty() ||
                        tokens.get(tokens.size() - 1).getType() ==
                        TokenType.OPERATOR ||
                        tokens.get(tokens.size() - 1).getValue().equals("(") ||
                        tokens.get(tokens.size() - 1).getType() ==
                        TokenType.COMMA
                    ) {
                        LOGGER.finer("Found a negative symbol.");
                        LOGGER.finest(
                            "Appending negative symbol to the number string."
                        );
                        numStr = "-";
                        continue;
                    }
                }
                currentToken = new Token(
                    TokenType.OPERATOR,
                    String.valueOf(currentChar)
                );
                // If the character is a parenthesis.
            } else if (currentChar == '(' || currentChar == ')') {
                LOGGER.finer("Character identified as a parenthesis.");
                currentToken = new Token(
                    TokenType.PARENTHESIS,
                    String.valueOf(currentChar)
                );
            } else if (currentChar == ',') {
                LOGGER.finer("Character identified as a comma.");
                currentToken = new Token(
                    TokenType.COMMA,
                    String.valueOf(currentChar)
                );
                // Throw error if nothing matches.
            } else {
                LOGGER.severe("Unknown character: '" + currentChar + "'.");
                throw new IllegalArgumentException(
                    "Unknown character in expression: '" + currentChar + "'."
                );
            }

            tokens.add(currentToken);
        }

        LOGGER.info("Finished tokenized expression: " + tokens + ".");
        return tokens;
    }
}
