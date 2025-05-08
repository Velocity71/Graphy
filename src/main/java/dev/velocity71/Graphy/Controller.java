package dev.velocity71.Graphy;

import dev.velocity71.Graphy.Parsing.FunctionParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Controller class for the Graphy application.  Handles user interactions
 * with the GUI elements defined in the associated FXML file (app.fxml)
 *
 * @since 0.2.4
 * @author Velocity71
 * @version 0.11
 */
public class Controller {

    /**
     * A VBox container to dynamically add and manage function labels.
     *
     * @since 0.6
     */
    @FXML
    private VBox functionVBox;

    /**
     * A Label displaying the first function.
     *
     * @since 0.1
     */
    @FXML
    private Label function1;

    /**
     * A Button that triggers the addition of a new function input field to
     * the {@link #functionVBox}.
     *
     * @since 0.1
     */
    @FXML
    private Button addFunctionButton;

    /**
     * A TextField where the user enters a mathematical function. This input
     * will be processed to generate the graph.
     *
     * @since 0.1
     */
    @FXML
    private TextField enterFunctionField;

    /**
     * A Canvas used to write functions in graphical form to.
     *
     * @since 0.11
     */
    @FXML
    private Canvas plane;

    /**
     * Initialize handlers for the FXML objects.
     *
     * @since 0.10
     * @author Velocity71
     * @version 0.2
     */
    public void initialize() {
        drawAxes();

        enterFunctionField
            .textProperty()
            .addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(
                        ObservableValue<? extends String> observable,
                        String oldValue,
                        String newValue
                    ) {
                        function1.setText(
                            FunctionParser.evalExpression(newValue)
                        );
                    }
                }
            );
    }

    /**
     * Draw the axes for the Cartesian coordinate system (X=0 and Y=0)
     *
     * @since 0.11
     * @author Velocity71
     * @version 0.1
     */
    private void drawAxes() {
        GraphicsContext gc = plane.getGraphicsContext2D();
        double width = plane.getWidth();
        double height = plane.getHeight();

        // Clear the plane
        gc.clearRect(0, 0, width, height);

        // Set line color
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1.0);

        // Calculate the Center
        double midX = width / 2;
        double midY = height / 2;

        // Draw X-axis
        gc.strokeLine(midX, 0, midX, height);

        // Draw Y-axis
        gc.strokeLine(0, midY, width, midY);
    }
}
