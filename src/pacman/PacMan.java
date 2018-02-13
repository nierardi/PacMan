package pacman;

/**
 * PacMan.java
 * Represents a PacMan. The PacMan eats the coins on the board.
 * 
 * @author Nolan Ierardi
 */

import javafx.scene.image.ImageView;

public class PacMan {

	
	private ImageView currentImg;	// currently displayed image
	private ImageView upImg;		// image for direction 0
	private ImageView leftImg;		// image for direction 1
	private ImageView downImg;		// image for direction 2
	private ImageView rightImg;		// image for direction 3
	private ImageView poweredUpImg;
	private ImageView poweredLeftImg;
	private ImageView poweredDownImg;
	private ImageView poweredRightImg;
	private int direction;			// valid directions: 0 = up, 1 = right, 2 = down, 3 = left
	private int x, y;				// x and y position for PacMan
	private boolean powered;		// if the PacMan is powered (collected a power pellet)
	
	/**
	 * Constructor for PacMan.
	 * 
	 * @param base the ImageView to load initially
	 * @param x the starting x position
	 * @param y the starting y position
	 */
	public PacMan(ImageView base, int x, int y) {
		
		currentImg = base;
		this.x = x;
		this.y = y;
		direction = 0;
	}
	
	/**
	 * Sets the ImageViews that the PacMan uses when the direction is changed.
	 * 
	 * @param up ImageView for up (0)
	 * @param right ImageView for right (1)
	 * @param down ImageView for down (2)
	 * @param left ImageView for left (3)
	 * @param poweredUp ImageView for powered up (0)
	 * @param poweredRight ImageView for powered right (1)
	 * @param poweredDown ImageView for powered down (2)
	 * @param poweredLeft ImageView for powered left (3)
	 */
	public void setImages(
		ImageView up,
		ImageView right,
		ImageView down,
		ImageView left,
		ImageView poweredUp,
		ImageView poweredRight,
		ImageView poweredDown,
		ImageView poweredLeft) {
		upImg = up;
		downImg = down;
		leftImg = left;
		rightImg = right;
		poweredUpImg = poweredUp;
		poweredRightImg = poweredRight;
		poweredDownImg = poweredDown;
		poweredLeftImg = poweredLeft;
	}
	
	/**
	 * Returns the direction as an int.
	 * 
	 * @return int representing direction: 0 = up, 1 = right, 2 = down, 3 = left
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Sets the direction of this PacMan.
	 * 
	 * @param dir int representing direction: 0 = up, 1 = right, 2 = down, 3 = left
	 */
	public void setDirection(int dir) {
		direction = dir;
		if (dir == 0) {
			if (powered) {
				currentImg = poweredUpImg;
			} else {
				currentImg = upImg;
			}
		} else if (dir == 1) {
			if (powered) {
				currentImg = poweredRightImg;
			} else {
				currentImg = rightImg;
			}
		} else if (dir == 2) {
			if (powered) {
				currentImg = poweredDownImg;
			} else {
				currentImg = downImg;
			}
		} else if (dir == 3) {
			if (powered) {
				currentImg = poweredLeftImg;
			} else {
				currentImg = leftImg;
			}
		} else {
			System.out.println("[PacMan:setDirection] Invalid direction specified. Valid directions are 0-3.");
		}
	}
	
	/**
	 * Returns the current ImageView. This is used by the Pane to push PacMan to
	 * the screen or to a button.
	 * 
	 * @return the ImageView that is currently active
	 */
	public ImageView getImageView() {
		return currentImg;
	}
	
	/**
	 * Returns the X position.
	 * 
	 * @return X position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the Y position.
	 * 
	 * @return Y position
	 */
	public int getY() {
		return y;
	}
		
	/*
	 * Sets the X coordinate.
	 * 
	 * @param x the X to use
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the Y coordinate.
	 * 
	 * @param y the Y to use
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Moves the PacMan in the desired direction.
	 * 
	 * @param dir int representing direction: 0 = up, 1 = right, 2 = down, 3 = left
	 */
	public void move(int dir) {
		if (dir == 0) {
			// moving up
			this.y--;
			setDirection(0);
		} else if (dir == 1) {
			// moving right
			this.x++;
			setDirection(1);
		} else if (dir == 2) {
			// moving down
			this.y++;
			setDirection(2);
		} else if (dir == 3) {
			// moving left
			this.x--;
			setDirection(3);
		}
	}
	
	/**
	 * Sets the PacMan to powered up state.
	 */
	public void powerUp() {
		powered = true;
	}
	
	/**
	 * Removes the PacMan from its powered up state.
	 */
	public void powerDown() {
		powered = false;
	}
	
	/**
	 * Returns whether or not the PacMan is powered.
	 * @return if the PacMan is powered
	 */
	public boolean getIsPowered() {
		return powered;
	}
	
}
