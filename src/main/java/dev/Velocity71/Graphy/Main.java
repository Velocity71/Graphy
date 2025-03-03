package dev.velocity71.Graphy;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		// create a label
		TextField functionInput = new TextField("Enter function here");
		Button graphButton = new Button("Graph");

		// create a stackpane to hold the label
		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(functionInput);
		root.getChildren().add(graphButton);

		// create a scene and set the stage
		Scene scene = new Scene(root, 300, 200);
		primaryStage.setTitle("Graphy Control");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
