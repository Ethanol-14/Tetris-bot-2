public class Decision {
	private static short holeCost = 30;
	private static short holeSeverity = 10;
	private static short holeCostDecayRate = 10;
	private static short bumpCost = 3;
	private static short lowCost = 2;
	private static short wellCost = 8;
	private static short lineClearReward = 8;
	
	public static short[] FindBestPlacement(int[] queue, int poolSize, byte[][] board) {
		//the queue is an integer array that represents the piece queue
		//the poolSize is the accepted pool size that the bot will use for lookahead. for example, poolSize = 5 means that the bot will take the 5 best placements of the current piece then use those to update the boardstate and lookahead
		//board is a 2D array of 0s and 1s that represent the board state

		byte lowestCostIndex = 0;
		
		if (queue.length == 1) {
			
			short[][] results = GiveRatings((byte) queue[0], board);
			
			for (byte i = 0; i < results.length; i++) {
				if (results[i][2] < results[lowestCostIndex][2]) {
					lowestCostIndex = i;
				}
			}
			
			return results[lowestCostIndex];
		}
		else {
			short[][] results = GiveRatings((byte) queue[0], board);
			short[][] lowests = new short[poolSize][3];
			byte[] indexIgnores = new byte[poolSize];
			boolean ignoreIndex = false;
			
			for (byte x = 0; x < poolSize && x < results.length; x++) {
				lowestCostIndex = 0;
				for (byte i = 0; i < results.length; i++) {
					if (results[i][2] < results[lowestCostIndex][2]) {
						for (byte i2 = 0; i2 < indexIgnores.length; i2++) {
							if (i == indexIgnores[i2]) {
								ignoreIndex = true;
								break;
							}
						}
						if (!ignoreIndex) {
							lowestCostIndex = i;
						}
						ignoreIndex = false;
					}
				}
				lowests[x] = results[lowestCostIndex];
			}
			
			//now how tf do I pass a whole updated board...
			//okay since java is pass by reference maybe I'll just have CalculateCost give me a board
			//and since it'd be way too messy, I'll have to make actual objects now....
			//fml maybe I'll just re-simulate piece falling and lineclearing
			//I honestly don't know which solution is faster
			
			int[] newQueue = new int[queue.length-1];
			
			for (byte i = 0; i < newQueue.length; i++) {
				newQueue[i] = queue[i+1];
			}
			
			byte[][] boardCopy = new byte[10][25];
			boardCopy = board;
			
			for (byte i = 0; i < poolSize && i < results.length; i++) {
				
				for (byte x = 0; x < board.length; x++) {
					for (byte y = 0; y < board[0].length; y++) {
						boardCopy[x][y] = board[x][y];
					}
				}
				
				FindBestPlacement(newQueue, poolSize, boardCopy);
			}
		}
		
		return null;
	}
	
	public static short[][] GiveRatings(byte piece, byte[][] board) {
		
		//Decide whether we need to clean up the stack, play a tetris, or continue stacking 9-0
		byte mode = DecideMode(board);
		
		if (mode == 0 && piece == 1) { //tetris
			short[][] results = new short[1][3];
			results[0][0] = 4;
			results[0][1] = 1;
			results[0][2] = (short) (lineClearReward*-4);
			//System.out.println("TETRIS WOOO :D");
			return results;
		}
		else if (mode == 2) { //clean stack
			//System.out.println("cleaning stack :(");
			
			short[][] results = TestCombinations(piece, board, (byte) 10);
			return results;
		}
		else { //stack 9-0
			//System.out.println("Stacking 9-0 :)");
			
			short[][] results = TestCombinations(piece, board, (byte) 9);
			return results;
		}
	}
			
	private static short[][] TestCombinations(byte piece, byte[][] board, byte range) { //the returned 2D array will have its first set of indices for the placement number, and the second set of indices for the piece position data
		
		byte[][] pieceData = new byte[4][2];
		int i = 0;
		
		short[][] feedback = new short[0][0];
		
		if (piece == 0) { //O piece
			
			feedback = new short[range-1][3];
			
			for (byte disp = -4; disp < range-5; disp++) {
				pieceData[0][0] = (byte) (disp+4);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+5);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
		}
		else if (piece == 1) { //I piece

			feedback = new short[(2*range)-3][3];
			
			for (byte disp = -3; disp < range-6; disp++) { //rotation 0
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte) (disp+5);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte) (disp+6);
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -5; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte) (disp+5);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+5);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+5);
				pieceData[2][1] = 2;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 3;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
		}
		else if (piece == 2) { //S piece

			feedback = new short[(2*range)-3][3];
			
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte) (disp+4);
				pieceData[0][1] = 2;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+5);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
		}
		else if (piece == 3) { //Z piece

			feedback = new short[(2*range)-3][3];
			
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte) (disp+4);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+5);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 2;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
		}
		else if (piece == 4) { //L piece

			feedback = new short[(4*range)-6][3];
			
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte) (disp+5);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte) (disp+4);
				pieceData[0][1] = 2;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -3; disp < range-5; disp++) { //rotation 2 (180)
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+3);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 2;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -3; disp < range-4; disp++) { //rotation 3 (ccw)
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 2;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 2;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte) (disp+4);
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 3;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
		}
		else if (piece == 5) { //J piece

			feedback = new short[(4*range)-6][3];
			
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte) (disp+3);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte) (disp+4);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 2;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 2;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -3; disp < range-5; disp++) { //rotation 2 (180)
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+5);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 2;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -3; disp < range-4; disp++) { //rotation 3 (ccw)
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte) (disp+4);
				pieceData[3][1] = 2;
				
				feedback[i][0] = disp;
				feedback[i][1] = 3;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
		}
		else if (piece == 6) { //T piece

			feedback = new short[(4*range)-6][3];
			
			for (byte disp = -3; disp < range-5; disp++) { //rotation 0
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte) (disp+5);
				pieceData[2][1] = 0;
				
				pieceData[3][0] = (byte) (disp+4);
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -4; disp < range-5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = (byte) (disp+4);
				pieceData[0][1] = 0;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 2;
				
				pieceData[3][0] = (byte) (disp+5);
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -3; disp < range-5; disp++) { //rotation 2 (180)
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 1;
				
				pieceData[2][0] = (byte) (disp+5);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte) (disp+4);
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 2;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
			for (byte disp = -3; disp < range-4; disp++) { //rotation 3 (ccw)
				pieceData[0][0] = (byte) (disp+3);
				pieceData[0][1] = 1;
				
				pieceData[1][0] = (byte) (disp+4);
				pieceData[1][1] = 0;
				
				pieceData[2][0] = (byte) (disp+4);
				pieceData[2][1] = 1;
				
				pieceData[3][0] = (byte) (disp+4);
				pieceData[3][1] = 2;
				
				feedback[i][0] = disp;
				feedback[i][1] = 3;
				feedback[i][2] = CalculateCost(pieceData, board, range);
				
				i++;
			}
		}
		
		return feedback;
	}

	private static short CalculateCost(byte[][] minoCoordinates, byte[][] originalBoard, byte range) {
		
		short cost = 0;
		
		byte board[][] = new byte[10][25];
		
		for (byte x = 0; x < originalBoard.length; x++) {
			for (byte y = 0; y < originalBoard[0].length; y++) {
				board[x][y] = originalBoard[x][y];
			}
		}
		
		//Drop piece
		boolean contact = false;
		byte yPos = 20;
		
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
		
		cost += yPos*lowCost;
		
		//Update board to contain dropped piece
		for (byte mino = 0; mino < minoCoordinates.length; mino++) {
			board[minoCoordinates[mino][0]][minoCoordinates[mino][1]+yPos] = 1;
		}
		
		//Clear lines
		byte sum = 0;
		byte rank = 0;
		byte linesCleared = 0;
		
		for (byte y = 0; y < 18; y++) {
			sum = 0;
			
			for (byte x = 0; x < 10; x++) {
				sum += board[x][y];
			}
			
			if (sum == 0) {

				for (byte y2 = rank; y2 < 18; y2++) {
					for (byte x = 0; x < 10; x++) {
						board[x][y2] = 0;
					}
				}
				break;
			}
			else if (sum != 10) {
				for (byte x = 0; x < 10; x++) {
					board[x][rank] = board[x][y];
				}
				rank++;
			}
			else {
				linesCleared++;
			}
		}
		
		cost -= linesCleared*lineClearReward;
		
		//Calculate costs
		
		//Hole costs
		short decayedHoleCost = holeSeverity;
		short holeRanks = 1;
		boolean holeFound = false;
		
		//int tempholecount = 0;
		
		if (linesCleared != 0) {
			for (byte y = 17; y >= 0; y--) {
				for (byte x = 0; x < range; x++) {
					if (board[x][y+1] == 1 && board[x][y] == 0) {
						holeFound = true;
						cost += holeCost;
					}
				}
				
				if (holeFound) {
					holeRanks++;
					
					holeFound = false;
				}
			}
		}
		else {
			for (byte y = 17; y >= 0; y--) {
				for (byte x = 0; x < range; x++) {
					if (board[x][y+1] == 1 && board[x][y] == 0) {
						holeFound = true;
						cost += holeCost + (holeSeverity/holeRanks);
						
						for (byte y2 = (byte) (y-1); y2 >= 0; y2--) { //the deeper the hole the worse
							if (board[x][y2] == 1) {
								break;
							}
							
							cost += decayedHoleCost;
						}
						for (byte y2 = (byte) (y+1); y2 < 18; y2++) { //are you stacking on top of a hole? that's bad
							if (board[x][y2] == 0) {
								break;
							}
							
							cost += decayedHoleCost;
						}
						//tempholecount++;
					}
				}
				
				if (holeFound) {
					decayedHoleCost /= holeCostDecayRate;
					holeRanks++;
					
					holeFound = false;
				}
			}
		}
		
		//System.out.println("Hole count: "+tempholecount);
		
		//Board flatness (or rather, bumpiness)
		byte yLevel = 0;
		for (byte y = 17; y >= 0; y--) { //get to the highest block in the first column (the column at x = 0)
			if (board[0][y] == 1) {
				yLevel = (byte) (y+1);
				break;
			}
		}
		
		//int tempbumpcount=0;
		
		for (byte x = 1; x < range; x++) {
			
			if (board[x][yLevel] == 1) { //we need to search up
				while (board[x][yLevel] == 1) {
					yLevel++;
					cost += bumpCost;
					
					//tempbumpcount++;
					
					if (yLevel >= 17) { //too high
						break;
					}
				}
			}
			else if (yLevel > 0 && board[x][yLevel-1] == 0) { //we need to search down
				while (board[x][yLevel-1] == 0) {
					yLevel--;
					cost += bumpCost;
					
					//tempbumpcount++;
					
					if (yLevel < 1) { //bottom of board
						break;
					}
				}
			}
		}
		
		//High wells. Only considers costs for wells that are not multiple
		//At x = 0
		short costiestWell = 0;
		short currentWellCost = 0;
		
		for (byte y = 17; y >= 0; y--) {
			currentWellCost = 0;
			if (board[0][y] == 0) {
				if (board[1][y] == 1) {
					currentWellCost -= wellCost;
					while (y >= 0 && board[0][y] == 0) {
						y--;
						cost += wellCost;
						currentWellCost += wellCost;
					}
					if (currentWellCost > costiestWell) {
						costiestWell = currentWellCost;
					}
					break;
				}
			}
			else {
				break;
			}
		}
		//At x = 10
		for (byte y = 17; y >= 0; y--) {
			currentWellCost = 0;
			if (board[9][y] == 0) {
				if (board[8][y] == 1) {
					currentWellCost -= wellCost;
					while (y >= 0 && board[9][y] == 0) {
						y--;
						cost += wellCost;
						currentWellCost += wellCost;
					}
					if (currentWellCost > costiestWell) {
						costiestWell = currentWellCost;
					}
					break;
				}
			}
			else {
				break;
			}
		}
		//And all the x in between
		for (byte x = 1; x < 9; x++) {
			currentWellCost = 0;
			for (byte y = 17; y >= 0; y--) {
				if (board[x][y] == 0) {
					if (board[x-1][y] + board[x+1][y] == 2) {
						currentWellCost -= wellCost;
						while (y >= 0 && board[x][y] == 0) {
							y--;
							cost += wellCost;
							currentWellCost += wellCost;
						}
						if (currentWellCost > costiestWell) {
							costiestWell = currentWellCost;
						}
						break;
					}
				}
				else {
					break;
				}
			}
		}
		
		cost -= costiestWell; //ignore the highest costing well, because that's really your main well
		
		//System.out.println("Bump count: "+tempbumpcount+"\n");
		/*System.out.println("Cost: "+cost);
		Board.Setboard(board);
		Board.Refresh();
		Delay(1000);*/
		
		return cost;
	}
	
	private static void Delay(int msec) {
		try {
		    Thread.sleep(msec);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	private static byte DecideMode(byte[][] board) {
		
		//0 means you could play a tetris
		//1 means continue stacking 9-0
		//2 means clean up stack
		
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < 15; y++) {
				if (board[x][y] == 0 && board[x][y+1] == 1) { //hole found
					return 2;
				}
			}
			
			if (board[x][12] == 1) {
				return 2;
			}
		}
		
		byte sum = 0;
		
		for (int x = 0; x < board.length-1; x++) {
			sum += board[x][3];
		}
		
		if (sum == board.length-1) { //4th row is filled too
			return 0;
		}
		else { //the stack has not reached 4 blocks height yet
			return 1;
		}
	}
}