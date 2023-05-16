public class Main {
	public static void main (String[] args) {
		System.out.println("test");

		Board.Init();
		Board.RandomizeFloor(5);
		TestT(6);
		
		
	}
	
	private static void TestT(int y) {
		byte[] queue = new byte[1];
		queue[0] = 6;
		short[] finals = Decision.FindBestPlacement(queue, Board.GetBoard());
		
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
}