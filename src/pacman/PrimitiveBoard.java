package pacman;

/**
 * PrimitiveBoard.java
 * Represents a board of BoardObjects. This was placed in a separate
 * class to avoid confusion.
 * 
 * @author Nolan Ierardi
 */

import java.util.ArrayList;

public class PrimitiveBoard {
	
	private final int 
		EMPTY = 0,
		WALL = 1,
		COIN = 2,
		SPECIAL_COIN = 3,
		GHOST_DOOR = 4
	;
	private final int SIZE = 30;
	ArrayList<ArrayList<BoardObject>> thisBoard;

	public PrimitiveBoard() {
		
		thisBoard = new ArrayList<ArrayList<BoardObject>>() {{
			// ROW 0 ============================================
			add(new ArrayList<BoardObject>() {{
				for (int i = 0 ; i <= 28 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
			}});
			// ROW 1 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				for (int i = 1 ; i <= 27 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 2 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				for (int i = 2 ; i <= 8 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(9, new BoardObject(COIN, SIZE));
				for (int i = 10 ; i <= 18 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(19, new BoardObject(COIN, SIZE));
				for (int i = 20 ; i <= 26 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 3 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				add(2, new BoardObject(WALL, SIZE));
				for (int i = 3 ; i <= 9 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(10, new BoardObject(WALL, SIZE));
				for (int i = 11 ; i <= 17 ; i++) {
					add(new BoardObject(EMPTY, SIZE));
				}
				add(18, new BoardObject(WALL, SIZE));
				for (int i = 19 ; i <= 25 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(26, new BoardObject(WALL, SIZE));
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 4 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				add(2, new BoardObject(WALL, SIZE));
				add(3, new BoardObject(WALL, SIZE));
				add(4, new BoardObject(COIN, SIZE));
				for (int i = 5 ; i <= 10 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				for (int i = 11 ; i <= 17 ; i++) {
					add(new BoardObject(EMPTY, SIZE));
				}
				for (int i = 18 ; i <= 23 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(24, new BoardObject(COIN, SIZE));
				add(25, new BoardObject(WALL, SIZE));
				add(26, new BoardObject(WALL, SIZE));
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 5 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				add(2, new BoardObject(WALL, SIZE));
				for (int i = 3 ; i <= 9 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(10, new BoardObject(WALL, SIZE));
				for (int i = 11 ; i <= 17 ; i++) {
					add(new BoardObject(EMPTY, SIZE));
				}
				add(18, new BoardObject(WALL, SIZE));
				for (int i = 19 ; i <= 25 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(26, new BoardObject(WALL, SIZE));
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 6 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				for (int i = 2 ; i <= 5 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(6, new BoardObject(COIN, SIZE));
				for (int i = 7 ; i <= 21 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(22, new BoardObject(COIN, SIZE));
				for (int i = 23 ; i <= 26 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 7 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				for (int i = 1 ; i <= 27 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 8 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				add(2, new BoardObject(WALL, SIZE));
				add(3, new BoardObject(WALL, SIZE));
				add(4, new BoardObject(COIN, SIZE));
				for (int i = 5 ; i <= 9 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(10, new BoardObject(COIN, SIZE));
				add(11, new BoardObject(WALL, SIZE));
				add(12, new BoardObject(COIN, SIZE));
				add(13, new BoardObject(WALL, SIZE));
				add(14, new BoardObject(COIN, SIZE));
				add(15, new BoardObject(WALL, SIZE));
				add(16, new BoardObject(COIN, SIZE));
				add(17, new BoardObject(WALL, SIZE));
				add(18, new BoardObject(COIN, SIZE));
				for (int i = 19 ; i <= 23 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(24, new BoardObject(COIN, SIZE));
				add(25, new BoardObject(WALL, SIZE));
				add(26, new BoardObject(WALL, SIZE));
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 9 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				add(2, new BoardObject(WALL, SIZE));
				for (int i = 3 ; i <= 8 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(9, new BoardObject(WALL, SIZE));
				add(10, new BoardObject(COIN, SIZE));
				add(11, new BoardObject(WALL, SIZE));
				add(12, new BoardObject(COIN, SIZE));
				add(13, new BoardObject(WALL, SIZE));
				add(14, new BoardObject(COIN, SIZE));
				add(15, new BoardObject(WALL, SIZE));
				add(16, new BoardObject(COIN, SIZE));
				add(17, new BoardObject(WALL, SIZE));
				add(18, new BoardObject(COIN, SIZE));
				add(19, new BoardObject(WALL, SIZE));
				for (int i = 20 ; i <= 25 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(26, new BoardObject(WALL, SIZE));
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 10 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				add(2, new BoardObject(WALL, SIZE));
				add(3, new BoardObject(COIN, SIZE));
				for (int i = 4 ; i <= 7 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(8, new BoardObject(COIN, SIZE));
				add(9, new BoardObject(WALL, SIZE));
				add(10, new BoardObject(COIN, SIZE));
				add(11, new BoardObject(WALL, SIZE));
				add(12, new BoardObject(COIN, SIZE));
				add(13, new BoardObject(WALL, SIZE));
				add(14, new BoardObject(EMPTY, SIZE));
				add(15, new BoardObject(WALL, SIZE));
				add(16, new BoardObject(COIN, SIZE));
				add(17, new BoardObject(WALL, SIZE));
				add(18, new BoardObject(COIN, SIZE));
				add(19, new BoardObject(WALL, SIZE));
				add(20, new BoardObject(COIN, SIZE));
				for (int i = 21 ; i <= 24 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(25, new BoardObject(COIN, SIZE));
				add(26, new BoardObject(WALL, SIZE));
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 11 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				add(2, new BoardObject(WALL, SIZE));
				for (int i = 3 ; i <= 8 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(9, new BoardObject(WALL, SIZE));
				add(10, new BoardObject(COIN, SIZE));
				add(11, new BoardObject(WALL, SIZE));
				add(12, new BoardObject(COIN, SIZE));
				add(13, new BoardObject(WALL, SIZE));
				add(14, new BoardObject(COIN, SIZE));
				add(15, new BoardObject(WALL, SIZE));
				add(16, new BoardObject(COIN, SIZE));
				add(17, new BoardObject(WALL, SIZE));
				add(18, new BoardObject(COIN, SIZE));
				add(19, new BoardObject(WALL, SIZE));
				for (int i = 20 ; i <= 25 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(26, new BoardObject(WALL, SIZE));
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 12 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				for (int i = 2 ; i <= 6 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(7, new BoardObject(COIN, SIZE));
				add(8, new BoardObject(WALL, SIZE));
				add(9, new BoardObject(WALL, SIZE));
				add(10, new BoardObject(COIN, SIZE));
				add(11, new BoardObject(WALL, SIZE));
				add(12, new BoardObject(COIN, SIZE));
				add(13, new BoardObject(WALL, SIZE));
				add(14, new BoardObject(COIN, SIZE));
				add(15, new BoardObject(WALL, SIZE));
				add(16, new BoardObject(COIN, SIZE));
				add(17, new BoardObject(WALL, SIZE));
				add(18, new BoardObject(COIN, SIZE));
				add(19, new BoardObject(WALL, SIZE));
				add(20, new BoardObject(WALL, SIZE));
				add(21, new BoardObject(COIN, SIZE));
				for (int i = 22 ; i <= 26 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 13 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				for (int i = 1 ; i <= 27 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 14 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				add(2, new BoardObject(WALL, SIZE));
				add(3, new BoardObject(WALL, SIZE));
				add(4, new BoardObject(COIN, SIZE));
				for (int i = 5 ; i <= 11 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(12, new BoardObject(COIN, SIZE));
				for (int i = 13 ; i <= 17 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(18, new BoardObject(COIN, SIZE));
				for (int i = 19 ; i <= 26 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 15 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				for (int i = 1 ; i <= 4 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(5, new BoardObject(WALL, SIZE));
				for (int i = 6 ; i <= 10 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(11, new BoardObject(WALL, SIZE));
				for (int i = 12 ; i <= 16 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(17, new BoardObject(WALL, SIZE));
				for (int i = 18 ; i <= 22 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(23, new BoardObject(WALL, SIZE));
				for (int i = 24 ; i <= 27 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 16 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				add(1, new BoardObject(COIN, SIZE));
				for (int i = 2 ; i <= 9 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(10, new BoardObject(COIN, SIZE));
				for (int i = 11 ; i <= 15 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(16, new BoardObject(COIN, SIZE));
				for (int i = 17 ; i <= 23 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
				add(24, new BoardObject(COIN, SIZE));
				add(25, new BoardObject(WALL, SIZE));
				add(26, new BoardObject(WALL, SIZE));
				add(27, new BoardObject(COIN, SIZE));
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 17 ============================================
			add(new ArrayList<BoardObject>() {{
				add(0, new BoardObject(WALL, SIZE));
				for (int i = 1 ; i <= 27 ; i++) {
					add(new BoardObject(COIN, SIZE));
				}
				add(28, new BoardObject(WALL, SIZE));
			}});
			// ROW 18 ============================================
			add(new ArrayList<BoardObject>() {{
				for (int i = 0 ; i <= 28 ; i++) {
					add(new BoardObject(WALL, SIZE));
				}
			}});
			
			get(1).set(14, new BoardObject(SPECIAL_COIN, SIZE));
			get(5).set(9, new BoardObject(SPECIAL_COIN, SIZE));
			get(5).set(19, new BoardObject(SPECIAL_COIN, SIZE));
			get(15).set(6, new BoardObject(SPECIAL_COIN, SIZE));
			get(15).set(14, new BoardObject(SPECIAL_COIN, SIZE));
			get(15).set(22, new BoardObject(SPECIAL_COIN, SIZE));
		}};
		
	}
	
	public ArrayList<ArrayList<BoardObject>> getBoard() {
		return thisBoard;
	}
	
}
