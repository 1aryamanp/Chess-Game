import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/**
 * Implements all the moves for the Chess game
 * 
 * @author Deep Shah
 * @author Aryaman Patel
 *
 */
public class Chess {
	
	public static Board chessboard;
	public static String cur_location = null;
	public static String new_location = null;
	public static String thirdArgs = null;
	public static boolean whitemove = true;
	
	public static boolean enPassant = false;
	public static int enPassantX;
	public static int enPassantY;
	
	
	public static int enPassantToEliminateX;
	public static int enPassantToEliminateY;
	
	
	public static int enPassantCapturer1X;
	public static int enPassantCapturer1Y;
	public static int enPassantCapturer2X;
	public static int enPassantCapturer2Y;
	
	
	/**
	 * Main method which starts the game and ends the game after Checkmate or Stalemate
	 * @param args: command line arguments if needed
	 */
	public static void main(String[] args) {
		//starts the game 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		
		 
		startGame();
		drawBoard();
		
		while (true){
			
			/**
 			* reads the player's move, checks if the player is in check, checkmate or stalemate's move. Also gives a error if there is a invalid input
 			*/

		     input = null;
		    
		     try {
		    	 
		    	 if (whitemove){		
		    			System.out.println("White player's turn:");	
		    	 }else{
		    		 System.out.println("Black player's turn:");
		    	 }
		    	 
		    	 boolean check = chessboard.detectCheck(whitemove);
		    	 if (check == true) {
		    		 if (whitemove) {
		    			System.out.println("white king is in check");
		    		 }else {
		    			System.out.println("black king is in check");
		    		 }
		    	 }
		    
		    	 input = br.readLine();
		    	 
		    	 parseInput(input);
		  
		    	 drawBoard();
		    	 
		    	 check = chessboard.detectCheck(!whitemove);
		    	 if (check == true) {
		    		 if (!whitemove) {
		    			System.out.println("Checkmate");
		    			System.out.println("Black wins");
		    		 }else {
		    			System.out.println("Checkmate");
		    			System.out.println("White wins");
		    		 }
		    		 System.exit(1);
		    	 }
		    	 
		    	if ( chessboard.checkStalemate( whitemove)){
		    		System.out.println("Stalemate");
                    System.out.println("The game is a draw");
		    		System.exit(1);
		    	}
		    
		    	 
		     } catch (IOException e) {
		    	 System.out.println("Invalid input, please try again.");
		     }       
		}
		
	}
	
