package dev.velocity71.Graphy.Parsing;

/**
 * An enum containing all the types of tokens found in the functions.
 * @author Velocity71
 * @version 0.1
 * @since 0.2.14
 * @see Token
 */
enum TokenType {

    /**
     * A number, e.g. 5, -2, 0.3
     * @since 0.2.14
     */
    NUMBER,

    /**
     * An operator, e.g. +, -, *.
     * @since 0.2.14
     */
    OPERATOR,

    /**
     * A unary function, e.g. sin(x).
     * @since 0.2.14
     */
    UNARY_FUNCTION,

    /**
     * A binary function, e.g. pow(x, y).
     * @since 0.2.14
     */
	BINARY_FUNCTION,

	/**
	 * A parenthesis "(" or ")".
	 * @since 0.2.14
	 */
	PARENTHESIS,

	/**
	 * A comma ",".
	 * @since 0.2.14
	 */
	COMMA
}
