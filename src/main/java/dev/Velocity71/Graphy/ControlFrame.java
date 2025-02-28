package dev.velocity71.Graphy;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Dialog;

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
        setLayout(new FlowLayout());

        // Delete the frame when intended by the user.
        addWindowListener(new WindowAdapter() {
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

        addComponents();
    }
    /**
     * Creates and adds the components to the ControlFrame.
     */
    private void addComponents() {
        // Text field the user inputs a function to for graphing.
        TextField graphEntry = new TextField("Enter function here");
        graphEntry.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { // On click delete placeholder text.
                graphEntry.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) { // If user leaves field blank replace placeholder text.
                if (graphEntry.getText().equals("")) {
                    graphEntry.setText("Enter function here");
                }
            }
        });
        add(graphEntry);

        // Button used to submit a provided function to the OutputFrame for graphing.
        Button graphButton = new Button("Graph");
        graphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!graphEntry.getText().equals("Enter function here")) {
                    OutputFrame.getInstance().setVisible(true);
                } else { // If the user has not entered a function, display a message indicating they must enter a function to graph.
                    Dialog dialog = new Dialog(getInstance(), "Enter a function to graph.", true);
                    dialog.setSize(300, 100);
                    dialog.add(new Label("You must enter a function to graph."));
                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            dialog.dispose();
                        }
                    });
                    dialog.setLocationRelativeTo(getInstance());
                    dialog.setVisible(true);
                    Main.getLogger().info("User attempted to graph without entering a function and activated a dialog.");
                }
            }
        });
        add(graphButton);
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