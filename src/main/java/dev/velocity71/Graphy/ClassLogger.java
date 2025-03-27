package dev.velocity71.Graphy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A custom handler for logging on a per-class basis. that extends Extends
 * java.util.logging.Handler.
 *
 * It creates a log file for a specified class, handling log records and
 * managing the file handler. The log file is created in the directory
 * specified by the "logs.path" property in the config.properties file.
 */
public class ClassLogger extends Handler {

    /**
     * The FileHandler for the class's log file.
     */
    private FileHandler fileHandler;

    /**
     * The name of the class the logger is being created for.
     */
    private String className;

    /**
     * The path and name of the log file from the root directory.
     *
     * Includes the path to the log file from the root directory, the
     * specified class and associated log file, and the ".log" suffix.
     */
    private String logFileName;

    /**
     * The logger associated with the specified class and log file.
     */
    private Logger logger;

    /**
     * Constant containing the path to the log directory.
     */
    private static final Path LOGS_PATH =
        Paths.get(Main.getConfig().getProperty("logs.path"));

    /**
     * Constructor for the ClassLogger class.
     * Creates the log file and sets up the logger and it's handler.
    *
     * @param specifiedClassName The name of the class the logger is for.
     * @throws IOException of there is an error creating the log directory
     * or FileHandler.
     */
    public ClassLogger(final String specifiedClassName) throws IOException {
        this.className = specifiedClassName;

        /* Ensure the directory meant to contain the log files exists,
            and if not create it. */
        try {
            Files.createDirectories(LOGS_PATH);
        } catch (IOException e) {
            System.err.println("Error creating log directory: " + e);
            throw e; // Re-throw exception to be handled by the caller.
        }


        logFileName = LOGS_PATH.resolve(className + ".log").toString();

        // 0: unlimited size, 1: one file, true: append.
        fileHandler = new FileHandler(logFileName, 0, 1, true);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(Level.ALL); // Write all levels to file.
        logger = Logger.getLogger(className);
        logger.addHandler(this);
        logger.setLevel(Level.ALL); // Log all levels.
    }

    /**
     * Publishes a log record to the file handler.
     *
     * @param record The log record to be published.
     */
    @Override
    public final void publish(final LogRecord record) {
        fileHandler.publish(record);
    }

    /**
     * Flushes the file handler, ensuring all log records are written to the
     * file.
     */
    @Override
    public final void flush() {
        fileHandler.flush();
    }

    /**
     * Closes the file handler, releasing resources.
     *
     * @throws SecurityException If there is a security manager and it denies
     * the close operation.
     */
    @Override
    public final void close() throws SecurityException {
        fileHandler.close();
    }
}
