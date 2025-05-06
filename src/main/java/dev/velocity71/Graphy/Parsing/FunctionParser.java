package dev.velocity71.Graphy.Parsing;

/**
 * An entry point to solve equations using the
 * {@link dev.velocity71.Graphy.Parsing Parsing} package.
 *
 * @since 0.1
 * @author Velocity71
 * @version 0.2
 */
public final class FunctionParser {

    /**
     * Evaluate an expression inside a string.
     *
     * <pre>
     * For example, the expression:
     * {@code "2*abs(-1*pow(2,2))"}
     * is equivalent to: {@code 8.0}
     * </pre>
     *
     * @param expression A string holding the expression to be evaluated.
     * @return The answer to the expression
     * @since 0.1
     * @author Velocity71
     * @version 0.1
     */
    public static String evalString(final String expression) {
        return RpnEvaluator.solve(
            ShuntingYardConverter.convert(Tokenizer.tokenize(expression))
        );
    }
}
