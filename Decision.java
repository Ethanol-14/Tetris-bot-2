public class Decision {
	private static int hole = -30;
	private static int holeSeverity = -10;
	private static int holeDecayRate = 10;
	private static int bump = -3;
	private static int StackSize = -2;
	private static int well = -8;
	private static int[] clears = {0, -20, -10, 10, 40};
	
	public static int[] FindBestPlacement(int[] queue, int poolSize, int[][] board) {
		//the queue is an integer array that represents the piece queue
		//the poolSize is the accepted pool size that the bot will use for lookahead. for example, poolSize = 5 means that the bot will take the 5 best placements of the current piece then use those to update the boardstate and lookahead
		//board is a 2D array of 0s and 1s that represent the board state

		int lowestCostIndex = 0;

		Boardstate[] fields = TestCombinations(queue[0], board);
		
		if (queue.length == 1) {
			for (int i = 1; i < results.length; i++) {
				if (results[i][2] < results[lowestCostIndex][2]) {
					lowestCostIndex = i;
				}
			}
			
			return results[lowestCostIndex];
		}
		else {
			
		}
	}
			
	private static Boardstate[] TestCombinations(int piece, int[][] board) { //the returned 2D array will have its first set of indices for the placement number, and the second set of indices for the piece position data
		
		int[][] pieceData = new int[4][2];
		int i = 0;
		
		//int[][] feedback = new int[0][0];
		
		if (piece == 0) { //O piece
			
			//feedback = new int[9][3];
			Boardstate[] fields = new Boardstate[9];
			
			for (int disp = -4; disp < 5; disp++) {
				pieceData[0][0] = disp+4;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+5;
				pieceData[2][1] = 0;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 1;
				
				//feedback[i][0] = disp;
				//feedback[i][1] = 0;
				//feedback[i][2] = CalculateCost(pieceData, board);
				
				fields[i].SetMovement(disp, 0);
				fields[i].
				
				i++;
			}
		}
		else if (piece == 1) { //I piece

			feedback = new int[17][3];
			
			for (int disp = -3; disp < 4; disp++) { //rotation 0
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 0;
				
				pieceData[2][0] = disp+5;
				pieceData[2][1] = 0;
				
				pieceData[3][0] = disp+6;
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -5; disp < 5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = disp+5;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+5;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+5;
				pieceData[2][1] = 2;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 3;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
		}
		else if (piece == 2) { //S piece

			feedback = new int[17][3];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 0;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 1;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = disp+4;
				pieceData[0][1] = 2;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+5;
				pieceData[2][1] = 1;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
		}
		else if (piece == 3) { //Z piece

			feedback = new int[17][3];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 1;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 0;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = disp+4;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+5;
				pieceData[2][1] = 1;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 2;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
		}
		else if (piece == 4) { //L piece

			feedback = new int[34][3];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 0;
				
				pieceData[2][0] = disp+5;
				pieceData[2][1] = 0;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = disp+4;
				pieceData[0][1] = 2;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 0;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -3; disp < 5; disp++) { //rotation 2 (180)
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+3;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 1;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 2;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -3; disp < 6; disp++) { //rotation 3 (ccw)
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 2;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 2;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 1;
				
				pieceData[3][0] = disp+4;
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 3;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
		}
		else if (piece == 5) { //J piece

			feedback = new int[34][3];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 1;
				
				pieceData[1][0] = disp+3;
				pieceData[1][1] = 0;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 0;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = disp+4;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 2;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 2;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -3; disp < 5; disp++) { //rotation 2 (180)
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 1;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+5;
				pieceData[2][1] = 1;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 2;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -3; disp < 6; disp++) { //rotation 3 (ccw)
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 0;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 1;
				
				pieceData[3][0] = disp+4;
				pieceData[3][1] = 2;
				
				feedback[i][0] = disp;
				feedback[i][1] = 3;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
		}
		else if (piece == 6) { //T piece

			feedback = new int[34][3];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 0;
				
				pieceData[2][0] = disp+5;
				pieceData[2][1] = 0;
				
				pieceData[3][0] = disp+4;
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 0;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				pieceData[0][0] = disp+4;
				pieceData[0][1] = 0;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 2;
				
				pieceData[3][0] = disp+5;
				pieceData[3][1] = 1;
				
				feedback[i][0] = disp;
				feedback[i][1] = 1;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -3; disp < 5; disp++) { //rotation 2 (180)
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 1;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 1;
				
				pieceData[2][0] = disp+5;
				pieceData[2][1] = 1;
				
				pieceData[3][0] = disp+4;
				pieceData[3][1] = 0;
				
				feedback[i][0] = disp;
				feedback[i][1] = 2;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
			for (int disp = -3; disp < 6; disp++) { //rotation 3 (ccw)
				pieceData[0][0] = disp+3;
				pieceData[0][1] = 1;
				
				pieceData[1][0] = disp+4;
				pieceData[1][1] = 0;
				
				pieceData[2][0] = disp+4;
				pieceData[2][1] = 1;
				
				pieceData[3][0] = disp+4;
				pieceData[3][1] = 2;
				
				feedback[i][0] = disp;
				feedback[i][1] = 3;
				feedback[i][2] = CalculateCost(pieceData, board);
				
				i++;
			}
		}
		
		return feedback;
	}

	private static Boardstate CalculateCost(int[][] minoCoordinates, int[][] originalBoard) {
		
		Boardstate field = new Boardstate();
		field.SetBoard(originalBoard);
		
		/*byte[][] board = new byte[10][25];
		for (int x = 0; x < originalBoard.length; x++) { //might need to bring this back due to java's default referencing... hopefully not tho
			for (int y = 0; y < originalBoard[0].length; y++) {
				board[x][y] = originalBoard[x][y];
			}
		}*/
		
		//Drop piece
		boolean contact = false;
		int yPos = 20;
		
		while (!contact && yPos >= 0) {
			
			for (int mino = 0; mino < minoCoordinates.length; mino++) {
				
				if (field.GetBoard(minoCoordinates[mino][0], yPos+minoCoordinates[mino][1]) == 1) {
					//we are immersed in floor
					contact = true;
					yPos++;
					break;
				}
			}
			
			yPos--;
		}
		yPos++;
		
		//cost += yPos*lowCost;
		
		//Update board to contain dropped piece
		for (int mino = 0; mino < minoCoordinates.length; mino++) {
			field.SetBoard(minoCoordinates[mino][0], minoCoordinates[mino][1]+yPos, 1);
		}
		
		//Clear lines
		int sum = 0;
		int rank = 0;
		int linesCleared = 0;
		
		for (int y = 0; y < 18; y++) {
			sum = 0;
			
			for (int x = 0; x < 10; x++) {
				sum += field.GetBoard(x, y);
			}
			
			if (sum == 0) {

				for (int y2 = rank; y2 < 18; y2++) {
					for (int x = 0; x < 10; x++) {
						field.SetBoard(x, y2, 0);
					}
				}
				break;
			}
			else if (sum != 10) {
				for (int x = 0; x < 10; x++) {
					field.SetBoard(x, rank, field.GetBoard(x, y));
				}
				rank++;
			}
			else {
				linesCleared++;
			}
		}
		
		//cost -= linesCleared*lineClear;
		field.ChangeScore(clears[linesCleared]);
		
		//Calculate costs
		
		//Hole costs
		int decayedHoleCost = holeSeverity;
		int holeRanks = 1;
		boolean holeFound = false;
		
		//int tempholecount = 0;
		
		if (linesCleared != 0) {
			for (int y = 17; y >= 0; y--) {
				for (int x = 0; x < 10; x++) {
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
			for (int y = 17; y >= 0; y--) {
				for (int x = 0; x < 10; x++) {
					if (board[x][y+1] == 1 && board[x][y] == 0) {
						holeFound = true;
						cost += holeCost + (holeSeverity/holeRanks);
						
						for (int y2 = y-1; y2 >= 0; y2--) { //the deeper the hole the worse
							if (board[x][y2] == 1) {
								break;
							}
							
							cost += decayedHoleCost;
						}
						for (int y2 = y+1; y2 < 18; y2++) { //are you stacking on top of a hole? that's bad
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
		int yLevel = 0;
		for (int y = 17; y >= 0; y--) { //get to the highest block in the first column (the column at x = 0)
			if (board[0][y] == 1) {
				yLevel = y+1;
				break;
			}
		}
		
		//int tempbumpcount=0;
		
		for (int x = 1; x < 10; x++) {
			
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
		
		//High wells. Only considers costs for extra wells
		//At x = 0
		int costiestWell = 0;
		int currentWellCost = 0;
		
		for (int y = 17; y >= 0; y--) {
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
		for (int y = 17; y >= 0; y--) {
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
		for (int x = 1; x < 9; x++) {
			currentWellCost = 0;
			for (int y = 17; y >= 0; y--) {
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
}