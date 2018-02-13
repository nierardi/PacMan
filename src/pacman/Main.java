package pacman;

/*
 * Main.java
 * This class drives the program and launches the application.
 * 
 * @author Nolan Ierardi
 */

import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	
	/*
	Initialize variables global to this class here
	This code runs only once
	Variables initialized here are changeable from game loop
	*/
	// BoardObject types
	private final int 
		EMPTY = 0,
		WALL = 1,
		COIN = 2,
		SPECIAL_COIN = 3,
		GHOST_DOOR = 4
	;
	private final int SIZE = 30;
	
	// Set to true to enable debug mode.
	// LOTS of strings will be printed.
	// No, seriously, your output will 
	public static final boolean DEBUG = true;
	
	// All ImageViews for the objects
	private final ImageView 
		PACMAN_IMAGE_UP = new ImageView(new Image(getClass().getResource("pacman0.png").toExternalForm())),
		PACMAN_IMAGE_RIGHT = new ImageView(new Image(getClass().getResource("pacman1.png").toExternalForm())),
		PACMAN_IMAGE_DOWN = new ImageView(new Image(getClass().getResource("pacman2.png").toExternalForm())),
		PACMAN_IMAGE_LEFT = new ImageView(new Image(getClass().getResource("pacman3.png").toExternalForm())),
		PACMAN_IMAGE_POWERED_UP = new ImageView(new Image(getClass().getResource("pacman0powered.png").toExternalForm())),
		PACMAN_IMAGE_POWERED_RIGHT = new ImageView(new Image(getClass().getResource("pacman1powered.png").toExternalForm())),
		PACMAN_IMAGE_POWERED_DOWN = new ImageView(new Image(getClass().getResource("pacman2powered.png").toExternalForm())),
		PACMAN_IMAGE_POWERED_LEFT = new ImageView(new Image(getClass().getResource("pacman3powered.png").toExternalForm())),
		GHOST1_IMAGE = new ImageView(new Image(getClass().getResource("ghostblue.png").toExternalForm())),
		GHOST2_IMAGE = new ImageView(new Image(getClass().getResource("ghostorange.png").toExternalForm())),
		GHOST3_IMAGE = new ImageView(new Image(getClass().getResource("ghostpink.png").toExternalForm())),
		GHOST4_IMAGE = new ImageView(new Image(getClass().getResource("ghostred.png").toExternalForm()))
	;
	
	private boolean poweredUp = false;
	private int powerUpTime = 0;
	private int lives = 3;
	private boolean win = false;
	
	public void start(Stage st) {
		
		/*
		Initialize game objects here
		This code runs only once
		Variables initialized here ARE NOT changeable from game loop
		*/
		
		// Starting positions
		// These are declared here so they can be easily changed if necessary
		final int
			PACMAN_START_X = 14,
			PACMAN_START_Y = 10,
			GHOST1_START_X = 12,
			GHOST1_START_Y = 3,
			GHOST2_START_X = 16,
			GHOST2_START_Y = 3,
			GHOST3_START_X = 12,
			GHOST3_START_Y = 5,
			GHOST4_START_X = 16,
			GHOST4_START_Y = 5
		;
		
		// Init screen
		Pane pane = new Pane();
		
		// Init board
		BoardController board = new BoardController();
		board.setEntireBoard(new PrimitiveBoard().getBoard());
		
		// Init pacman
		PacMan pacman = new PacMan(PACMAN_IMAGE_RIGHT, PACMAN_START_X, PACMAN_START_Y);
		pacman.setImages(PACMAN_IMAGE_UP, PACMAN_IMAGE_RIGHT, PACMAN_IMAGE_DOWN, PACMAN_IMAGE_LEFT,
			PACMAN_IMAGE_POWERED_UP, PACMAN_IMAGE_POWERED_RIGHT, PACMAN_IMAGE_POWERED_DOWN, PACMAN_IMAGE_POWERED_LEFT);
		
		// Push objects to screen
		ArrayList<ArrayList<BoardObject>> boardList = board.getBoard();
		//if (Main.DEBUG) System.out.println("[DEBUG][Main:start] boardList.size(): " + boardList.size());
		//if (Main.DEBUG) System.out.println("[DEBUG][Main:start] boardList.get(y).size(): " + boardList.get(1).size());
		
		for (int y = 0 ; y < boardList.size() ; y++) {
			for (int x = 0 ; x < boardList.get(y).size() ; x++) {
				// System.out.println("[DEBUG][Main:start] For loop to add images to screen: iteration " + y + "," + x);
				Button btn = boardList.get(y).get(x).getButton();
				pane.getChildren().add(btn);
				btn.relocate(SIZE * x, SIZE * y);
			}
		}
		board.updatePacmanPosition(pacman);
		
		// Init ghosts
		Ghost[] ghosts = new Ghost[] {
			new Ghost(GHOST1_IMAGE, GHOST1_START_X, GHOST1_START_Y),
			new Ghost(GHOST2_IMAGE, GHOST2_START_X, GHOST2_START_Y),
			new Ghost(GHOST3_IMAGE, GHOST3_START_X, GHOST3_START_Y),
			new Ghost(GHOST4_IMAGE, GHOST4_START_X, GHOST4_START_Y),
		};
		for (Ghost g : ghosts) {
			board.updateGhostPosition(g);
		}
		GhostValidator gv = new GhostValidator(board.getBoard());
		
		// Init scoring system
		Text score = new Text(10, 10, "0");
		score.setFill(Color.WHITE);
		score.setFont(Font.font(null, FontWeight.BOLD, 12.0));
		pane.getChildren().add(score);
		
		// Init lives system
		Text livesText = new Text(60, 10, "Lives: " + lives);
		livesText.setFill(Color.WHITE);
		livesText.setFont(Font.font(null, FontWeight.BOLD, 12.0));
		pane.getChildren().add(livesText);
		
		// Finalize stage
		Scene sc = new Scene(pane,boardList.get(0).size() * SIZE, boardList.size() * SIZE);
		sc.setFill(Color.BLACK);
		st.setTitle("PacMan");
		st.setScene(sc);
		st.show();
		System.setProperty("prism.lcdtext", "false");
		
		// BEGIN KEYFRAME =====================================
		KeyFrame kf = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			
			/*
			Initialize class variables local to the keyframe here
			This code runs only once
			*/
			int currentLevel = 1;
			boolean currentLevelInitialized = true;
			
			public void handle(ActionEvent evt) {
				
				/*
				Main loop
				This code runs every declared amount of milliseconds
				DO NOT initialize variables here, as they will be reset
				*/
				
				if (!currentLevelInitialized) {
					
					/*
					Level initialization tasks go here
					This code runs at every new level
					*/
					
					// WE'LL DO THIS LATER
					
				}
				
				if (lives > 0 && !win) {
					
					/*
					Tasks to run if not game over
					Ghost movement goes here
					*/
					
					for (Ghost g : ghosts) {
						if (g.getAlive()) board.moveGhost(g, gv);
					}
					
					int totalCount = 0;
					for (int y = 0 ; y < boardList.size() ; y++) {
						for (int x = 0 ; x < boardList.get(y).size() ; x++) {
							BoardObject current = boardList.get(y).get(x);
							if (!current.hasGhost() && current.getType() == EMPTY) {
								totalCount++;
							}
						}
					}
					System.out.println("[DEBUG][Main:<handleKeyFrameEvent] totalCount: " + totalCount);
					if (totalCount >= 263) win = true;
					
				}
				
				if (lives == 0) {
					
					/*
					Tasks to run once game over
					Display the game over, stop motion of things
					*/
					
					// Create a text "GAME OVER", set position, color, font
					Text gameOver = new Text(250, 300, "GAME OVER");
					gameOver.setFont(Font.font(null, FontWeight.BOLD, 50.0));
					gameOver.setFill(Color.RED);
					// Push to screen
					pane.getChildren().add(gameOver);
				}
				
				if (win) {

					/*
					Tasks to run once we win
					Display the game over, stop motion of things
					*/
					
					// Create a text "GAME OVER", set position, color, font
					Text gameWin = new Text(290, 300, "YOU WIN");
					gameWin.setFont(Font.font(null, FontWeight.BOLD, 50.0));
					gameWin.setFill(Color.LIGHTGREEN);
					// Push to screen
					pane.getChildren().add(gameWin);
				}
				
				if (poweredUp) {
					if (powerUpTime == 0) pacman.powerUp();
					if (powerUpTime	== 20) {
						poweredUp = false;
						pacman.powerDown();
						powerUpTime = -1;
					}
					powerUpTime++;
					if (DEBUG) System.out.println("[DEBUG][Main:<handleKeyFrameEvent>] powerUpTime: " + powerUpTime);
				}
				
				livesText.setText("Lives: " + lives);
				
			}
			
		});
		
		// Detect key presses. This runs whenever any key is pressed
		sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			public void handle(KeyEvent evt) {
				if (evt.getCode() == KeyCode.UP || evt.getCode() == KeyCode.W) {
					if (lives > 0 && !win) {
						int points = board.processMovement(0, pacman, ghosts);
						if (points >= 0) score.setText("" + (Integer.parseInt(score.getText()) + points));
						if (points == 100) poweredUp = true;
						if (points == -1) {
							lives--;
							pacman.setX(PACMAN_START_X);
							pacman.setX(PACMAN_START_Y);
							board.updatePacmanPosition(pacman);
						}
						if (DEBUG) System.out.println("[DEBUG][Main:<handleKeyboardEvent>] Powered up: " + poweredUp);
					}
				}
				if (evt.getCode() == KeyCode.RIGHT || evt.getCode() == KeyCode.D) {
					if (lives > 0 && !win) {
						int points = board.processMovement(1, pacman, ghosts);
						if (points >= 0) score.setText("" + (Integer.parseInt(score.getText()) + points));
						if (points == 100) poweredUp = true;
						if (points == -1) {
							lives--;
							pacman.setX(PACMAN_START_X);
							pacman.setX(PACMAN_START_Y);
							board.updatePacmanPosition(pacman);
						}
						if (DEBUG) System.out.println("[DEBUG][Main:<handleKeyboardEvent>] Powered up: " + poweredUp);
					}
				}
				if (evt.getCode() == KeyCode.DOWN || evt.getCode() == KeyCode.S) {
					if (lives > 0 && !win) {
						int points = board.processMovement(2, pacman, ghosts);
						if (points >= 0) score.setText("" + (Integer.parseInt(score.getText()) + points));
						if (points == 100) poweredUp = true;
						if (points == -1) {
							lives--;
							pacman.setX(PACMAN_START_X);
							pacman.setX(PACMAN_START_Y);
							board.updatePacmanPosition(pacman);
						}
						if (DEBUG) System.out.println("[DEBUG][Main:<handleKeyboardEvent>] Powered up: " + poweredUp);
					}
				}
				if (evt.getCode() == KeyCode.LEFT || evt.getCode() == KeyCode.A) {
					if (lives > 0 && !win) {
						int points = board.processMovement(3, pacman, ghosts);
						if (points >= 0) score.setText("" + (Integer.parseInt(score.getText()) + points));
						if (points == 100) poweredUp = true;
						if (points == -1) {
							lives--;
							pacman.setX(PACMAN_START_X);
							pacman.setX(PACMAN_START_Y);
							board.updatePacmanPosition(pacman);
						}
						if (DEBUG) System.out.println("[DEBUG][Main:<handleKeyboardEvent>] Powered up: " + poweredUp);
					}
				}
			}
			
		});
		
		// Finalize timeline
		Timeline tl = new Timeline(kf);
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.play();
			
	}
	
	// This does only one thing: it launches the application
	public static void main(String[] args) {
		launch();
	}
	
}