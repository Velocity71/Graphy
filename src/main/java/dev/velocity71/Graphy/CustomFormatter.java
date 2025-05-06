package dev.velocity71.Graphy;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * A custom formatter, identical to the SimpleFormatter except it adds a line
 * break inbetween logs.
 *
 * @since 0.2.16
 * @author Velocity71
 * @version 0.1
 */
public class CustomFormatter extends Formatter {

    private static final SimpleFormatter SF = new SimpleFormatter();

    @Override
    public String format(LogRecord record) {
        return SF.format(record) + "\n";
    }
}
