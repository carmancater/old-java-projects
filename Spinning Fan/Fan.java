package runningFan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Fan extends BorderPane {
	private double radius = 90;
	private double centerX = 150;
	private double centerY = 100;
	private int blade1Start = 30;
	private int blade2Start = 120;
	private int blade3Start = 210;
	private int blade4Start = 300;
	private boolean reverse = false;
	private Timeline animation;
	private Button btnPause = new Button("Pause");
	private Button btnPlay = new Button("Play");
	private Button btnReverse = new Button("Reverse");
	
	public Fan() {
		paintFan();
		animation = new Timeline(new KeyFrame(
				Duration.millis(5), e -> spinFanBlades()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.setRate(1.0);
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		paintFan();
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
		paintFan();
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
		paintFan();
	}

	public Button getBtnPause() {
		return btnPause;
	}

	public void setBtnPause(Button btnPause) {
		this.btnPause = btnPause;
	}

	public Button getBtnResume() {
		return btnPlay;
	}

	public void setBtnResume(Button btnResume) {
		this.btnPlay = btnResume;
	}

	public Button getBtnReverse() {
		return btnReverse;
	}

	public void setBtnReverse(Button btnReverse) {
		this.btnReverse = btnReverse;
	}

	protected void paintFan() {
		Circle circle = new Circle(centerX, centerY, radius);
		Circle circle_1 = new Circle(centerX, centerY, radius *10);
		Arc arc1 = new Arc(centerX, centerY, radius - 10, radius - 10, blade1Start, 35);
		Arc arc2 = new Arc(centerX, centerY, radius - 10, radius - 10, blade2Start, 35);
		Arc arc3 = new Arc(centerX, centerY, radius - 10, radius - 10, blade3Start, 35);
		Arc arc4 = new Arc(centerX, centerY, radius - 10, radius - 10, blade4Start, 35);
		
		// Draw fan
	    Pane pane = new Pane();
	    
	    circle_1.setFill(Color.BLACK);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
	    
		pane.getChildren().add(circle_1);
	    pane.getChildren().add(circle);
	    
	    arc1.setFill(Color.SILVER); // Set fill color
	    arc1.setType(ArcType.ROUND); // Set arc type
	    arc1.setStroke(Color.BLACK);

	    pane.getChildren().add(arc1); // Add arc to pane
	    
	    arc2.setFill(Color.SILVER);
	    arc2.setType(ArcType.ROUND);
	    arc2.setStroke(Color.BLACK);
 
	    pane.getChildren().add(arc2);

	    arc3.setFill(Color.SILVER);
	    arc3.setType(ArcType.ROUND);
	    arc3.setStroke(Color.BLACK);

	    pane.getChildren().add(arc3);
	    
	    arc4.setFill(Color.SILVER);
	    arc4.setType(ArcType.ROUND);
	    arc4.setStroke(Color.BLACK);

	    pane.getChildren().add(arc4); 
	    
	    
	    // Buttons
		HBox hBox = new HBox();
		hBox.getChildren().addAll(btnPlay,btnPause, btnReverse);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(5, 5, 5, 5));
		hBox.setSpacing(10);
		
		setCenter(pane);
		setBottom(hBox);
	}

	public void spinFanBlades() {
		if (reverse == false) {
			blade1Start--;
			blade2Start--;
			blade3Start--;
			blade4Start--;
			paintFan();
		}
		else {
			blade1Start++;
			blade2Start++;
			blade3Start++;
			blade4Start++;
			paintFan();
		}
		
	}
	
	public void pause() {
		animation.pause();
	}
	
	public void play() {
		animation.play();
	}
	
	public void reverse() {
		if (reverse == false) {
			reverse = true;
		}
		else {
			reverse = false;
		}
	}
	
	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}
	
	public void decreaseSpeed() {
		animation.setRate(
				animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
	}
}