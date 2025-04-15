package dev.velocity71.Graphy.Parsing;

import java.util.Hashtable;
import java.util.Objects;

/**
 * Represents a token extracted from the string holding a mathematical
 * expression.
 *
 * @since 0.2.14
 * @author Velocity71
 * @version 0.1
 */
final class Token {

	/**
	 * The {@link TokenType} of the Token.
	 *
	 * @since 0.2.14
	 */
	private final TokenType type;

	/**
	 * The value of the token.
	 *
	 * @since 0.2.14
	 */
	private final String value;

	/**
	 * The precedence of the token. Only applicable to the
	 * {@link TokenType#OPERATOR operator} TokenType. Either 1 or 2, where 1 is
	 * lower precedence. Null if not applicable.
	 *
	 * @since 0.2.14
	 */
	private final Integer precedence;

	/**
	 * The associativity of the token. Only applicable to the
	 * {@link TokenType#PARENTHESIS parenthesis} TokenType. Either "left" or
	 * "right". Null if not applicable.
	 *
	 * @since 0.2.14
	 */
	private final String associativity;

	/**
	 * A Hashtable that holds the precedence values for each operator.
	 *
	 * @since 0.2.14
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
		//precedenceTable.put(",", 0);
	}

	/**
	 * Constructs a new Token and applies values to the precedence and
	 * associativity variables.
	 *
	 * @param type the {@link TokenType} of the Token.
	 * @param value The string value of the token.
	 * @since 0.2.14
	 * @Author Velocity71
	 * @version 0.1
	 */
	Token(TokenType type, String value) {
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
	}

	/**
	 * @return The {@link TokenType} of the Token.
	 */
	public TokenType getType() {
		return type;
	}

	/**
	 * @return The string value of the Token.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return The {@link Token#precedence precedence} of the Token if applicable.
	 * If not applicable the value is null.
	 */
	public Integer getPrecedence() {
		return precedence;
	}

	/**
	 * @return The {@link Token#associativity associativity} of the Token if
	 * applicable. If not applicable the value is null.
	 */
	public String getAssociativity() {
		return associativity;
	}

	/**
	 * The toString() method for a Token. Prints the type, value, precedence, and
	 * and associativity if applicable.
	 *
	 * @return The attributes of the Token.
	 */
	@Override public String toString() {
		return
		    /*"Token{" +
			"type=" + type +
			/*", value='" +*/ value /*+ "'" +
			", precedence=" + precedence +
			", associativity=" + associativity +
			"}"*/;
	}

	/**
	 * @return The hashcode for the object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(type, value, precedence, associativity);
	}
}
