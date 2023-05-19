public class Main {
	
	//Parameters
	private static int depth = 1;
	private static int delay = 500;
	private static int movementDelay = 5;
	
	private static int[] queue = new int[depth];
	private static int[] topLeft = {604, 217};
	private static int[] slope = {24, 24};
	private static int[][] piecePos = new int[depth][2];
	private static int[] gamePos = {700, 400};
	private static int[] pieceColors = {155, 65, 91, 159, 177, 15, 41};
	
	public static void main (String[] args) {
		configureForJstris();
		Board.SetBoardDimensions(10, 25);
		
		/*System.out.println("test");
		Board.Init();
		
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 4; y++) {
				Board.EditBoard(x, y, 1);
			}
		}
		for (int x = 0; x < 0; x++) {
			for (int y = 0; y < 4; y++) {
				Board.EditBoard(x, y, 1);
			}
		}
		TestI(6);
		Board.Refresh();
		
		Delay(10000000);*/
		
		/*boolean lmao = true;
		while (lmao) {
			Board.Setboard(Screen.DetermineBoardData(topLeft, slope));
			Board.Refresh();
			Delay(10);
		}*/

		Screen.SetBoardDimensions(10, 25);
		Screen.FocusGame(gamePos, 20);
		Screen.SetDelay(movementDelay);
		
		int failCount = 0;
		
		while (true) {
			Board.Setboard(Screen.DetermineBoardData(topLeft, slope));
			
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
				Screen.OutputMovement(Decision.FindBestPlacement(queue, 1, Board.GetBoard()), movementDelay, false);
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
		
		for (int x = 1; x < piecePos.length; x++) {
			piecePos[x][0] = 0;
			piecePos[x][1] = 0;
		}
		
		gamePos[0] = 1000;
		gamePos[1] = 400;
		pieceColors[0] = 159;
		pieceColors[1] = 155;
		pieceColors[2] = 177;
		pieceColors[3] = 15;
		pieceColors[4] = 91;
		pieceColors[5] = 65;
		pieceColors[6] = 41;
	}
	
	private static void configureForJstrisOnSchoolComputer() {
		topLeft[0] = 602;
		topLeft[1] = 184;
		slope[0] = 24;
		slope[1] = 24;
		piecePos[0][0] = 697;
		piecePos[0][1] = 185;
		
		for (int x = 1; x < piecePos.length; x++) {
			piecePos[x][0] = 0;
			piecePos[x][1] = 0;
		}
		
		gamePos[0] = 800;
		gamePos[1] = 400;
		pieceColors[0] = 159;
		pieceColors[1] = 155;
		pieceColors[2] = 177;
		pieceColors[3] = 15;
		pieceColors[4] = 91;
		pieceColors[5] = 65;
		pieceColors[6] = 41;
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
		int[] finals = Decision.FindBestPlacement(queue, 0, Board.GetBoard());
		
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
	
	private static void TestI(int y) {
		int[] queue = new int[1];
		queue[0] = 1;
		int[] finals = Decision.FindBestPlacement(queue, 0, Board.GetBoard());
		
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
}