	/**
	 * Creates a new Board when the game is initialized
	 */
	public static void startGame(){
		chessboard = new Board();
	}
	
	
	/**
	 * Draws the whole board on the terminal and puts the chess pieces
	 */
	public static void drawBoard(){
		String[][] result = new String[8][8];
		
        /**
 			draws white and black board spaces
        */
		
		boolean white = true;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (white){
					result[i][j] = "  |";
					white = false;
				}else{
					result[i][j] = "##|";
					white = true;
				}
			}
			white = !(white);
		}	
		
        /**
 			draws the chess pieces in their right location
        */
		
		for (int y = 0; y < 8; y++){
			for (int x = 0; x < 8; x++){
				
				if ( chessboard.board[x][y] != null){
					result[x][y] = chessboard.board[x][y].drawPiece() + "|";
				}
			}
		}
		
		/**
 			draws the chess board
        */

		System.out.println("_________________________");
		for (int y = 0; y < 8; y++){
			System.out.print("|");
			for (int x = 0; x < 8; x++){
				System.out.print(result[x][y]);
			}
			System.out.print("  " + (8-y));
			System.out.println();
		}
		System.out.println("\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'\'"); 
		System.out.println(" a  b  c  d  e  f  g  h"); 
	}
	
	
	/**
	 * Method to Coordinate the file
	 * @param file: a String representing the name of the file to be coordinated.
	 * @return int: An integer value representing the coordinate of the file. The value is calculated based on the lowercase first letter of the file name.
	 */
	public static int coordinateFile(String file){
		int result = (int) file.toLowerCase().charAt(0) - (int)('a');
		return result;
	}
	
	
	/**
	 * Method to Rank the file
	 * @param file: a String representing the name of the file to be ranked.
	 * @return int: An integer value representing the rank of the file. The value is calculated based on the second character of the file name, which should be a number from 1 to 8.
	 */
	public static int coordinateRank(String file){
		int result = 7 - ((int) file.toLowerCase().charAt(1) - (int)('1'));
		return result;
	}

	
	/**
	 * Checks if the input is legal or not
	 * @param x1, x2, x3, x4: integer values representing coordinates to test if the input is within range.
	 * @return boolean: A boolean value indicating whether the input coordinates are legal or not. The method checks if each of the four integer values is between 0 and 7 (inclusive). If all four values are within this range, the method returns true; otherwise, it returns false.
	 */
	public static boolean legalInput(int x1, int x2, int x3, int x4){
		if ((x1 >= 0 && x1 <= 7) && (x2 >= 0 && x2 <= 7) && (x3 >= 0 && x3 <= 7) && (x4 >= 0 && x4 <= 7)){
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * computes if the player won when other player resigns or if there is a draw, also checks for any invalid inputs
	 * @param input: a String representing the user input to be parsed.
	 */
	public static void parseInput(String input){
		StringTokenizer st = null;
		int count = 0;
		input = input.toLowerCase();
		input = input.trim();
		
		String[] array = new String[100]; 
		 
		st = new StringTokenizer(input);
		 
		while (st.hasMoreTokens()) {
	        array[count] = st.nextToken();
	        count++;
	    }
		
		if (count > 0 && count < 4){
			if (count == 1){
				if (array[0].equals("resign")){
					if (whitemove){
						System.out.println("Black wins");
						System.exit(1);
					}else{
						System.out.println("White wins");
						System.exit(1);
					}
					
				}else if (array[0].equalsIgnoreCase("draw")){
					System.exit(1);
				}else{
					System.out.println("Invalid input, Please try again. ");
				}
			}else if (count == 2) {
				cur_location = array[0];
				new_location = array[1];
				if (cur_location.length() == 2 && new_location.length() == 2 ){
					
					executeMove();
				}else{
					System.out.println("Invalid input, Please try again.");
				}
				
			}else if (count == 3){
				cur_location = array[0];
				new_location = array[1];
				if (cur_location.length() == 2 && new_location.length() == 2 ){
					
					if (array[2].equals("draw?")){
						executeMove();
					}else if (array[2].equalsIgnoreCase("q") || array[2].equalsIgnoreCase("r") || array[2].equalsIgnoreCase("b") || array[2].equalsIgnoreCase("n")) {
						thirdArgs = array[2];
						executeMove();
					}else{
						System.out.println("Invalid input, Please try again.");
					}
				}else{
					System.out.println("Invalid input, Please try again.");
				}
			}
		}else{
			System.out.println("Invalid input Please try again.");
		}
		
		cur_location = null;
		new_location = null;
		thirdArgs = null;
	}
	
	
	/**
    *   checks for player moves and coordinates if it is allowed and if the spot is empty
    */
	public static void executeMoveOld(){
		 
		int oldx = coordinateFile(cur_location);
		int oldy =coordinateRank(cur_location);
		 
		int newx = coordinateFile(new_location);
		int newy =coordinateRank(new_location);
		
		System.out.println(cur_location +" "+ new_location);
		 
		if (legalInput(oldx, oldy, newx, newy) == true){ 
			
			if (chessboard.board[oldx][oldy] != null){ 
						
					if ((whitemove && chessboard.board[oldx][oldy].checkWhite()) || (!whitemove && !chessboard.board[oldx][oldy].checkWhite())){ 
						if (chessboard.board[newx][newy] == null){ 
							
							if (chessboard.testCastling(oldx, oldy, newx, newy)) {
								return;
							}
							
							if (chessboard.board[oldx][oldy].canMove(oldx, oldy, newx, newy, true)  && chessboard.isPathClear(oldx, oldy, newx, newx) ){ 
								
								chessboard.board[newx][newy] = chessboard.board[oldx][oldy];
								
								if ( chessboard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") || chessboard.board[newx][newy].drawPiece().equalsIgnoreCase("bp")  ) {
									pawnPromotion(newx, newy);
								}
								
								chessboard.board[oldx][oldy] = null;
								
								whitemove = !whitemove;
							}else{
								System.out.println("Illegal move: This piece cannot move this way");
							}
							
						}else if ((whitemove && !chessboard.board[newx][newy].checkWhite()) || 
								(!whitemove && chessboard.board[newx][newy].checkWhite())){ 
							if (chessboard.board[oldx][oldy].canMove(oldx, oldy, newx, newy, false)  && chessboard.isPathClear(oldx, oldy, newx, newx)){ 
								
								chessboard.board[newx][newy] = chessboard.board[oldx][oldy];
								
								if ( chessboard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") || chessboard.board[newx][newy].drawPiece().equalsIgnoreCase("bp")  ) {
									pawnPromotion(newx, newy);
								}
								
								chessboard.board[oldx][oldy] = null;
								
								whitemove = !whitemove;
							}else{
									System.out.println("Illegal move: This piece cannot move this way");
							}
							
						}else{
							 System.out.println("Illegal move, Please try again");
						}
					
				}else{
					 System.out.println("Illegal move: This piece cannot be moved this way, Please try again");
				}
			}else{
				System.out.println("Illegal move: There are no pieces at the chosen location, Please try again");
			}
		}else{
			System.out.println("Illegal move: The chosen location is not on the board, Please try again");
		}
		 
	}
	
	/**
	 * method for promoting Pawn to Queen, Rook, Knight or Bishop when it reaches the last box
	 * @param newX, newY: an integer representing the x-coordinate, y-coordinate of the pawn's new position.
	 */
	public static void pawnPromotion(int newx, int newy) {
		if (chessboard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") && newy == 0) {
			if (thirdArgs == null) {
				chessboard.board[newx][newy] = new Queen(true);
				
			}else if (thirdArgs.equalsIgnoreCase("q")) {
				chessboard.board[newx][newy] = new Queen(true);
				
			}else if (thirdArgs.equalsIgnoreCase("R")) {
				chessboard.board[newx][newy] = new Rook(true);
				
			}else if (thirdArgs.equalsIgnoreCase("B")) {
				chessboard.board[newx][newy] = new Bishop(true);
				
			}else if (thirdArgs.equalsIgnoreCase("N")) {
				chessboard.board[newx][newy] = new Knight(true);
				
			}
		}else if (chessboard.board[newx][newy].drawPiece().equalsIgnoreCase("bp") && newy == 7) {
			if (thirdArgs == null) {
				chessboard.board[newx][newy] = new Queen(false);
				
			}else if (thirdArgs.equals("Q")) {
				chessboard.board[newx][newy] = new Queen(false);
				
			}else if (thirdArgs.equals("R")) {
				chessboard.board[newx][newy] = new Rook(false);
				
			}else if (thirdArgs.equals("B")) {
				chessboard.board[newx][newy] = new Bishop(false);
				
			}else if (thirdArgs.equals("N")) {
				chessboard.board[newx][newy] = new Knight(false);
				
			}
		}
	}
	
    /**
 	*	checks for pieces if they are moving correctly and prints error messages if not
    */
	
	public static void executeMove(){
		 
		int oldx = coordinateFile(cur_location);
		int oldy =coordinateRank(cur_location);
		 
		int newx = coordinateFile(new_location);
		int newy =coordinateRank(new_location);
		
		if (legalInput(oldx, oldy, newx, newy) == false){ 
			System.out.println("Illegal move, Invalid file and rank. Please try again");
			return;
		}
		
		if (chessboard.board[oldx][oldy] == null){ 
			System.out.println("Illegal move, A piece does not exist in this spot. Please try again");
			return;
		}
		
		
		if (whitemove != chessboard.board[oldx][oldy].checkWhite()) {
			System.out.println("Illegal move, wrong player move selected, Please try again");
			return;
		}
		
		boolean isNewSpotEmpty = true;
		if (chessboard.board[newx][newy] != null) {
			if (chessboard.board[newx][newy].checkWhite() == whitemove) {
				System.out.println("Illegal move, piece at that same location. Please Try again.");
				return;
			}
			isNewSpotEmpty = false;
		}
		
		if (chessboard.testCastling(oldx, oldy, newx, newy)) {
			
			chessboard.board[newx][newy].firstMove = false;
			
			whitemove = !whitemove;
			return;
		}
		
		if (enPassant == true) {
			enPassant = false;
			if (chessboard.testEnPassant(oldx, oldy, newx, newy) == true) {
				
				chessboard.board[newx][newy].firstMove = false;
				
				whitemove = !whitemove;
				return;
			}
		} 
		
		if (chessboard.isPathClear(oldx, oldy, newx, newy) == false) {
			System.out.println("Illegal move, pieces in the way.");
			return;
		}
		 
		if (chessboard.board[oldx][oldy].canMove(oldx, oldy, newx, newy, isNewSpotEmpty) == false) {
			System.out.println("Illegal move. This piece cannot move this way.");
			return;
		}
		
		chessboard.board[newx][newy] = chessboard.board[oldx][oldy];
		chessboard.board[newx][newy].firstMove = false;
		
		if ( chessboard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") || chessboard.board[newx][newy].drawPiece().equalsIgnoreCase("bp")  ) {
			pawnPromotion(newx, newy);
		}
		
		chessboard.board[oldx][oldy] = null;
		
		if (chessboard.detectEnPassant(oldx, oldy, newx, newy)){
			System.out.println("en passant");
		
		}
		
		whitemove = !whitemove;
		
		return;
		
	}
}
