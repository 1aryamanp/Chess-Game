/**
 * Implement the moves for Rook
 * 
 * @author Deep Shah
 * @author Aryaman Patel
 *
 */
public class Rook extends Piece{

	private String name = "R";
	
	/**
	 * Constructor method for Rook class
	 * 
	 * @param c: true for white and false for black
	 *
	 */
	public Rook(boolean c){
		this.setWhite(c);
		
		if (c == true){
			this.name = "w" + this.name; 
		}else{
			this.name = "b" + this.name; 
		}
	}
	
	/**
	 * Check if the Rook can move in a certain direction
	 * 
	 * @param oldX, oldY, newX, newY, isNewSpotEmpty:  x and y coordinates to check if Bishop can move from the old coordinated to new coordinates and also checks if spot is empty
	 * @return boolean: true if the spot is empty and false if not
	 *
	 */
	public boolean canMove(int oldX, int oldY, int newX, int newY, boolean isNewSpotEmpty) {

		int deltaX;
		int deltaY;
		
		deltaX = Math.abs(newX-oldX);
		deltaY = Math.abs(newY-oldY);
		
		if (deltaX == 0 && deltaY != 0){
			return true;
		} else if (deltaX != 0 && deltaY == 0) {
			return true;
		}
		
		return false;
	}

	/**
	 * returns the name of the piece
	 *
	 *@return String: returns the name of the piece i.e. R
	 */
	public String drawPiece() {
		return this.name;
	}

	/**
	 * void function for moving the piece
	 */
	public void movePiece() {
		
	}

}
