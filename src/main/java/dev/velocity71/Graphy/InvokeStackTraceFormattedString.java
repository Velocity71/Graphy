package dev.velocity71.Graphy;

/**
 * Object that creates a string detailing the class, method, and file of the
 * method that invoked it.
 *
 * @since 0.2.15
 * @author Velocity71
 * @version 0.1
 */
public class InvokeStackTraceFormattedString {

    /**
     * The formatted stack trace to return.
     *
     * @since 0.2.15
     */
    private String formattedStackTrace;

    /**
     * The element that called the function requesting the information.
     *
     * @since 0.2.15
     */
    private StackTraceElement caller;

    /**
     * Create a string based on the method that called it.
     *
     * @since 0.2.15
     * @author Velocity71
     * @version 0.1
     */
    public InvokeStackTraceFormattedString() {
        caller = Thread.currentThread().getStackTrace()[2];

        /* Get the the method name of the function that invoked this
           constructor.*/
        formattedStackTrace += ("Method '" +
            Thread.currentThread().getStackTrace()[1].getMethodName() +
            "' called by: \n");

        // Get Invocation info for the function that requested it.
        formattedStackTrace += ("Class: '" + caller.getClassName() + "'.\n");
        formattedStackTrace += ("Method: '" + caller.getMethodName() + "'.\n");
        formattedStackTrace += ("File: " + caller.getFileName());
        formattedStackTrace += ("(" + caller.getLineNumber() + ").");
    }

    @Override
    public final String toString() {
        return formattedStackTrace;
    }
}
