public class Main {
	
	//Parameters
	private static int depth = 1;
	private static int poolSize = 1;
	
	private static int delay = 1000;
	private static int movementDelay = 10;
	
	private static int[] queue = new int[depth];
	private static int[] topLeft = new int[2];
	private static int[] slope = new int[2];
	private static int[][] piecePos = new int[depth][2];
	private static int[] gamePos = new int[2];
	private static int[] pieceColors = new int[7];
	private static int[] queuePieceColors = new int[7];
	
	public static void main (String[] args) {
		configureForJstrisOnSchoolComputer();
		
		int[][] board = new int[10][22];
		
		/*System.out.println("test");
		Board.Init();
		Board.Setboard(Screen.DetermineBoardData(topLeft, slope));
		TestT(15);
		Board.Refresh();
		Delay(10000000);*/
		
		/*boolean lmao = true;
		Board.Init();
		while (lmao) {
			Board.Setboard(Screen.DetermineBoardData(topLeft, slope));
			Board.Refresh();
			Delay(10);
		}*/

		Screen.SetBoardDimensions(10, 22);
		Screen.FocusGame(gamePos, 20);
		Screen.SetDelay(movementDelay);
		
		int failCount = 0;
		
		while (true) {
			board = Screen.DetermineBoardData(topLeft, slope);
			
			for (int x = 0; x < queue.length; x++) {
				queue[x] = Screen.DeterminePiece(piecePos[x][0], piecePos[x][1], pieceColors);
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
				Screen.OutputMovement(Decision.FindBestPlacement(queue, poolSize, board), movementDelay, false);
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
		gamePos[0] = 1000;
		gamePos[1] = 400;
		
		for (int x = 1; x < piecePos.length; x++) {
			piecePos[x][0] = 0;
			piecePos[x][1] = 0;
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
		topLeft[0] = 602;
		topLeft[1] = 184;
		slope[0] = 24;
		slope[1] = 24;
		piecePos[0][0] = 697;
		piecePos[0][1] = 185;
		gamePos[0] = 800;
		gamePos[1] = 400;
		
		for (int x = 1; x < piecePos.length; x++) {
			piecePos[x][0] = 0;
			piecePos[x][1] = 0;
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
	
	private static void TestT(int y) {
		int[] queue = new int[1];
		queue[0] = 6;
		int[] finals = Decision.FindBestPlacement(queue, 1, Board.GetBoard());
		
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
	}
	
	/*private static void TestI(int y) {
		int[] queue = new int[1];
		queue[0] = 1;
		short[] finals = Decision.FindBestPlacement(queue, 0, Board.GetBoard());
		
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
	}*/
}