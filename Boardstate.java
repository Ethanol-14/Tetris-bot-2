public class Boardstate {
	
	private int piece = 0;
	private int[][] pieceData = new int[4][2];
	private int[] movementAndScore = new int[3];
	private int[][] board = new int[10][21];
	
	//object declaration
	public Boardstate() {}
	
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
	
	public void ChangeScore(int _changeInScore) {
		movementAndScore[2] += _changeInScore;
	}
	
	public void SetBoard(int[][] _board) {
		board = _board;
	}
	
	public void SetBoard(int x, int y, int value) {
		board[x][y] = value;
	}
	
	public void SetBoardAndScore(Boardstate _field) {
		movementAndScore[2] = _field.GetScore();
		board = _field.GetBoard();
	}
}