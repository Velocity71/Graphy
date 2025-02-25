package dev.velocity71.Graphy;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The control frame for Graphy.
 * - Input the functions, change the scale, resolution, etc.
 * Note that OutputFrame extends Frame.
 *
 * @author Velocity71
 * @since 0.3
 */
public class ControlFrame extends Frame {

    /**
     * Constructor for ControlFrame.
     */
    public ControlFrame() {
        super("Graphy Control"); // Set the window title.

        addWindowListener(new WindowAdapter() { // Delete the frame when intended by the user.
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // Dispose of the frame.
                Main.getLogger().info("Closed a Graphy Control Frame."); // Log successful frame closure.
                Main.exitGraphy(); // Exit the application.
            }
        });

        setSize(400, 400); // Set the window size (x,y).
        setLocationRelativeTo(null); // Center the window on the screen.
        setVisible(true);
        Main.getLogger().info("Instantiated a Graphy Control Frame."); // Log successful frame creation.
    }
}