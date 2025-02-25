package dev.velocity71.Graphy;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The output frame for Graphy.
 * - Displays the function inputted by the user in a graph form.
 * Note that OutputFrame extends Frame.
 *
 * @author Velocity71
 * @version 0.1
 * @since 0.1
 */
public class OutputFrame extends Frame {

    /**
     * Constructor for OutputFrame.
     */
    public OutputFrame() {
        super("Graphy Output"); // Set the window title.

        addWindowListener(new WindowAdapter() { // Delete the frame when intended by the user.
                @Override
                public void windowClosing(final WindowEvent e) {
                    dispose(); // Dispose of the frame
                    Main.getAppLogger().info("Closed a Graphy Output Frame"); // Log successful frame closure.
                    System.exit(0); // MOVE PROGRAM END TO CONTROL FRAME WHEN CREATED
                }
            }
        );

        setSize(800, 600); // Set the window size (x,y).
        setLocationRelativeTo(null); // Center the window on the screen.
        setVisible(true); // Window is visible on initialization.
        Main.getAppLogger().info("Instantiated a Graphy Output Frame"); // Log successful frame creation.
    }
}
