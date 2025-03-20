package dev.velocity71.Graphy.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

// Controller for the UI elements defined in src/main/resources/fxml/app.fxml, and used in the App scene.
public class AppController {

	// Defining UI elements from the app.fxml file. The '@FXML' annotation tells JavaFX to connect the FXML elements to these Java variables.
    @FXML private Button graphButton;
    @FXML private TextField functionInput;
    @FXML private Label printFunction;

    // Event handler for the 'graphButton'. On click it sets the text of the 'printFunction' label to the contents of the 'functionInput' text field.
    @FXML private void handleGraphButtonClick() {
    	final String functionString = functionInput.getText();
     	printFunction.setText(functionString);
    }
}
