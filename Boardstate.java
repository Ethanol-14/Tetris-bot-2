public class Boardstate {
	
	private int piece = 0;
	private int[][] pieceData = new int[4][2];
	private int[] movementAndScore = new int[3];
	private int[][] board = new int[10][21];
	private boolean active = true;
	
	//object declaration
	public Boardstate() {}
	
	//other object declaration
	/*public Boardstate(Boardstate field) {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 2; y++) {
				pieceData[x][y] = field.GetPieceData(x, y);
			}
		}
		for (int i = 0; i < 3; i++) {
			movementAndScore[i] = field.GetMovementAndScore(i);
		}
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				board[x][y] = (byte) field.GetBoard(x, y);
			}
		}
	}*/
	
	public int GetPiece() {
		return piece;
	}
	
	public int[][] GetPieceData() {
		return pieceData;
	}
	
	public int GetPieceData(int x, int y) {
		return pieceData[x][y];
	}
	
	public int GetScore() {
		return movementAndScore[2];
	}
	
	public int[][] GetBoard() {
		return board;
	}
	
	public int GetBoard(int x, int y) {
		return board[x][y];
	}
	
	public int[] GetMovementAndScore() {
		return movementAndScore;
	}
	
	public int GetMovementAndScore(int i) {
		return movementAndScore[i];
	}
	
	public boolean IsActive() {
		return active;
	}
	
	public void SetPiece(int _piece) {
		piece = _piece;
	}
	
	public void SetPieceData(int[][] _pieceData) {
		pieceData = _pieceData;
	}
	
	public void SetPieceData(int x, int y, int value) {
		pieceData[x][y] = value;
	}
	
	public void SetMovement(int _displacement, int _rotation) {
		movementAndScore[0] = _displacement;
		movementAndScore[1] = _rotation;
	}
	
	public void SetScore(int _score) {
		movementAndScore[2] = _score;
	}
	
	public void ChangeScore(int _changeInScore) {
		movementAndScore[2] += _changeInScore;
	}
	
	public void SetBoard(int[][] _board) {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				board[x][y] = _board[x][y];
			}
		}
	}
	
	public void SetBoard(int x, int y, int value) {
		board[x][y] = value;
	}
	
	public void SetBoardAndScore(Boardstate _field) {
		movementAndScore[2] = _field.GetScore();
		board = _field.GetBoard();
	}
	
	public void SetActivity(boolean activity) {
		active = activity;
	}
}