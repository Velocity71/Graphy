package dev.velocity71.Graphy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

//import java.util.HashMap;

// Controller for the UI elements defined in src/main/resources/fxml/app.fxml, and used in the App scene.
public class Controller {

	// Defining UI elements from the app.fxml file. The '@FXML' annotation tells JavaFX to connect the FXML elements to these Java variables.
    @FXML VBox functionVBox;
    @FXML Label Function1;
    @FXML Button addFunctionButton;
    @FXML TextField enterFunctionField;

    //HashMap<Integer, Function> FunctionMap = new HashMap<Integer, Function>();


    /*@FXML private void initialize() {
    	enterFunctionField.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
     		if (!newValue) { // newValue is false when focus is lost
       			Function1.setText(enterFunctionField.getText());
     		}
     	});

     	addFunctionButton.setOnAction(e -> addFunctionField());
      }*/

    /*private void addFunctionField() {
    	TextField newFunctionField = new TextField();
     	newFunctionField.setPromptText("Enter function here");
      	newFunctionField.prefWidthProperty().bind(functionVBox.widthProperty());
       	newFunctionField.setMaxWidth(Double.MAX_VALUE);

        functionVBox.getChildren().add(functionVBox.getChildren().indexOf(addFunctionButton), newFunctionField);
        }*/
}
