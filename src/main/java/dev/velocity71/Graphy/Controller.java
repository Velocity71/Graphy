package dev.velocity71.Graphy;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Controller class for the Graphy application.  Handles user interactions
 * with the GUI elements defined in the associated FXML file (app.fxml)
 *
 * @since 0.2.4
 * @author Velocity71
 * @version 0.9
 */
public class Controller {

    /**
     * A VBox container to dynamically add and manage function labels.
     *
     * @since 0.6
     */
    @FXML private VBox functionVBox;

    /**
     * A Label displaying the first function.
     *
     * @since 0.1
     */
    @FXML private Label function1;

    /**
     * A Button that triggers the addition of a new function input field to
     * the {@link #functionVBox}.
     *
     * @since 0.1
     */
    @FXML private Button addFunctionButton;

    /**
     * A TextField where the user enters a mathematical function. This input
     * will be processed to generate the graph. Currently no functionality.
     *
     * @since 0.1
     */
    @FXML private TextField enterFunctionField;
}
