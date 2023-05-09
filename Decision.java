package tetrismode;

public class Decision {
	
	private static int holeCost = -20;
	private static int bumpCost = -1;
	
	public static int[] FindBestPlacement(int[] pieces, short[][] board) {
		
		//Decide whether we need to clean up the stack, play a tetris, or continue stacking 9-0
		int mode = DecideMode(board);
		
		if (mode == 0 && pieces[0] == 0) { //tetris
			
		}
		else if (mode == 2) { //clean stack
			
		}
		else { //stack 9-0
			
		}
		
		return null;
	}
	
	private static int[] TestCombinations(int piece, short[][] board, int stackingMode) {
		
		if (piece == 0) { //O piece
			
		}
		if (piece == 1) { //I piece
			
		}
		if (piece == 2) { //S piece
			
		}
		if (piece == 3) { //Z piece
			
		}
		if (piece == 4) { //L piece
			
		}
		if (piece == 5) { //J piece
			
		}
		if (piece == 6) { //T piece
			
		}
		
		return null; //error return
	}
	
	private static int CalculateCost(int[][] minoCoordinates, short[][] board, int stackingMode) {
		
		int cost = 0;
		int bumpCost = 1;
		int holeCost = 20;
		
		//Drop piece
		boolean contact = false;
		int yPos = 17;
		
		while (!contact && yPos >= 0) {
			
			for (int mino = 0; mino < minoCoordinates.length; mino++) {
				
				if (board[minoCoordinates[mino][0]][yPos+minoCoordinates[mino][1]] == 1) {
					//we are immersed in floor
					contact = true;
					yPos++;
					break;
				}
			}
			
			yPos--;
		}
		yPos++;
		
		//Update board to contain dropped piece
		for (int mino = 0; mino < minoCoordinates.length; mino++) {
			board[minoCoordinates[mino][0]][minoCoordinates[mino][1]+yPos] = 1;
		}
		
		//Clear lines
		int sum = 0;
		int linesCleared = 0;
		
		for (int y = 0; y < 20; y++) {
			sum = 0;
			
			for (int x = 0; x < 10; x++) {
				board[x][y] = board[x][y+linesCleared];
			}
			
			for (int x = 0; x < 10; x++) {
				sum += board[x][y];
			}
			
			if (sum == 10) {
				linesCleared += 1;
			}
			if (sum == 0) {
				break;
			}
		}
		
		//Calculate cost
		//Hole costs
		int holeCostDecay = 0;
		
		for (int mino = 0; mino < minoCoordinates.length; mino++) {
			
			for (int y = minoCoordinates[mino][1]+yPos-1; y >= 0; y--) {
				
				if (board[minoCoordinates[mino][0]][y] == 0) {
					cost += holeCost-(holeCostDecay);
				}
				holeCostDecay++;
			}
		}
		
		//Board flatness (or rather, bumpiness)
		int yLevel = 0;
		for (int y = 19; y > 0; y--) { //get to the highest block in the first column (the column at x = 0)
			
			if (board[0][y] == 1) {
				yLevel = y+1;
				break;
			}
		}
		
		for (int x = 0; x < 10; x++) {
			
			if (yLevel != 0) {
				
				if (board[x][yLevel] == 1) { //we need to search up
					
					yLevel++;
					cost += bumpCost;
					
					while (board[x][yLevel] == 1) {
						yLevel++;
						cost += bumpCost;
					}
				}
			}
			if (board[x][yLevel-1] == 0) { //we need to search down
				
				yLevel--;
				cost += bumpCost;
				
				while (board[x][yLevel] == 0) {
					yLevel--;
					cost += bumpCost;
				}
			}
		}
		
		return cost;
	}
	
	private static int DecideMode(short[][] board) {
		
		//0 means you could play a tetris
		//1 means continue stacking 9-0
		//2 means clean up stack
		
		short sum = 0;
		
		for (int x = 0; x < board.length-1; x++) {
			sum += board[x][3];
		}
		
		if (sum == 10) {
			sum = 0;
			
			for (int x = 0; x < board.length-1; x++) {
				
				for (int y = 0; y < 3; y++) {
					sum += board[x][y];
				}
			}
			
			if (sum == 30) { //no holes found
				return 0;
			}
			else { //holes found
				return 2;
			}
		}
		else {
			return 1;
		}
	}
}
