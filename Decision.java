public class Decision {
	private static final int hole = -30;
	private static final int holeCover = -10;
	private static final int bump = -3;
	private static final int stackSize = -2;
	//private static final int well = -8;
	private static final int[] clears = {0, -20, -10, 10, 40};
	
	public static int[] FindBestPlacement(int[] queue, int poolSize, int[][] board) {
		//the queue is an integer array that represents the piece queue
		//the poolSize is the accepted pool size that the bot will use for lookahead. for example, poolSize = 5 means that the bot will take the 5 best placements of the current piece then use those to update the boardstate and lookahead
		//board is a 2D array of 0s and 1s that represent the board state

		int lowestCostIndex = 0;

		Boardstate[] fields = TestCombinations(queue[0], board);
		
		if (queue.length == 1) {
			for (int i = 1; i < fields.length; i++) {
				if (fields[i].GetScore() < fields[lowestCostIndex].GetScore()) {
					lowestCostIndex = i;
				}
			}
			
			return fields[lowestCostIndex].GetMovementAndScore();
		}
		else {
			
		}
		
		return null;
	}
			
	private static Boardstate[] TestCombinations(int piece, int[][] board) { //the returned 2D array will have its first set of indices for the placement number, and the second set of indices for the piece position data
		
		int i = 0;
		
		Boardstate[] fields = new Boardstate[0];
		
		if (piece == 0) { //O piece
			fields = new Boardstate[9];
			
			for (int disp = -4; disp < 5; disp++) {

				fields[i].SetPieceData(0, 0, disp+4);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 0);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 1);
				
				fields[i].SetMovement(disp, 0);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
		}
		else if (piece == 1) { //I piece

			fields = new Boardstate[17];
			
			for (int disp = -3; disp < 4; disp++) { //rotation 0
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 0);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 0);
				
				fields[i].SetPieceData(3, 0, disp+6);
				fields[i].SetPieceData(3, 1, 0);
				
				fields[i].SetMovement(disp, 0);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -5; disp < 5; disp++) { //rotation 1 (cw)
				
				fields[i].SetPieceData(0, 0, disp+5);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+5);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 2);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 3);
				
				fields[i].SetMovement(disp, 1);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
		}
		else if (piece == 2) { //S piece

			fields = new Boardstate[17];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 0);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 1);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 1);
				
				fields[i].SetMovement(disp, 0);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				
				fields[i].SetPieceData(0, 0, disp+4);
				fields[i].SetPieceData(0, 1, 2);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 1);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 0);
				
				fields[i].SetMovement(disp, 1);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
		}
		else if (piece == 3) { //Z piece

			fields = new Boardstate[17];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 1);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 0);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 0);
				
				fields[i].SetMovement(disp, 0);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				
				fields[i].SetPieceData(0, 0, disp+4);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 1);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 2);
				
				fields[i].SetMovement(disp, 1);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
		}
		else if (piece == 4) { //L piece

			fields = new Boardstate[34];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 0);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 0);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 1);
				
				fields[i].SetMovement(disp, 0);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				
				fields[i].SetPieceData(0, 0, disp+4);
				fields[i].SetPieceData(0, 1, 2);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 0);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 0);
				
				fields[i].SetMovement(disp, 1);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -3; disp < 5; disp++) { //rotation 2 (180)
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+3);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 1);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 1);
				
				fields[i].SetMovement(disp, 2);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -3; disp < 6; disp++) { //rotation 3 (ccw)
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 2);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 2);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 1);
				
				fields[i].SetPieceData(3, 0, disp+4);
				fields[i].SetPieceData(3, 1, 0);
				
				fields[i].SetMovement(disp, 3);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
		}
		else if (piece == 5) { //J piece

			fields = new Boardstate[34];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 1);
				
				fields[i].SetPieceData(1, 0, disp+3);
				fields[i].SetPieceData(1, 1, 0);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 0);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 0);
				
				fields[i].SetMovement(disp, 0);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				
				fields[i].SetPieceData(0, 0, disp+4);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 2);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 2);
				
				fields[i].SetMovement(disp, 1);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -3; disp < 5; disp++) { //rotation 2 (180)
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 1);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 1);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 0);
				
				fields[i].SetMovement(disp, 2);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -3; disp < 6; disp++) { //rotation 3 (ccw)
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 0);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 1);
				
				fields[i].SetPieceData(3, 0, disp+4);
				fields[i].SetPieceData(3, 1, 2);
				
				fields[i].SetMovement(disp, 3);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
		}
		else if (piece == 6) { //T piece

			fields = new Boardstate[34];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 0);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 0);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 1);
				
				fields[i].SetMovement(disp, 0);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				
				fields[i].SetPieceData(0, 0, disp+4);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 2);
				
				fields[i].SetPieceData(3, 0, disp+5);
				fields[i].SetPieceData(3, 1, 1);
				
				fields[i].SetMovement(disp, 1);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -3; disp < 5; disp++) { //rotation 2 (180)
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 1);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 1);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 1);
				
				fields[i].SetPieceData(3, 0, disp+4);
				fields[i].SetPieceData(3, 1, 0);
				
				fields[i].SetMovement(disp, 2);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -3; disp < 6; disp++) { //rotation 3 (ccw)
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 1);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 0);
				
				fields[i].SetPieceData(2, 0, disp+4);
				fields[i].SetPieceData(2, 1, 1);
				
				fields[i].SetPieceData(3, 0, disp+4);
				fields[i].SetPieceData(3, 1, 2);
				
				fields[i].SetMovement(disp, 3);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
		}
		
		return fields;
	}

	private static Boardstate CalculateCost(Boardstate field) {
		
		/*byte[][] board = new byte[10][25];
		for (int x = 0; x < originalBoard.length; x++) { //might need to bring this back due to java's default referencing... hopefully not tho
			for (int y = 0; y < originalBoard[0].length; y++) {
				board[x][y] = originalBoard[x][y];
			}
		}*/
		
		//Drop piece
		boolean contact = false;
		int yPos = 17;
		
		while (!contact && yPos >= 0) {
			
			for (int mino = 0; mino < 4; mino++) {
				
				if (field.GetBoard(field.GetPieceData(mino, 0), yPos+field.GetPieceData(mino, 1)) == 1) {
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
		for (int mino = 0; mino < 4; mino++) {
			field.SetBoard(field.GetPieceData(mino, 0), field.GetPieceData(mino, 1)+yPos, 1);
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
			
			if (sum == 0) { //nothing left

				for (int y2 = rank; y2 < 18; y2++) {
					for (int x = 0; x < 10; x++) {
						field.SetBoard(x, y2, 0);
					}
				}
				
				field.ChangeScore(y*stackSize);
				
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

		field.ChangeScore(clears[linesCleared]);
		
		//Calculate costs
		
		//Hole costs
		//redo hole costs cause it sucks rn
		
		
		
		//Board flatness (or rather, bumpiness)
		int yLevel = 0;
		for (int y = 18; y >= 0; y--) { //get to the highest block in the first column (the column at x = 0)
			if (field.GetBoard(0, y) == 1) {
				yLevel = y+1;
				break;
			}
		}
		
		//int tempbumpcount=0;
		
		for (int x = 1; x < 10; x++) {
			
			if (field.GetBoard(x, yLevel) == 1) { //we need to search up
				while (field.GetBoard(x, yLevel) == 1) {
					yLevel++;
					field.ChangeScore(bump);
					
					//tempbumpcount++;
					
					if (yLevel >= 18) { //too high
						break;
					}
				}
			}
			else if (yLevel > 0 && field.GetBoard(x, yLevel-1) == 0) { //we need to search down
				while (field.GetBoard(x, yLevel-1) == 0) {
					yLevel--;
					field.ChangeScore(bump);
					
					//tempbumpcount++;
					
					if (yLevel < 1) { //bottom of board
						break;
					}
				}
			}
		}
		
		//High wells
		//At x = 0
		
		/*for (int y = 17; y >= 0; y--) {
			if (field.GetBoard(0, y) == 0) {
				if (field.GetBoard(1, y) == 1) {
					while (y >= 0 && field.GetBoard(0, y) == 0) {
						y--;
						field.ChangeScore(well);
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
			if (field.GetBoard(9, y) == 0) {
				if (field.GetBoard(8, y) == 1) {
					while (y >= 0 && field.GetBoard(9, y) == 0) {
						y--;
						field.ChangeScore(well);
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
			for (int y = 17; y >= 0; y--) {
				if (field.GetBoard(x, y) == 0) {
					if (field.GetBoard(x-1, y) + field.GetBoard(x+1, y) == 2) {
						while (y >= 0 && field.GetBoard(x, y) == 0) {
							y--;
							field.ChangeScore(well);
						}
						break;
					}
				}
				else {
					break;
				}
			}
		}*/
		
		//System.out.println("Bump count: "+tempbumpcount+"\n");
		/*System.out.println("Cost: "+cost);
		Board.Setboard(board);
		Board.Refresh();
		Delay(1000);*/
		
		return field;
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