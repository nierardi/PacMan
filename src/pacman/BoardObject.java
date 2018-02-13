package pacman;

/**
 * BoardObject.java
 * Represents a "tile" or square on a PacMan game board.
 * 
 * @author Nolan Ierardi
 */

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class BoardObject {

	private int type;			// object type (see below)
	private String desc;		// object human readable description
	private Color color;		// background color
	private ImageView imgView;	// image (optional)
	private ImageView tempImg;	// temporary image view
	private Button button;		// button object for use in BoardController
	private int points;			// the points you get if this object is eaten
	private boolean hasGhost;	// if the space has a ghost on it
	
	private final ImageView 		
		COIN_IMAGE = new ImageView(new Image(getClass().getResource("coin.png").toExternalForm())),
		SUPERCOIN_IMAGE = new ImageView(new Image(getClass().getResource("supercoin.png").toExternalForm()))
	;
	
	private final int 
		EMPTY = 0,
		WALL = 1,
		COIN = 2,
		SPECIAL_COIN = 3,
		GHOST_DOOR = 4
	;
	
	/**
	 * Constructor for BoardObject.
	 * This constructor is intended to be used with the map file parser, hence
	 * the "type" argument. This directly corresponds with the integers in the
	 * map file.
	 * Alternatively, this can work with the PrimitiveBoard class to create a
	 * predefined board.
	 * 0 = empty
	 * 1 = wall
	 * 2 = coin
	 * 3 = special coin
	 * 4 = ghost area door
	 * 
	 * @param type the type of object represented as an int
	 * @param unitSize the size of the button
	 */
	public BoardObject(int type, int unitSize) {
		if (type == 0) {
			desc = "empty";
			color = Color.BLACK;
			points = 0;
			imgView = null;
		} else if (type == 1) {
			desc = "wall";
			color = Color.rgb(107, 107, 107);
			points = 0;
			imgView = null;
		} else if (type == 2) {
			desc = "coin";
			color = Color.BLACK;
			imgView = COIN_IMAGE;
			points = 10;
			//imgShowing = true;
		} else if (type == 3) {
			desc = "specialcoin";
			color = Color.BLACK;
			imgView = SUPERCOIN_IMAGE;
			points = 100;
			//imgShowing = true;
		} else if (type == 4) {
			desc = "ghostdoor";
			color = Color.rgb(0, 117, 126);
			points = 0;
			imgView = null;
		} else {
			System.out.println("[BoardObject:Constructor] Cannot create object: type is not valid (passed: " + type + "). The tile has been marked red.");
			desc = "error";
			color = Color.RED;
			points = 0;
		}
		
		this.type = type;
		button = new Button();
		button.setPrefSize(unitSize, unitSize);
		button.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		button.setGraphic(imgView);
		tempImg = null;
		
	}
	
	/**
	 * Alternative constructor for BoardObject.
	 * This uses user-passed parameters for all values to create
	 * a custom button. This is not anticipated to be used often.
	 * @param type an integer representing the type of the object
	 * @param unitSize the size of the button
	 * @param desc a human-readable description
	 * @param color a Color object to color the button
	 */
	public BoardObject(int type, int unitSize, String desc, Color color) {
		this.type = type;
		this.color = color;
		button = new Button();
		button.setPrefSize(unitSize, unitSize);
		button.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		
	}
	
	/**
	 * Returns the JavaFX Button object. This is used by Main to push
	 * the graphics to the screen.
	 * @return the Button object for the BoardObject
	 */
	public Button getButton() {
		return button;
	}
	
	/**
	 * Returns a human-readable String description of the BoardObject.
	 * @return a String description
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Returns the integer type of the BoardObject.
	 * @return int representing the type of the BoardObject
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Sets the ImageView currently in the button to the passed ImageView.
	 * This overwrites the ImageView that currently exists.
	 * This method is intended to be used with BoardController to update
	 * the PacMan's position.
	 * @param img the ImageView to use
	 */
	public void replaceImage(ImageView img) {
		imgView = img;
		//imgShowing = true;
		button.setGraphic(imgView);
		//if (Main.DEBUG) System.out.println("[DEBUG][BoardObject:setImage] Setting the ImageView of the object to the image reference (this should not be null): " + imgView);
	}
	
	/**
	 * Sets the ImageView currently in the button to null, removing the image.
	 */
	public void clearImage() {
		imgView = null;
		button.setGraphic(imgView);
	}
	
	/**
	 * Sets a temporary variable to capture the current ImageView
	 * so that it can be temporarily replaced by a ghost.
	 */
	public void saveImage() {
		tempImg = imgView;
		//if (Main.DEBUG) System.out.println("[DEBUG][BoardObject:saveImage] Saving tempImg to the image reference (this should not be null): " + tempImg);
		hasGhost = true;
	}
	
	/**
	 * Replaces the current image with the temporarily stored image.
	 */
	public void resetImage() {
		//if (Main.DEBUG) System.out.println("[DEBUG][BoardObject:resetImage] Resetting image to the passed image reference (this should not be null): " + tempImg);
		imgView = tempImg;
		button.setGraphic(imgView);
		hasGhost = false;
	}
	
	
	/**
	 * Tells the BoardObject that the PacMan just consumed the coin
	 * that it held.
	 * An error will be printed if it does not contain a coin.
	 */
	public void coinEaten() {
		if (type == COIN) {
			//imgShowing = false;
			desc = "empty";
			type = 0;
		} else {
			System.out.println("[BoardObject:coinEaten] This BoardObject does not contain a COIN or SUPER_COIN. Nothing can be eaten.");
		}
	}
	
	/**
	 * Returns the amount of points that will be collected if this
	 * BoardObject is eaten.
	 * @return amount of points to earn
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Returns if this BoardObject contains a ghost.
	 * @return if this BoardObject contains a ghost
	 */
	public boolean hasGhost() {
		return hasGhost;
	}
	
}
