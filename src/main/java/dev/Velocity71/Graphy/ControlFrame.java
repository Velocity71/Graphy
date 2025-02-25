package dev.velocity71.Graphy;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A singleton class that extends java.awt.Frame.
 * - Input the functions, change the scale, resolution, etc.
 *
 * @author Velocity71
 * @since 0.3
 */
public class ControlFrame extends Frame {

    /**
     * The singleton instance of ControlFrame.
     */
    private static final ControlFrame INSTANCE = new ControlFrame();

    /**
     * Constructor for ControlFrame.
     */
    private ControlFrame() {
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
        Main.getLogger().info("Instantiated a Graphy Control Frame."); // Log successful frame creation.
    }

    /**
     * Getter method for the singleton instance of ControlFrame.
     *
     * @return INSTANCE.
     */
    public static ControlFrame getInstance() {
        return INSTANCE;
    }
}