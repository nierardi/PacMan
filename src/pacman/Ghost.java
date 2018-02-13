package pacman;

/**
 * Ghost.java
 * Represents a ghost. Ghosts are enemies in the game of Pacman.
 * 
 * @author Nolan Ierardi
 */

import javafx.scene.image.ImageView;

public class Ghost {

	private ImageView img;		// currently displayed image
	private int x, y;			// x and y position for Ghost
	private boolean alive;		// if the ghost is alive or not
	
	/**
	 * Ghost(ImageView img, int x, int y)
	 * Constructor for Ghost.
	 * 
	 * @param img the ImageView this ghost will use
	 * @param x the starting x position
	 * @param y the starting y position
	 */
	public Ghost(ImageView img, int x, int y) {
		
		this.img = img;
		this.x = x;
		this.y = y;
		alive = true;
	}
	
	/**
	 * getImageView
	 * Returns the current ImageView. This is used by the Pane to push PacMan to
	 * the screen or to a button.
	 * 
	 * @return the ImageView that is currently active
	 */
	public ImageView getImageView() {
		return img;
	}
	
	/**
	 * getX()
	 * Returns the X position.
	 * 
	 * @return X position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * getY()
	 * Returns the Y position.
	 * 
	 * @return Y position
	 */
	public int getY() {
		return y;
	}
		
	/**
	 * setX(int x)
	 * Sets the X coordinate.
	 * 
	 * @param x the X to use
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * setY(int y)
	 * Sets the Y coordinate.
	 * 
	 * @param y the Y to use
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * move(int dir)
	 * Moves the Ghost in the desired direction.
	 * 
	 * @param dir int representing direction: 0 = up, 1 = right, 2 = down, 3 = left
	 */
	public void move(int dir) {
		// to be implemented
	}
	
	/**
	 * die()
	 * Kills the ghost, setting its alive status to false.
	 */
	public void die() {
		alive = false;
	}	
	
	/**
	 * isAlive()
	 * Returns if the ghost is alive or not.
	 * @return if the ghost is alive
	 */
	public boolean getAlive() {
		return alive;
	}
	
}
