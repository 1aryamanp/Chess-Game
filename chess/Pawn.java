/**
 * Implement the moves for Pawn
 * 
 * @author Deep Shah
 * @author Aryaman Patel
 *
 */
public class Pawn extends Piece{

	private String name = "P";
	
	/**
	 * Constructor method for Pawn class
	 * 
	 * @param c: true for white and false for black
	 *
	 */
	public Pawn(boolean c){
		this.setWhite(c);
		
		if (c == true){
			this.name = "w" + this.name; 
		}else{
			this.name = "b" + this.name; 
		}
	}
	
	/**
	 * Check if the Pawn can move in a certain direction
	 * 
	 * @param oldX, oldY, newX, newY, isNewSpotEmpty: x and y coordinates to check if Bishop can move from the old coordinated to new coordinates and also checks if spot is empty
	 * @return boolean: true if the spot is empty and false if not
	 *
	 */
	public boolean canMove(int oldX, int oldY, int newX, int newY, boolean isNewSpotEmpty) {

		int deltaX;
		int deltaY;
		boolean legal = false;
		
		if (this.checkWhite() == true){
			deltaY = -(newY-oldY);
		}else{
			deltaY = newY - oldY;
		}
		
		deltaX = Math.abs(newX-oldX);

		if (deltaY == 1 && deltaX == 0 && isNewSpotEmpty == true){
			legal = true;
		} else if (deltaX == 1 && deltaY == 1 && isNewSpotEmpty == false){  //pawn capture
			legal  = true;
		} else if (this.firstMove == true && deltaY == 2 && deltaX == 0 && isNewSpotEmpty == true){
			legal = true;
		
		}
		
		return legal;
	}

	/**
	 * returns the name of the piece
	 *
	 *@return String: returns the name of the piece i.e. P
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
