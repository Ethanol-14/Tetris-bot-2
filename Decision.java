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
	
	private static int[] TestCombinations(int piece, short[][] board) {
		
		if (piece == 0) {
			
		}
		if (piece == 1) {
			
		}
		if (piece == 2) {
			
		}
		if (piece == 3) {
			
		}
		if (piece == 4) {
			
		}
		if (piece == 5) {
			
		}
		if (piece == 6) {
			
		}
		
		return null; //error return
	}
	
	private static int CalculateCost(int[][] minoCoordinates, short[][] board) {
		
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
		
		//Clear lines
		
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
