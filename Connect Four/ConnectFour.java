package connectFour;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConnectFour extends Application {
	private Integer rowTracker_0 = 5, rowTracker_1 = 5, rowTracker_2 = 5, rowTracker_3 = 5, rowTracker_4 = 5, rowTracker_5 = 5, rowTracker_6 = 5;
	private boolean colorToggle = true; 
	private int[][] connectFourArray = new int[6][7];
	private VBox[] column = new VBox[10];
	private boolean gameToggle = true;
	private final int MAX_CHECKERS = 42;
	private int countForGameOver = 0;
	
	public static void main(String[] args)  {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Create connect four board
		BorderPane pane = gameBoard();

		// Event handlers 
		column[0].setOnMouseClicked(e -> {
			processClick(0, rowTracker_0);
			rowTracker_0--;
		});
		column[1].setOnMouseClicked(e -> {
			processClick(1, rowTracker_1);
			rowTracker_1--;
		});
		column[2].setOnMouseClicked(e -> {
			processClick(2, rowTracker_2);
			rowTracker_2--;
		});
		column[3].setOnMouseClicked(e -> {
			processClick(3, rowTracker_3);
			rowTracker_3--;
		});
		column[4].setOnMouseClicked(e -> {
			processClick(4, rowTracker_4);
			rowTracker_4--;
		});
		column[5].setOnMouseClicked(e -> {
			processClick(5, rowTracker_5);
			rowTracker_5--;
		});
		column[6].setOnMouseClicked(e -> {
			processClick(6, rowTracker_6);
			rowTracker_6--;
		});
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 600, 500);
		scene.setFill(Color.DODGERBLUE); // Set background color
		primaryStage.setTitle("Connect Four"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	/**
	 * Create game board
	 */
	public BorderPane gameBoard() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(5, 5, 5, 5));
		pane.setHgap(5);
		pane.setVgap(5);
		for (int i = 0; i < 7; i++) {
			column[i] = getColumnWithCircles();
			pane.addColumn(i, column[i]);
		}
		BorderPane bp = new BorderPane();
		bp.setCenter(pane);
		return bp;
	}
	
	/**
	 * Create columns with circles that make up board
	 * @return VBox with Circles in it
	 */
	public VBox getColumnWithCircles() {
		VBox vBox = new VBox(6);
		for (int i = 0; i < 6; i++) {
			vBox.getChildren().add(getCircle());
		}
		return vBox;
	}
	
	/**
	 * Create circles that make up board
	 * @return a Circle object
	 */
	public Circle getCircle() {
		Circle circle = new Circle(30, Color.WHITE);
		circle.setStroke(Color.WHITE);
		circle.setStrokeType(StrokeType.CENTERED);
		circle.setStrokeWidth(10);
		return circle;
	}
	
	/**
	 * Process click for event handler
	 */
	public void processClick(int i, Integer row) {
		if (gameToggle) {
			if (row >= 0) {
				Node nodeOut = column[i].getChildren().get(row);
				setCircleColor(nodeOut);
				if (colorToggle) {
					connectFourArray[row][i] = 1; 
				} else {
					connectFourArray[row][i] = 2;
				}
				gameWon();
				countForGameOver++;
				if (countForGameOver == MAX_CHECKERS) {
					//playLosingSound();
				}
			}
		}
	}
	
	/**
	 * Toggles color of checker
	 * @param nodeOut
	 */
	public void setCircleColor(Node nodeOut) {
		if (nodeOut instanceof Circle) {
			if (colorToggle) {
				((Circle) nodeOut).setFill(Color.YELLOW);
				((Circle) nodeOut).setStroke(Color.YELLOW);
			} else {
				((Circle) nodeOut).setFill(Color.RED);
				((Circle) nodeOut).setStroke(Color.RED);
			}
		}
		if (colorToggle) {
			colorToggle = false;
		} else {
			colorToggle = true;
		}
	}
	
	
	/**
	 * Outlines winning checkers
	 * @param j
	 * @param i
	 */
	int count = 0;
	public void highlightCheckers(int j , int i, int q, int w, int e, int r, int t, int y) {
		final Duration duration = Duration.millis(500);
		Node checker_0 = column[i].getChildren().get(j);
		Node checker_1 = column[w].getChildren().get(q);
		Node checker_2 = column[r].getChildren().get(e);
		Node checker_3 = column[y].getChildren().get(t);
		count++;
		
		gameToggle = false;
		if (count > 0) {
			FadeTransition ft_0 = new FadeTransition(duration, (Circle)(checker_0));
			ft_0.setFromValue(1.0);
			ft_0.setToValue(0.1);
			ft_0.setCycleCount(Timeline.INDEFINITE);
			ft_0.setAutoReverse(true);
			FadeTransition ft_1 = new FadeTransition(duration, (Circle)(checker_1));
			ft_1.setFromValue(1.0);
			ft_1.setToValue(0.1);
			ft_1.setCycleCount(Timeline.INDEFINITE);
			ft_1.setAutoReverse(true);
			FadeTransition ft_2 = new FadeTransition(duration, (Circle)(checker_2));
			ft_2.setFromValue(1.0);
			ft_2.setToValue(0.1);
			ft_2.setCycleCount(Timeline.INDEFINITE);
			ft_2.setAutoReverse(true);
			FadeTransition ft_3 = new FadeTransition(duration, (Circle)(checker_3));
			ft_3.setFromValue(1.0);
			ft_3.setToValue(0.1);
			ft_3.setCycleCount(Timeline.INDEFINITE);
			ft_3.setAutoReverse(true);
			
			ParallelTransition pt = new ParallelTransition(ft_0, ft_1, ft_2, ft_3);
			pt.play();
			//playWinningSound();
		}
	}
	
	/**
	 * Check if someone won
	 */
	public void gameWon() {
		int numberOfRows = connectFourArray.length;
		int numberOfColumns = connectFourArray[0].length;
		
        int[][] board = connectFourArray;
        
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                if(board[j][i] != 0) {
                    if (j + 3 < numberOfRows) {
                        if(board[j][i] == board[j + 1][i] && board[j][i] == board[j + 2][i] && board[j][i] == board[j + 3][i]) {
                            highlightCheckers(j, i, j + 1, i, j + 2, i, j + 3, i);
                        }
                    }
                    if (i + 3 < numberOfColumns) {
                        if (board[j][i] == board[j][i + 1] && board[j][i] == board[j][i + 2] && board[j][i] == board[j][i + 3]) {
                            highlightCheckers(j, i, j, i + 1, j, i + 2, j, i + 3);
                        }
                    }
                    if (i + 3 < numberOfColumns && j + 3 < numberOfRows) {
                        if(board[j][i] == board[j + 1][i + 1] && board[j][i] == board[j + 2][i + 2] && board[j][i] == board[j + 3][i + 3]) {
                            highlightCheckers(j, i, j + 1, i + 1, j + 2, i + 2, j + 3,i + 3);
                        }
                    }
                    if (i > 2 && j + 3 < numberOfRows) {
                        if (board[j][i] == board[j + 1][i - 1] && board[j][i] == board[j + 2][i - 2] && board[j][i] == board[j + 3][i - 3]) {
                            highlightCheckers(j, i, j + 1, i - 1, j + 2, i - 2, j + 3, i - 3);
                        }
                    }
                }
            }
        }
    }
	
//	/**
//	 * Plays winning sound
//	 */
//	public void playWinningSound() {
//		String soundLocation = "C:\\Users\\Carman Cater\\Documents\\Eclipse Workspace\\16_BookProblems_JavaFXUIControlsAndMultimedia\\bin\\connectFour\\lib\\connectFour_winningSound.mp3";
//		Media media = new Media(new File(soundLocation).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.play();
//	}
//	
//	/**
//	 * Plays losing sound
//	 */
//	public void playLosingSound() {
//		String soundLocation = "C:\\Users\\Carman Cater\\Documents\\Eclipse Workspace\\16_BookProblems_JavaFXUIControlsAndMultimedia\\bin\\connectFour\\lib\\connectFour_gameOver.wav";
//		Media media = new Media(new File(soundLocation).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		mediaPlayer.play();
//	}
}