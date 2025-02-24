package dev.Velocity71.Graphy;

import java.awt.Frame;

public class OutputFrame extends Frame {
    public OutputFrame() {
        super("Graphy Output");

        setSize(800, 600);
        setLocationRelativeTo(null);
        // setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Main.getLogger().info("Instantiated a Graphy Output Frame");
    }
}