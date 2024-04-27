package eyeHandCoordination;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class eyeHandCoordination extends Application {
	// Counter for game (number of clicks)
	int gameLength = 10;
	int count = 0;
	long tStart, tEnd, tDelta;
	double elapsedSeconds;
	
	// Array of colors
	Random rand = new Random(); 
	String[] colors = new String[]{"Blue", "Red", "Green", "Yellow", "Black", "Brown", "Cyan",  "Purple", "White"};
	
	/***
	 * Creates simple clicking game (overwrites start method in Application class
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Length and width of scene
		int length = 500, width = 500;
		
		// Create circle
		Circle circle = new Circle(100, 100, 20);
		circle.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
		circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
		
		// Place circle in pane
		Pane pane = new Pane();
		pane.getChildren().add(circle);
		
		// Event handler (When user clicks on circle the circle moves)
		circle.setOnMouseClicked(e -> {
			// On first click start timer
			if (count == 0) {
				tStart = System.currentTimeMillis();
			}
			else if (count == gameLength - 1) { // On click "gameLength" end game and display time elapsed
				tEnd = System.currentTimeMillis();
				tDelta = tEnd - tStart;
				elapsedSeconds = tDelta / 1000.0;
				circle.setVisible(false);
				Label label_0 = new Label("Congratulations!");
				Label label_1 = new Label("\nTime spent: " + elapsedSeconds + " seconds");
				Label label_2 = new Label("\n\nClicks: " + gameLength);
				label_0.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.REGULAR, 20));
				label_1.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.REGULAR, 20));
				label_2.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.REGULAR, 20));
				pane.getChildren().addAll(label_0, label_1, label_2);
			}
			
			// Randomize circle properties
			circle.setCenterX(Math.random() * (width - circle.getRadius()));
			circle.setCenterY(Math.random() * (length - circle.getRadius()));
			circle.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
			circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
			
			// Ensure circle stays within view
			if (circle.getCenterX() < circle.getRadius()) {
				circle.setCenterX(circle.getRadius());
			}
			if (circle.getCenterY() < circle.getRadius()) {
				circle.setCenterY(circle.getRadius());
			}
			
			pane.setStyle("-fx-background-color: " + colors[rand.nextInt(colors.length)]);
			count++;
		});
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, width, length);
		primaryStage.setTitle("Game: Hand-Eye Coordination");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

	/***
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}


