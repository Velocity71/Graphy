package dev.velocity71.Graphy.Parsing;

import dev.velocity71.Graphy.InvokeStackTraceFormattedString;
import java.util.Hashtable;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Represents a token extracted from the string holding a mathematical
 * expression.
 *
 * @since 0.1
 * @author Velocity71
 * @version 0.2
 */
final class Token {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(
        Token.class.getName()
    );

    /**
     * The {@link TokenType} of the Token.
     *
     * @since 0.1
     */
    private final TokenType type;

    /**
     * The value of the token.
     *
     * @since 0.1
     */
    private final String value;

    /**
     * The precedence of the token. Only applicable to the
     * {@link TokenType#OPERATOR operator} TokenType. Either 1 or 2, where 1 is
     * lower precedence. Null if not applicable.
     *
     * @since 0.1
     */
    private final Integer precedence;

    /**
     * The associativity of the token. Only applicable to the
     * {@link TokenType#PARENTHESIS parenthesis} TokenType. Either "left" or
     * "right". Null if not applicable.
     *
     * @since 0.1
     */
    private final String associativity;

    /**
     * A Hashtable that holds the precedence values for each operator.
     *
     * @since 0.1
     */
    private static final Hashtable<String, Integer> precedenceTable =
        new Hashtable<String, Integer>();

    static {
        precedenceTable.put("+", 1);
        precedenceTable.put("-", 1);
        precedenceTable.put("*", 2);
        precedenceTable.put("/", 2);
        precedenceTable.put("(", 0);
        precedenceTable.put(")", 0);
    }

    /**
     * Constructs a new Token and applies values to the precedence and
     * associativity variables.
     *
     * @param type the {@link TokenType} of the Token.
     * @param value The string value of the token.
     * @since 0.1
     * @Author Velocity71
     * @version 0.2
     */
    Token(TokenType type, String value) {
        LOGGER.finest(new InvokeStackTraceFormattedString().toString());

        this.type = type;
        this.value = value;

        // Assigning precedence if applicable.
        if (precedenceTable.containsKey(value)) {
            precedence = precedenceTable.get(value);
        } else {
            precedence = null;
        }

        // Assigning associativity if applicable.
        if (type == TokenType.PARENTHESIS) {
            if (value.equals("(")) {
                associativity = "left";
            } else {
                associativity = "right";
            }
        } else {
            associativity = null;
        }

        LOGGER.finer("New token created: " + this.toString() + ".");
    }

    /**
     * @return The {@link TokenType} of the Token.
     */
    public TokenType getType() {
        LOGGER.finest(new InvokeStackTraceFormattedString().toString());
        return type;
    }

    /**
     * @return The string value of the Token.
     */
    String getValue() {
        LOGGER.finest(new InvokeStackTraceFormattedString().toString());
        return value;
    }

    /**
     * @return The {@link Token#precedence precedence} of the Token if applicable.
     * If not applicable the value is null.
     */
    Integer getPrecedence() {
        LOGGER.finest(new InvokeStackTraceFormattedString().toString());
        return precedence;
    }

    /**
     * @return The {@link Token#associativity associativity} of the Token if
     * applicable. If not applicable the value is null.
     */
    String getAssociativity() {
        LOGGER.finest(new InvokeStackTraceFormattedString().toString());
        return associativity;
    }

    /**
     * The toString() method for a Token. Prints the type, value, precedence, and
     * and associativity if applicable.
     *
     * @return The attributes of the Token.
     */
    @Override
    public String toString() {
        LOGGER.finest(new InvokeStackTraceFormattedString().toString());
        return (
            "Token{" +
            "type=" +
            type +
            ", value='" +
            value +
            "'" +
            ", precedence=" +
            precedence +
            ", associativity=" +
            associativity +
            "}"
        );
    }

    /**
     * @return The hashcode for the object.
     */
    @Override
    public int hashCode() {
        LOGGER.finest(new InvokeStackTraceFormattedString().toString());
        return Objects.hash(type, value, precedence, associativity);
    }
}
