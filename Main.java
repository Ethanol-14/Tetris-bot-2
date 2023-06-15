public class Main {
	
	//Parameters
	private static int depth = 4;
	
	private static int delay = 10;
	private static int movementDelay = 10;

	private static int[] queue = new int[depth];
	private static int[] holdQueue = new int[depth];
	private static int[] topLeft = new int[2];
	private static int[] slope = new int[2];
	private static int[][] piecePos = new int[depth+1][2];
	private static int[] gamePos = new int[2];
	private static int[] pieceColors = new int[7];
	private static int[] queuePieceColors = new int[7];
	
	public static void main (String[] args) {
		configureForJstrisOnSchoolComputer();
		
		int[][] board = new int[10][21];
		
		/*System.out.println("test");
		Board.Init();
		Board.Setboard(Screen.DetermineBoardData(topLeft, slope));
		queue[0] = 4;
		queue[1] = 3;
		queue[2] = 5;
		TestQueue(queue, 15);
		Board.Refresh();
		Delay(10000000);*/
		
		/*boolean lmao = true;
		Board.Init();
		while (lmao) {
			Board.Setboard(Screen.DetermineBoardData(topLeft, slope));
			Board.Refresh();
			Delay(10);
		}*/
		
		/*boolean lmao = true;
		while (lmao) {
			System.out.println(Screen.DeterminePiece(piecePos[1][0], piecePos[1][1], queuePieceColors));
			Delay(10);
		}*/

		Screen.SetBoardDimensions(10, 21);
		Screen.FocusGame(gamePos, 20);
		Screen.SetDelay(movementDelay);
		
		int failCount = 0;
		int piece = 0;
		int[] path = new int[3];
		int[] queuePath = new int[3];
		
		while (true) {
			board = Screen.DetermineBoardData(topLeft, slope);
			
			queue[0] = Screen.DeterminePiece(piecePos[0][0], piecePos[0][1], pieceColors);
			holdQueue[0] = Screen.DeterminePiece(piecePos[1][0], piecePos[1][1], queuePieceColors);
			for (int x = 0; x < depth-1; x++) {
				piece = Screen.DeterminePiece(piecePos[x+2][0], piecePos[x+2][1], queuePieceColors);
				
				queue[x+1] = piece;
				holdQueue[x+1] = piece;
			}

			//board.refresh();
			Delay(delay);
			
			if (queue[0] == -1) {
				Screen.FailSafe();
				System.out.println("Emergency hold");
				failCount++;
				if (failCount >= 5) {
					System.out.println("FAILSAFE ACTIVATED");
					System.exit(0);
				}
			}
			else {
				path = Decision.FindBestPlacement(queue, board).GetMovementAndScore();
				queuePath = Decision.FindBestPlacement(holdQueue, board).GetMovementAndScore();
				
				if (path[2] >= queuePath[2]) {
					Screen.OutputMovement(path, movementDelay, false);
				}
				else {
					Screen.OutputMovement(queuePath, movementDelay, true);
				}
			}
			Delay(10); //allow time for the screen itself to refresh
		}
		
	}
	
	private static void configureForJstris() {
		topLeft[0] = 924;
		topLeft[1] = 216;
		slope[0] = 24;
		slope[1] = 24;
		piecePos[0][0] = 1018;
		piecePos[0][1] = 217;
		piecePos[1][0] = 842;
		piecePos[1][1] = 266;
		gamePos[0] = 1000;
		gamePos[1] = 400;
		
		for (int x = 2; x < piecePos.length; x++) {
			piecePos[x][0] = 1219;
			piecePos[x][1] = 266 + (72*(x-2));
		}
		
		pieceColors[0] = 159;
		pieceColors[1] = 155;
		pieceColors[2] = 177;
		pieceColors[3] = 15;
		pieceColors[4] = 91;
		pieceColors[5] = 65;
		pieceColors[6] = 41;
		
		queuePieceColors[0] = 159;
		queuePieceColors[1] = 0;
		queuePieceColors[2] = 177;
		queuePieceColors[3] = 15;
		queuePieceColors[4] = 91;
		queuePieceColors[5] = 65;
		queuePieceColors[6] = 41;
	}
	
	private static void configureForJstrisOnSchoolComputer() {
		topLeft[0] = 605;
		topLeft[1] = 216;
		slope[0] = 24;
		slope[1] = 24;
		piecePos[0][0] = 700;
		piecePos[0][1] = 212;
		piecePos[1][0] = 521;
		piecePos[1][1] = 264;
		gamePos[0] = 700;
		gamePos[1] = 500;
		
		for (int x = 2; x < piecePos.length; x++) {
			piecePos[x][0] = 898;
			piecePos[x][1] = 265 + (72*(x-2));
		}
		
		pieceColors[0] = 159;
		pieceColors[1] = 155;
		pieceColors[2] = 177;
		pieceColors[3] = 15;
		pieceColors[4] = 91;
		pieceColors[5] = 65;
		pieceColors[6] = 41;
		
		queuePieceColors[0] = 159;
		queuePieceColors[1] = 0;
		queuePieceColors[2] = 177;
		queuePieceColors[3] = 15;
		queuePieceColors[4] = 91;
		queuePieceColors[5] = 65;
		queuePieceColors[6] = 41;
	}
	
	private static void Delay(int msec) {
		try {
		    Thread.sleep(msec);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	private static void printPieceName(int piece) {
		if (piece == 0) {
			System.out.println("O piece");
		}
		else if (piece == 1) {
			System.out.println("I piece");
		}
		else if (piece == 2) {
			System.out.println("S piece");
		}
		else if (piece == 3) {
			System.out.println("Z piece");
		}
		else if (piece == 4) {
			System.out.println("L piece");
		}
		else if (piece == 5) {
			System.out.println("J piece");
		}
		else if (piece == 6) {
			System.out.println("T piece");
		}
		else {
			System.out.println("that ain't a piece");
		}
	}
	
	private static void TestQueue(int[] queue, int y1) {
		Boardstate result = Decision.FindBestPlacement(queue, Board.GetBoard());
		Board.Setboard(Screen.DetermineBoardData(topLeft, slope));
		Board.EditBoard(result.GetPieceData()[0][0], result.GetPieceData()[0][1]+y1, 1);
		Board.EditBoard(result.GetPieceData()[1][0], result.GetPieceData()[1][1]+y1, 1);
		Board.EditBoard(result.GetPieceData()[2][0], result.GetPieceData()[2][1]+y1, 1);
		Board.EditBoard(result.GetPieceData()[3][0], result.GetPieceData()[3][1]+y1, 1);
	}
	
	/*private static void TestI(int y) {
		int[] queue = new int[1];
		queue[0] = 1;
		int[] finals = Decision.FindBestPlacement(queue, 1, Board.GetBoard()).GetMovementAndScore();
		
		System.out.println(finals[0]);
		System.out.println(finals[1]);
		
		if (finals[1] == 0) { //horizontal
			Board.EditBoard(3+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(5+finals[0], y, 1);
			Board.EditBoard(6+finals[0], y, 1);
		}
		else { //vertical
			Board.EditBoard(5+finals[0], y, 1);
			Board.EditBoard(5+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y+2, 1);
			Board.EditBoard(5+finals[0], y+3, 1);
		}
	}
	
	private static void TestS(int y) {
		int[] queue = new int[1];
		queue[0] = 2;
		int[] finals = Decision.FindBestPlacement(queue, 1, Board.GetBoard()).GetMovementAndScore();
		
		System.out.println(finals[0]);
		System.out.println(finals[1]);
		
		if (finals[1] == 0) { //S
			Board.EditBoard(3+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y+1, 1);
		}
		else { //sideways S
			Board.EditBoard(4+finals[0], y+2, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y, 1);
		}
	}
	
	private static void TestZ(int y) {
		int[] queue = new int[1];
		queue[0] = 3;
		int[] finals = Decision.FindBestPlacement(queue, 1, Board.GetBoard()).GetMovementAndScore();
		
		System.out.println(finals[0]);
		System.out.println(finals[1]);
		
		if (finals[1] == 0) { //Z
			Board.EditBoard(3+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(5+finals[0], y, 1);
		}
		else { //sideways Z
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y+2, 1);
		}
	}
	
	private static void TestL(int y) {
		int[] queue = new int[1];
		queue[0] = 4;
		int[] finals = Decision.FindBestPlacement(queue, 1, Board.GetBoard()).GetMovementAndScore();
		
		System.out.println(finals[0]);
		System.out.println(finals[1]);
		
		if (finals[1] == 0) { //L pointing left
			Board.EditBoard(3+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(5+finals[0], y, 1);
			Board.EditBoard(5+finals[0], y+1, 1);
		}
		else if (finals[1] == 1) { //L
			Board.EditBoard(4+finals[0], y+2, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(5+finals[0], y, 1);
		}
		else if (finals[1] == 2) { //L pointing right
			Board.EditBoard(3+finals[0], y, 1);
			Board.EditBoard(3+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y+1, 1);
		}
		else { //L pointing down
			Board.EditBoard(3+finals[0], y+2, 1);
			Board.EditBoard(4+finals[0], y+2, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y, 1);
		}
	}
	
	private static void TestJ(int y) {
		int[] queue = new int[1];
		queue[0] = 5;
		int[] finals = Decision.FindBestPlacement(queue, 1, Board.GetBoard()).GetMovementAndScore();
		
		System.out.println(finals[0]);
		System.out.println(finals[1]);
		
		if (finals[1] == 0) { //J pointing right
			Board.EditBoard(3+finals[0], y+1, 1);
			Board.EditBoard(3+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(5+finals[0], y, 1);
		}
		else if (finals[1] == 1) { //J pointing down
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y+2, 1);
			Board.EditBoard(5+finals[0], y+2, 1);
		}
		else if (finals[1] == 2) { //J pointing left
			Board.EditBoard(3+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y, 1);
		}
		else { //J
			Board.EditBoard(3+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y+2, 1);
		}
	}
	
	private static void TestT(int y) {
		int[] queue = new int[1];
		queue[0] = 6;
		int[] finals = Decision.FindBestPlacement(queue, 1, Board.GetBoard()).GetMovementAndScore();
		
		System.out.println(finals[0]);
		System.out.println(finals[1]);
		
		if (finals[1] == 0) { //T pointing down
			Board.EditBoard(3+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(5+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
		}
		else if (finals[1] == 1) { //T pointing left
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y+2, 1);
			Board.EditBoard(5+finals[0], y+1, 1);
		}
		else if (finals[1] == 2) { //T
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(3+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(5+finals[0], y+1, 1);
		}
		else { //T pointing right
			Board.EditBoard(3+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y, 1);
			Board.EditBoard(4+finals[0], y+1, 1);
			Board.EditBoard(4+finals[0], y+2, 1);
		}
	}*/
}