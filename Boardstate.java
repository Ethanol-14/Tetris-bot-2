public class Boardstate {
	
	private int piece = 0;
	private int[] movement = new int[3];
	private int score = 0;
	private int[][] board = new int[10][25];
	
	//object declaration
	public Boardstate() {}
	
	public int GetPiece() {
		return piece;
	}
	
	public int[] GetMovement() {
		return movement;
	}
	
	public int GetCost() {
		return score;
	}
	
	public int[][] GetBoard() {
		return board;
	}
	
	public int GetBoard(int x, int y) {
		return board[x][y];
	}
	
	public void SetPiece(int _piece) {
		piece = _piece;
	}
	
	public void SetMovement(int _displacement, int _rotation) {
		movement[0] = _displacement;
		movement[1] = _rotation;
	}
	
	public void ChangeScore(int _changeInScore) {
		score += _changeInScore; 
	}
	
	public void SetBoard(int[][] _board) {
		board = _board;
	}
	
	public void SetBoard(int x, int y, int value) {
		board[x][y] = value;
	}
}
