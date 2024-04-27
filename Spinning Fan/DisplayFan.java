package runningFan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DisplayFan extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create fan
		Fan fan = new Fan();
		
		// Put fan in StackPane
		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(fan);
		
		fan.getBtnPause().setOnMousePressed(e -> fan.pause());
		fan.getBtnResume().setOnMousePressed(e -> fan.play());
		fan.getBtnReverse().setOnMousePressed(e -> fan.reverse());
		fan.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.F) {
				fan.increaseSpeed();
			}
			else if (e.getCode() == KeyCode.S) {
				fan.decreaseSpeed();
			}
		});
		
		stackPane.widthProperty().addListener(ov -> {
			fan.setCenterX(stackPane.getWidth() / 2);
			if (fan.getRadius() < stackPane.getWidth() && fan.getRadius() < stackPane.getHeight()) {
				fan.setRadius(stackPane.getWidth() / 2.25);
			}
		});
		
		stackPane.heightProperty().addListener(ov -> {
			fan.setCenterY(stackPane.getHeight() / 2.25);
			if (fan.getRadius() < stackPane.getWidth() && fan.getRadius() < stackPane.getHeight()) {
				fan.setRadius(stackPane.getHeight() / 2.25);
			}
		});

		// Create a scene and place it in the stage
		Scene scene = new Scene(stackPane, 300, 280);
		primaryStage.setTitle("Fan (Speed up - F key, Slow down - S key"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}
}