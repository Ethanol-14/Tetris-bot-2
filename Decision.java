package tetrismode;

public class Decision {
	private static byte holeCost = 10;
	private static byte bumpCost = 1;
	
	public static byte[] FindBestPlacement(byte[] pieces, byte[][] board) {
		
		//Decide whether we need to clean up the stack, play a tetris, or continue stacking 9-0
		byte mode = DecideMode(board);
		
		if (mode == 0 && pieces[0] == 0) { //tetris
			byte[] movement = new byte[2];
			movement[0] = 4;
			movement[1] = 1;
			return movement;
		}
		else if (mode == 2) { //clean stack
			
		}
		else { //stack 9-0
			
		}
		
		return null;
	}
	
	private static short[] TestCombinations(byte piece, byte[][] board, byte range) {
		
		byte[][] pieceData = new byte[4][2];
		short[] movement = new short[3];
		short lowestCost = 9999;
		short currentCost = 0;
		
		if (piece == 0) { //O piece
			for (byte disp = -4; disp < range-5; disp++) {
				pieceData[0][0] = (byte)(disp+4);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+5);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 1;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 0;
					movement[2] = currentCost;
				}
			}
		}
		else if (piece == 1) { //I piece
			for (byte disp = -3; disp < range-6; disp++) { //rotation 0
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte)(disp+5);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte)(disp+6);
				pieceData[3][1] = 0;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 0;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -5; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte)(disp+5);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+5);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+5);
				pieceData[2][1] = 2;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 3;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 1;
					movement[2] = currentCost;
				}
			}
		}
		else if (piece == 2) { //S piece
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte)(disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 1;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 0;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte)(disp+4);
				pieceData[0][1] = 2;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+5);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 0;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 1;
					movement[2] = currentCost;
				}
			}
		}
		else if (piece == 3) { //Z piece
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+4);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 0;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 0;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte)(disp+4);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+5);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 2;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 1;
					movement[2] = currentCost;
				}
			}
		}
		else if (piece == 4) { //L piece
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte)(disp+5);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 1;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 0;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 2;
				
				pieceData[1][0] = (byte)(disp+3);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+3);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte)(disp+4);
				pieceData[3][1] = 0;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 1;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -3; disp < range-5; disp++) { //rotation 2 (180)
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+3);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 1;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 2;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -3; disp < range-4; disp++) { //rotation 3 (ccw)
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 2;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 2;
				
				pieceData[2][0] = (byte)(disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte)(disp+4);
				pieceData[3][1] = 0;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 3;
					movement[2] = currentCost;
				}
			}
		}
		else if (piece == 5) { //J piece
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte)(disp+3);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte)(disp+4);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 0;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 0;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+3);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+3);
				pieceData[2][1] = 2;
				
				pieceData[3][0] = (byte)(disp+4);
				pieceData[3][1] = 2;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 1;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -3; disp < range-5; disp++) { //rotation 2 (180)
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+5);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 0;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 2;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -3; disp < range-4; disp++) { //rotation 3 (ccw)
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte)(disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte)(disp+4);
				pieceData[3][1] = 2;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 3;
					movement[2] = currentCost;
				}
			}
		}
		else if (piece == 6) { //T piece
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte)(disp+5);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte)(disp+4);
				pieceData[3][1] = 1;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 0;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte)(disp+4);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+4);
				pieceData[2][1] = 2;
				
				pieceData[3][0] = (byte)(disp+5);
				pieceData[3][1] = 1;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 1;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -3; disp < range-5; disp++) { //rotation 2 (180)
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte)(disp+5);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte)(disp+4);
				pieceData[3][1] = 0;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 2;
					movement[2] = currentCost;
				}
			}
			for (byte disp = -3; disp < range-4; disp++) { //rotation 3  (ccw)
				pieceData[0][0] = (byte)(disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte)(disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte)(disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte)(disp+4);
				pieceData[3][1] = 2;
				
				currentCost = CalculateCost(pieceData, board, range);
				
				if (currentCost < lowestCost) {
					lowestCost = currentCost;
					movement[0] = disp;
					movement[1] = 3;
					movement[2] = currentCost;
				}
			}
		}
		
		return movement;
	}
	
	private static short CalculateCost(byte[][] minoCoordinates, byte[][] originalBoard, byte range) {
		
		short cost = 0;
		
		byte board[][] = new byte[10][20];
		
		board = originalBoard; //could go wrong
		
		//Drop piece
		boolean contact = false;
		byte yPos = 16;
		
		while (!contact && yPos >= 0) {
			
			for (byte mino = 0; mino < minoCoordinates.length; mino++) {
				
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
		for (byte mino = 0; mino < minoCoordinates.length; mino++) {
			board[minoCoordinates[mino][0]][minoCoordinates[mino][1]+yPos] = 1;
		}
		
		//Clear lines
		byte sum = 0;
		byte linesCleared = 0;
		
		for (int y = 0; y < 20-linesCleared; y++) {
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
		byte holeCostDecay = holeCost;
		
		for (byte mino = 0; mino < minoCoordinates.length; mino++) {
			
			for (byte y = (byte)(minoCoordinates[mino][1]+yPos-1); y >= 0; y--) {
				
				if (board[minoCoordinates[mino][0]][y] == 0) {
					cost += holeCostDecay;
				}
				holeCostDecay /= 2;
			}
		}
		
		//Board flatness (or rather, bumpiness)
		byte yLevel = 0;
		for (byte y = 19; y > 0; y--) { //get to the highest block in the first column (the column at x = 0)
			if (board[0][y] == 1) {
				yLevel = (byte)(y+1);
				break;
			}
		}
		
		for (byte x = 1; x < range; x++) {
			
			if (board[x][yLevel] == 1) { //we need to search up
				while (board[x][yLevel] == 1) {
					yLevel++;
					cost += bumpCost;
					
					if (yLevel >= 17) { //too high
						break;
					}
				}
			}
			else if (board[x][yLevel-1] == 0) { //we need to search down
				while (board[x][yLevel] == 0) {
					yLevel--;
					cost += bumpCost;
					
					if (yLevel < 0) { //bottom of board
						break;
					}
				}
				yLevel++;
				cost -= bumpCost;
			}
		}
		
		return cost;
	}
	
	private static byte DecideMode(byte[][] board) {
		
		//0 means you could play a tetris
		//1 means continue stacking 9-0
		//2 means clean up stack
		
		byte sum = 9;
		
		for (byte x = 0; x < board.length-1; x++) {
			if (board[x][3] == 0) {
				sum--;
				if (board[x][4] == 1) { //hole found on 4th row
					return 2;
				}
			}
		}
		
		if (sum < 9) { //4th row is not filled yet
			sum = 0;
			for (byte x = 0; x < board.length-1; x++) {
				for (byte y = 0; y < 3; y++) {
					sum += board[x][y];
				}
			}
			if (sum == 27) { //no holes below 4th row, keep stacking
				return 1;
			}
			else { //at least one hole found
				return 2;
			}
		}
		else {
			sum = 0;
			for (byte x = 0; x < board.length-1; x++) {
				for (byte y = 0; y < 3; y++) {
					sum += board[x][y];
				}
			}
			if (sum == 27) { //no holes below 4th row, take tetris if available
				return 0;
			}
			else { //at least one hole found
				return 2;
			}
		}
	}
}
