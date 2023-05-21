/**
 * Implement the moves for Bishop Piece
 * 
 * @author Deep Shah
 * @author Aryaman Patel
 *
 */
public class Bishop extends Piece{

	private String name = "B";
	
	/**
	 * Constructor method for Bishop class
	 * 
	 * @param c:  true for white and false for black
	 *
	 */
	public Bishop(boolean c){
		this.setWhite(c);
		
		if (c == true){
			this.name = "w" + this.name; 
		}else{
			this.name = "b" + this.name; 
		}
		
	}
	
	/**
	 * Check if the Bishop can move in a certain direction
	 * 
	 * @param oldX, oldY, newX, newY, isNewSpotEmpty: x and y coordinates to check if Bishop can move from the old coordinated to new coordinates and also checks if spot is empty
	 * @return boolean: true if the spot is empty and false if not
	 */
	public boolean canMove(int oldX, int oldY, int newX, int newY, boolean isNewSpotEmpty) {

		int deltaX;
		int deltaY;
		
		deltaX = Math.abs(newX-oldX);
		deltaY = Math.abs(newY-oldY);
		
		if (deltaX == deltaY) {
		
			return true;
		}
		return false;
	}

	/**
	 * returns the name of the piece
	 *
	 *@return String: returns the name of the piece i.e. B
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
