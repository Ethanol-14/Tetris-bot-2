public class Main {
	
	//Parameters
	private static byte[] queue = new byte[3];
	private static int[] topLeft = {604, 217};
	private static int[] slope = {24, 24};
	private static int[] piecePos = {699, 214};
	private static short[] movement = new short[3];
	private static int[] gamePos = {700, 400};
	private static int[] pieceColors = {155, 65, 91, 159, 177, 15, 41};
	private static int[] boardDimensions = {10, 20};
	private static int delay = 500;
	private static int movementDelay = 10;
	
	public static void main (String[] args) {
		//System.out.println("test");
		//Board.Init();
		//Board.RandomizeFloor(0);
		//TestT(5);
		
		configureForJstrisOnSchoolComputer();
		Board.SetBoardDimensions(boardDimensions[0], boardDimensions[1]);

		Screen.SetBoardDimensions(boardDimensions[0], boardDimensions[1]);
		Screen.FocusGame(gamePos, 20);
		Screen.SetDelay(movementDelay);
		
		int failCount = 0;
		
		while (true) {
			Board.Setboard(Screen.DetermineBoardData(topLeft, slope));
			queue[0] = Screen.DeterminePiece(piecePos[0], piecePos[1], pieceColors);

			//board.refresh();
			Delay(delay);
			
			if (queue[0] == -1) {
				Screen.FailSafe(20);
				System.out.println("Emergency hold");
				failCount++;
				if (failCount >= 5) {
					System.out.println("FAILSAFE ACTIVATED");
					System.exit(0);
				}
			}
			else {
				movement = Decision.FindBestPlacement(queue[0], Board.GetBoard());

				Screen.OutputMovement(movement, movementDelay, false);
			}
			Delay(10); //allow time for the screen itself to refresh
		}
		
	}
	
	private static void configureForJstris() {
		topLeft[0] = 924;
		topLeft[1] = 216;
		slope[0] = 24;
		slope[1] = 24;
		piecePos[0] = 1018;
		piecePos[1] = 217;
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
		piecePos[0] = 697;
		piecePos[1] = 185;
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
		byte[] queue = new byte[1];
		queue[0] = 6;
		short[] finals = Decision.FindBestPlacement(queue[0], Board.GetBoard());
		
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
		byte[] queue = new byte[1];
		queue[0] = 1;
		short[] finals = Decision.FindBestPlacement(queue[0], Board.GetBoard());
		
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