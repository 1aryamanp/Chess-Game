/**
 * An abstract class Piece, which contains certain regular and abstract method to check if a piece is moving correctly.
 * 
 * @author Deep Shah
 * @author Aryaman Patel
 *
 */
public abstract class Piece {
	
	/**
	 * Indicates which player owns the piece. False is black, whereas True is white.
	 */
	private boolean white = true;
	
	public boolean firstMove = true;
	
	/**
	 * @param t - the true or false value. True is white and False is black
	 */
	public void setWhite(boolean t){
		white = t;
	}
	
	
	
	/**
	 * Checks whether the piece is which or black.
	 * @return boolean true if the color is white and false if color is black
	 */
	public boolean checkWhite(){
		return this.white;
	}
	
	
	/**
	 * abstract method to check if the piece can move or not
	 * @return boolean true if the piece can move
	 */
	public abstract boolean canMove(int oldX, int oldY, int newX, int newY, boolean isNewSpotEmpty);
	
	/**
	 * abstract method to move a certain piece
	 * 
	 */
	public abstract void movePiece();
	
	
	/**
	 * abstract method to draw the piece
	 * @return String returns the name of the piece
	 */
	public abstract String drawPiece();
}
