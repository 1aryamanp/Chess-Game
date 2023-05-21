
/**
 * creates a chess board when we run the project and detects every pieces' movement 
 * 
 * @author Deep Shah
 * @author Aryaman Patel
 *
 */
public class Board {
	
	Piece[][] board;
	
	
	/**
	 * Creates a new 8x8 board
	 */	
	public Board(){
		board = new Piece[8][8];
		
		initializeBoard();
		
	}
	
	/**
	 * Initializes the board and checks for the number of chess pieces for black and white team player
	 */	
	public void initializeBoard(){
		//initialize the board
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				board[i][j] = null;
			}
		}
		
		
		/*
		 *|bR|bN|bB|bQ|bK|bB|bN|bR| 8 
		 *|bP|bP|bP|bP|bP|bP|bP|bP| 7
		 *|  |##|  |##|  |##|  |##| 4
		 *|##|  |##|  |##|  |##|  | 5
		 *|  |##|  |##|  |##|  |##| 4
		 *|##|  |##|  |##|  |##|  | 3
		 *|wP|wP|wP|wP|wP|wP|wP|wP| 2
		 *|wR|wN|wB|wQ|wK|wB|wN|wR| 1
		 * a   b  c  d  e  f  g  h
		 * 
		 *The Board will look like this when we run the project
		 */
		
		
		//white
		
		board[0][1] = new Pawn(false);
		board[1][1] = new Pawn(false);
		board[2][1] = new Pawn(false);
		board[3][1] = new Pawn(false);
		board[4][1] = new Pawn(false);
		board[5][1] = new Pawn(false);
		board[6][1] = new Pawn(false);
		board[7][1] = new Pawn(false);
		
		board[0][0] = new Rook(false);
		board[1][0] = new Knight(false);
		board[2][0] = new Bishop(false);
		board[3][0] = new Queen(false);
		board[4][0] = new King(false);
		board[5][0] = new Bishop(false);
		board[6][0] = new Knight(false);
		board[7][0] = new Rook(false);
		
		
		//black
		
		board[0][6] = new Pawn(true);
		board[1][6] = new Pawn(true);
		board[2][6] = new Pawn(true);
		board[3][6] = new Pawn(true);
		board[4][6] = new Pawn(true);
		board[5][6] = new Pawn(true);
		board[6][6] = new Pawn(true);
		board[7][6] = new Pawn(true);
		
		board[0][7] = new Rook(true);
		board[1][7] = new Knight(true);
		board[2][7] = new Bishop(true);
		board[3][7] = new Queen(true);
		board[4][7] = new King(true);
		board[5][7] = new Bishop(true);
		board[6][7] = new Knight(true);
		board[7][7] = new Rook(true);	
	}
	
	/**
	 * Checks if the path is clear for each turn
	 * @param oldX, oldY, newX, newY: New and Old x and y coordinates to check if the path is clear to go from old coordinates to new ones
	 * @return boolean: true if path is clear and false if not clear
	 */
	
	public boolean isPathClear(int oldX, int oldY, int newX, int newY){
		
		String pieceName = board[oldX][oldY].drawPiece();
		
		if (pieceName.equalsIgnoreCase("wk") || pieceName.equalsIgnoreCase("bk")) {
			return true;
		} else if (pieceName.equalsIgnoreCase("wn") || pieceName.equalsIgnoreCase("bn")) {
			return true;
		}
		
		int deltaX;
		int deltaY;
		
		deltaX = newX-oldX;
		deltaY = newY-oldY;
		
		int locX = oldX;
		int locY = oldY;
		int dx = 0;
		int dy = 0;
		
		if (deltaX > 0) {
			dx = 1;
		}else if (deltaX < 0) {
			dx = -1;
		}
		
		if (deltaY > 0) {
			dy = 1;
		}else if (deltaY < 0) {
			dy = -1;
		}
		
		
		boolean clearPath = true;
		
		
		
		if (deltaY == 0) {
			locX = locX + dx;
			for (int i = 0; i < Math.abs(deltaX)-1; i++) {
				if (board[locX][locY] != null) {
					clearPath = false;
					break;
				}
				
				locX = locX + dx;
			} 
			return clearPath;
		}
		
		
		if (deltaX == 0) {
			locY = locY + dy;
			for (int i = 0; i < Math.abs(deltaY)-1; i++) {
				if (board[locX][locY] != null) {
					clearPath = false;
					break;
				}
				
				locY = locY + dy;
			}
			
			return clearPath;
		}
		
		if (deltaX != 0 && deltaY != 0) {
			locX = locX + dx;
			locY = locY + dy;
			for (int i = 0; i < Math.abs(deltaY)-1; i++) {
				if (board[locX][locY] != null) {
					clearPath = false;
					break;
				}
				
				locX = locX + dx;
				locY = locY + dy;
			} 
		}
		
		return clearPath;
		
	}
	
	/**
	 * checks if the castling is done correctly, and the chess pieces are in the correct place after castling 
	 * @param oldX, oldY, newX, newY: New and Old x and y coordinates of king and rook to check if castling can happen
	 * @return boolean: true if castling can work and false if it cannot work
	 */
	public boolean testCastling(int oldX, int oldY, int newX, int newY) {

		int deltaX = newX - oldX;
		if ( board[oldX][oldY] != null) {
			
			String pieceName = board[oldX][oldY].drawPiece();
			if (pieceName.equalsIgnoreCase("wk") && board[oldX][oldY].firstMove == true) { 
				
				if (deltaX == 2) { 

					if (board[7][7] != null) {
						if (board[7][7].drawPiece().equalsIgnoreCase("wr") ) { 

							if (isPathClear(oldX, oldY, newX, newY)) {
								board[oldX][oldY] = null;
								board[7][7] = null;
								
								board[newX][newY] = new King(true);
								board[newX][newY].firstMove = false;
								
								board[5][7] = new Rook(true);
								board[5][7].firstMove = false;
								
								return true;
							}
						}
					}
				}
			}
		}
		
		if ( board[oldX][oldY] != null) {
			String pieceName = board[oldX][oldY].drawPiece();

			if (pieceName.equalsIgnoreCase("bk") && board[oldX][oldY].firstMove == true) { 

				if (deltaX == 2) { 
					if (board[7][0] != null) { 
						if (board[7][0].drawPiece().equalsIgnoreCase("br") ) { 
							if (isPathClear(oldX, oldY, newX, newY)) {
								board[oldX][oldY] = null;
								board[7][0] = null;
								
								board[newX][newY] = new King(false);
								board[newX][newY].firstMove = false;
								
								board[5][0] = new Rook(false);
								board[5][0].firstMove = false;
								
								return true;
							}
						}
					}
				}
			}
		}
		
		if ( board[oldX][oldY] != null) {
			String pieceName = board[oldX][oldY].drawPiece();

			if (pieceName.equalsIgnoreCase("wk") && board[oldX][oldY].firstMove == true) { 

				if (deltaX == -2) { 

					if (board[0][7] != null) {

						if (board[0][7].drawPiece().equalsIgnoreCase("wr") ) { 

							if (isPathClear(oldX, oldY, newX, newY)) {

								board[oldX][oldY] = null; 
								board[0][7] = null; 
								
								board[newX][newY] = new King(true);
								board[newX][newY].firstMove = false;
								
								board[3][7] = new Rook(true);
								board[3][7].firstMove = false;

								
								return true;
							}
						}
					}
				}
			}
		}
		
		if ( board[oldX][oldY] != null) {
			String pieceName = board[oldX][oldY].drawPiece();

			if (pieceName.equalsIgnoreCase("bk") && board[oldX][oldY].firstMove == true) { 


				if (deltaX == -2) { 

					if (board[0][0] != null) { 

						if (board[0][0].drawPiece().equalsIgnoreCase("br") ) { 

							if (isPathClear(oldX, oldY, newX, newY)) {

								board[oldX][oldY] = null; 
								board[0][0] = null; 
								
								board[newX][newY] = new King(false);
								board[newX][newY].firstMove = false;
								
								board[3][0] = new Rook(false);
								board[3][0].firstMove = false;
								
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * detects if there is an en passant
	 * @param oldX, oldY, newX, newY: coordinates of the pawn for detecting if there is a en passant
	 * @return boolean: true if there is an en passant and false if there is no en passant
	 */
	public boolean detectEnPassant(int oldX, int oldY, int newX, int newY) { 
		Chess.enPassantToEliminateX = newX;
		Chess.enPassantToEliminateY = newY;
		
		if (board[newX][newY] == null) {
			return false;
		}

		if (!(board[newX][newY].drawPiece().equalsIgnoreCase("wp") || 
				board[newX][newY].drawPiece().equalsIgnoreCase("bp"))) { 
			return false;
		}
		if (Math.abs(newY - oldY) != 2) { 
			return false;
		}
		
		int locX = newX - 1;
		
		if (locX >= 0) {
			Chess.enPassantCapturer1X = locX;
			Chess.enPassantCapturer1Y = newY;
			
			if (board[locX][newY] != null) {
				if (board[locX][newY].drawPiece().equalsIgnoreCase("wp") && Chess.whitemove == false) {
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 2;
					
					return true;
				}
				
				if (board[locX][newY].drawPiece().equalsIgnoreCase("bp") && Chess.whitemove == true) {
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 5;
					
					return true;
				}
			}
		}
		
	
		locX = newX + 1;
		if (locX <= 7) {
			Chess.enPassantCapturer2X = locX;
			Chess.enPassantCapturer2Y = newY;
			
			if (board[locX][newY] != null) {
				if (board[locX][newY].drawPiece().equalsIgnoreCase("wp") && Chess.whitemove == false) {
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 2;
					return true;
				}
				
				if (board[locX][newY].drawPiece().equalsIgnoreCase("bp") && Chess.whitemove == true) {
					//System.out.println("10");
					Chess.enPassant = true;
					Chess.enPassantX = newX;
					Chess.enPassantY = 5;
					return true;
				}
			}
		}
	
		
		return false;
	}
	
	/**
	 * checks if the white is in check
	 * @param oldX, oldY, newX, newY: New and Old x and y coordinates to test the EnPassant with
	 * @return boolean: detects if enPassant is working or not
	 */
	public  boolean testEnPassant(int oldX, int oldY, int newX, int newY) {
		if (((Chess.enPassantCapturer1X == oldX) && (Chess.enPassantCapturer1Y == oldY)) 
				|| ((Chess.enPassantCapturer2X == oldX) && (Chess.enPassantCapturer2Y == oldY))) {
			if (newX == Chess.enPassantX && newY == Chess.enPassantY) {
				board[Chess.enPassantToEliminateX][Chess.enPassantToEliminateY] = null;
				board[newX][newY] = board[oldX][oldY];
				board[oldX][oldY] = null;
				
				return true;
			}
		}
		
		return false;
	}

	/**
	 * checks if the white is in check
	 * @param whiteTurn: check if it is white side's turn or black side's turn
	 * @return boolean: detects if black or white is in check or not
	 */
	public boolean detectCheck(boolean whiteturn) {
		int kingLocationX = 0;
		int kingLocationY = 0;
		
			
		if (whiteturn == true) {
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (board[x][y] != null) {
						if (board[x][y].drawPiece().equalsIgnoreCase("wk")) {
							kingLocationX = x;
							kingLocationY = y;
							break;
						}
					}
				}
			}
			
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (board[x][y] != null) {
						if (board[x][y].checkWhite() == false) {
							if (board[x][y].canMove(x, y, kingLocationX, kingLocationY, true)) {
								if (isPathClear(x, y, kingLocationX, kingLocationY)) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		
		if (whiteturn == false) {
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (board[x][y] != null) {
						if (board[x][y].drawPiece().equalsIgnoreCase("bk")) {
							kingLocationX = x;
							kingLocationY = y;
							break;
						}
					}
				}
			}
			
			for (int y = 0; y < 8; y++){
				for (int x = 0; x < 8; x++){
					if (board[x][y] != null) {
						if (board[x][y].checkWhite() == true) {
							if (board[x][y].canMove(x, y, kingLocationX, kingLocationY, true)) {
								if (isPathClear(x, y, kingLocationX, kingLocationY)) {
								
									return true;
								}
							}
						}
					}
				}
			}
		}
		
	
		return false;
	}
	
	/**
	 * check if there is a stalemate
	 * @param white: if true then white, false then black
	 * @return boolean: true if there is a stalemate, false if there is not
	 */
	public boolean checkStalemate(boolean white) {
		
		for (int y = 0; y < 8; y++){
			for (int x = 0; x < 8; x++){
				if (board[x][y] != null) {
					if (board[x][y].checkWhite() == white) {
						for (int yy = 0; yy < 8; yy++){
							for (int xx = 0; xx < 8; xx++){
								boolean isNewSpotEmpty = true;
								if (board[xx][yy] != null) {
									isNewSpotEmpty = false;
								}
								if (board[x][y].canMove(x, y, xx, yy, isNewSpotEmpty)) {
									if (isPathClear(x, y, xx, yy)) {
										return false;
									}
								}
							}
						}	
					}
				}
			}
		}	
		
		return true;  
	}
}
