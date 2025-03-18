package dev.velocity71.Graphy.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlSceneController {

	// Variables used for the Control Scene, also defined in src/main/resources/fxml/control.fxml.
    @FXML private Button graphButton;
    @FXML private TextField functionInput;
    @FXML private Label printFunction;

    // When the 'graphButton' button is clicked, set the text of teh 'printFunction' label to the text inputted into the 'functionInput' textfield.
    @FXML
    private void handleGraphButtonClick() {
    	final String functionString = functionInput.getText();
     	printFunction.setText(functionString);
    }
}
