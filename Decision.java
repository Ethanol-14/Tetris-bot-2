public class Decision {
	private static final int hole = -80;
	private static final int holeCover = -10;
	private static final int bump = -6;
	private static final int stackSize = -3;
	private static final int stackSizeSquared = -2;
	private static final int[] clears = {0, -70, -50, -30, 80};
	
	public static Boardstate FindBestPlacement(int[] queue, int pool, int [][] board) {
		//the queue is an integer array that represents the piece queue
		//the pool is a number from 0 to 100 of the rough percentage of tree you are not trimming off. ex; if you set it to 10 it will only take around the top 10% best placements for further planning
		//board is a 2D array of 0s and 1s that represent the board state

		int highscoreIndex = 1;
		
		Boardstate[] fields = TestCombinations(queue[0], board);
		
		if (queue.length == 1) {
			for (int i = 1; i < fields.length; i++) {
				if (fields[i].GetScore() > fields[highscoreIndex].GetScore()) {
					highscoreIndex = i;
				}
			}
			return fields[highscoreIndex];
		}
		else {
			//take out first queue piece cause you placed it
			int[] updatedQueue = new int[queue.length-1];
			
			for (int i = 0; i < updatedQueue.length; i++) {
				updatedQueue[i] = queue[i+1];
			}
			
			//find average cost of all combinations
			int threshold = 0;
			int lowestScore = fields[0].GetScore();
			int highestScore = fields[0].GetScore();
			
			for (int i = 0; i < fields.length; i++) {
				
				threshold = fields[i].GetScore();
				
				if (threshold > highestScore) {
					highestScore = threshold;
				}
				else if (threshold < lowestScore) {
					lowestScore = threshold;
				}
			}
			
			threshold = ((lowestScore*pool) + (highestScore*(100-pool))) / 100;
			
			//test everything above average
			for (int i = 0; i < fields.length; i++) {
				if (fields[i].GetScore() >= threshold) {
					Boardstate tempField = FindBestPlacement(updatedQueue, pool, fields[i].GetBoard());
					fields[i].ChangeScore(tempField.GetScore());
					fields[i].SetBoard(tempField.GetBoard());
					
					highscoreIndex = i; //prevent the next for loop from trying to GetScore from a null fields[i]
				}
				else {
					fields[i] = null;
				}
			}
			
			for (int i = 1; i < fields.length; i++) {
				if (fields[i] != null && (fields[i].GetScore() > fields[highscoreIndex].GetScore())) {
					highscoreIndex = i;
				}
			}
			
			return fields[highscoreIndex];
		}
	}
			
	private static Boardstate[] TestCombinations(int piece, int[][] board) { //the returned 2D array will have its first set of indices for the placement number, and the second set of indices for the piece position data
		
		int i = 0;
		
		if (piece == 0) { //O piece
			Boardstate[] fields = new Boardstate[9];
			
			for (int disp = -4; disp < 5; disp++) {
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);

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
			
			return fields;
		}
		else if (piece == 1) { //I piece

			Boardstate[] fields = new Boardstate[17];
			
			for (int disp = -3; disp < 4; disp++) { //rotation 0
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
			
			return fields;
		}
		else if (piece == 2) { //S piece

			Boardstate[] fields = new Boardstate[17];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
			
			return fields;
		}
		else if (piece == 3) { //Z piece

			Boardstate[] fields = new Boardstate[17];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
			
			return fields;
		}
		else if (piece == 4) { //L piece

			Boardstate[] fields = new Boardstate[34];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
			
			return fields;
		}
		else if (piece == 5) { //J piece

			Boardstate[] fields = new Boardstate[34];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
			
			return fields;
		}
		else if (piece == 6) { //T piece

			Boardstate[] fields = new Boardstate[34];
			
			for (int disp = -3; disp < 5; disp++) { //rotation 0
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
				fields[i].SetPieceData(0, 0, disp+3);
				fields[i].SetPieceData(0, 1, 0);
				
				fields[i].SetPieceData(1, 0, disp+4);
				fields[i].SetPieceData(1, 1, 0);
				
				fields[i].SetPieceData(2, 0, disp+5);
				fields[i].SetPieceData(2, 1, 0);
				
				fields[i].SetPieceData(3, 0, disp+4);
				fields[i].SetPieceData(3, 1, 1);
				
				fields[i].SetMovement(disp, 0);
				fields[i].SetBoardAndScore(CalculateCost(fields[i]));
				
				i++;
			}
			for (int disp = -4; disp < 5; disp++) { //rotation 1 (cw)
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
				
				fields[i] = new Boardstate();
				
				fields[i].SetBoard(board);
				
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
			
			return fields;
		}
		return null;
	}

	private static Boardstate CalculateCost(Boardstate field) {
		
		//Drop piece
		boolean contact = false;
		int yPos = 16;
		
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
		
		field.ChangeScore(rank*stackSize);
		field.ChangeScore(rank*rank*stackSizeSquared);
		field.ChangeScore(clears[linesCleared]);
		
		//Hole costs
		int potentialCovers = 0;
		
		for (int x = 0; x < 10; x++) { //mfw when I need to re-write the entirety of this hole cost section to implement hole cost decay
			
			potentialCovers = 0;
			
			for (int y = 17; y >= 0; y--) {
				
				potentialCovers += field.GetBoard(x, y+1);
				
				if (field.GetBoard(x, y+1) == 1 && field.GetBoard(x, y) == 0) {
					field.ChangeScore(hole);
					field.ChangeScore(potentialCovers*holeCover);
					
					while (y >= 0) {
						if (field.GetBoard(x, y) == 0 && field.GetBoard(x, y+1) == 1) {
							field.ChangeScore(hole);
						}
						y--;
					}
					break;
				}
			}
		}
		
		//Board flatness (or rather, bumpiness)
		int yLevel = 0;
		for (int y = 16; y >= 0; y--) { //get to the highest block in the first column (the column at x = 0)
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
						yLevel--;
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
		
		//System.out.println("Bump count: "+tempbumpcount+"\n");
		/*System.out.println("Cost: "+field.GetScore());
		Board.Setboard(field.GetBoard());
		Board.Refresh();
		Delay(5);*/
		
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