package dev.velocity71.Graphy.Parsing;

import java.util.HashMap;

/**
 * An entry point to the Parsing package. Used to solve expressions and
 * single/multi variable functions.
 *
 * @since 0.1
 * @author Velocity71
 * @version 0.3
 */
public final class FunctionParser {

    /**
     * Evaluate a given mathematical expression inside a string.
     *
     * @param expression A String holding the expression to be evaluated.
     * @return The result of the expression
     * @since 0.1
     * @author Velocity71
     * @version 0.2
     */
    public static String evalExpression(final String expression) {
        return RpnEvaluator.solve(
            ShuntingYardConverter.convert(Tokenizer.tokenize(expression))
        );
    }

    /**
     * Evaluate a given mathematical function with a single given variable
     * representing a single given input.
     *
     * @param function A String holding the function to be evaluated.
     * @param variable A String holding the character(s) representing a
     * variable.
     * @param input A String holding the input to the function.
     * @return The result of f(x).
     * @since 0.3
     * @author Velocity71
     * @version 0.1
     */
    public static String evalFunction(
        final String function,
        final String variable,
        final String input
    ) {
        return evalExpression(function.replace(variable, input));
    }

    /**
     * Evaluate a given mathematical function with multiple given variables
     * representing multiple respective inputs.
     *
     * Similar to the single-variable method of the same name, but instead
     * we first iterate through the given variables and replace them with their
     * respective values.
     *
     * @param function A String holding the function to be evaluated.
     * @param variables A HashMap<String> holding the variables and their
     * respective values.
     * @return The result of f(x).
     * @since 0.3
     * @author Velocity71
     * @version 0.1
     */
    public static String evalFunction(
        String function,
        final HashMap<String, String> variables
    ) {
        for (String k : variables.keySet()) {
            function = function.replace(k, variables.get(k));
        }
        return evalExpression(function);
    }

    /**
     * Find a set of output values for a given mathematical function with a single
     * given variable and a set of given inputs.
     *
     * @param function A string holding the function to be evaluated.
     * @param variable A String holding the character(s) representing a
     * variable.
     * @param input A String array holding the input values to evaluate the
     * function with.
     * @return A HashMap<double, String> holding a input key and a result
     * value.
     * @since 0.3
     * @author Velocity71
     * @version 0.1
     */
    public static HashMap<Double, String> getFunctionTable(
        final String function,
        final String variable,
        double[] input
    ) {
        HashMap<Double, String> output = new HashMap<Double, String>(
            input.length,
            1
        );
        for (double i : input) {
            output.put(i, evalFunction(function, variable, Double.toString(i)));
        }
        return output;
    }
}
