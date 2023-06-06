import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Random;

public class Board extends JPanel { //this class is really just for basic board visualization

	private static final long serialVersionUID = -7326512691602942406L;
	
	private static JFrame frame = new JFrame("Block solution");
	private static JPanel panel = new Board();
	
	private static int[][] board = new int[10][22];
	
	public static int[][] GetBoard() {
		return board;
	}
	
	public static void EditBoard(int x, int y, int value) {
		board[x][y] = value;
	}
	
	public static void SetBoardDimensions(int width, int height) {
		board = new int[width][height];
	}
	
	public static void Init() {		
		panel.setBackground(Color.black);
		frame.setSize(400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = 0;
			}
		}
	}
		
	public static void RandomizeFloor(int layers) {
		//create random number generating object
		Random randomInt = new Random();
		
		//randomize board's first layer of squares
		for (int j = 0; j < layers; j++) {
			for (int i = 0; i < board.length; i++) {
				board[i][j] = randomInt.nextInt(2);
			}
		}
	}
	
	public static void Setboard(int[][] _board) {
		board = _board;
	}
	
	public static void Refresh() {
		frame.repaint();
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.blue);
		
		int height = getHeight();
		int width = getWidth();
		
		g.fillRect(width/10, height/10, width-(width/5),  height-(height/5));
		
		g.setColor(Color.white);
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				if (board[x][y] == 1) {
					g.fillRect((((5*width)+(4*x*width))/50)+2, (((43*height)-(2*y*height))/50)+2, ((2*width)/25)-4, (height/25)-4);
				}
			}
		}
	}
}