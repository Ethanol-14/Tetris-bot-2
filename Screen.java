import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class Screen {
	
	private static Robot computer;

	private static int boardWidth = 10;
	private static int boardHeight = 21;
	
	private static int inBetweenDelay = 5;
	
	public static void SetDelay(int delay) {
		inBetweenDelay = delay;
	}
	
	public static void FocusGame(int[] gamePos, int delayMsec) {
		
		try {
			computer = new Robot();
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
		
		computer.mouseMove(gamePos[0], gamePos[1]);
		Delay(inBetweenDelay);
		computer.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Delay(inBetweenDelay);
		computer.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Delay(inBetweenDelay);
		computer.mouseMove(0, 0);
	}
	
	public static void FailSafe() {
		computer.keyPress(KeyEvent.VK_SHIFT);
		Delay(inBetweenDelay);
		computer.keyRelease(KeyEvent.VK_SHIFT);
	}

	public static void SetBoardDimensions(int width, int height) {
		boardWidth = width;
		boardHeight = height;
	}
	
	public static void OutputMovement(int[] movement, int delayMsec, boolean useSecondary) {
				
		//System.out.println("Displacement: "+movement[0]);
		//System.out.println("Spin: "+movement[1]);
		
		if (useSecondary) {
			computer.keyPress(KeyEvent.VK_SHIFT);
			Delay(inBetweenDelay);
			computer.keyRelease(KeyEvent.VK_SHIFT);
		}
		
		//spin 0 requires no key presses
		if (movement[1] == 1) { //spin 1 (90 deg to the right)
			computer.keyPress(KeyEvent.VK_D);
			Delay(inBetweenDelay);
			computer.keyRelease(KeyEvent.VK_D);
			Delay(delayMsec);
		}
		else if (movement[1] == 2) { //spin 2 (180 deg)
			computer.keyPress(KeyEvent.VK_S);
			Delay(inBetweenDelay);
			computer.keyRelease(KeyEvent.VK_S);
			Delay(delayMsec);
		}
		else if (movement[1] == 3) { //spin 3 (90 deg to the left)
			computer.keyPress(KeyEvent.VK_A);
			Delay(inBetweenDelay);
			computer.keyRelease(KeyEvent.VK_A);
			Delay(delayMsec);
		}
		
		//displacement 0 requires no key presses
		if (movement[0] < 0) { //to the left
			for (int x = 0; x < movement[0]*-1; x++) {
				computer.keyPress(KeyEvent.VK_LEFT);
				Delay(inBetweenDelay);
				computer.keyRelease(KeyEvent.VK_LEFT);
				Delay(delayMsec);
			}
		}
		else { //to the right
			for (int x = 0; x < movement[0]; x++) {
				computer.keyPress(KeyEvent.VK_RIGHT);
				Delay(inBetweenDelay);
				computer.keyRelease(KeyEvent.VK_RIGHT);
				Delay(delayMsec);
			}
		}
		
		//take it back now y'all
		computer.keyPress(KeyEvent.VK_SPACE);
		Delay(inBetweenDelay);
		computer.keyRelease(KeyEvent.VK_SPACE);
	}
	
	public static int[][] DetermineBoardData(int[] topLeft, int[] slope) {
		//long startTime = System.currentTimeMillis();
		
		int[][] board = new int[boardWidth][boardHeight];
		
		try {
			computer = new Robot();
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
		
		Rectangle searchArea = new Rectangle(topLeft[0], topLeft[1], slope[0]*10, slope[1]*20);
		BufferedImage screenshot = computer.createScreenCapture(searchArea);
		
		for (int x = 0; x < boardWidth; x++) {
			for (int y = 1; y < 20; y++) {
				if (screenshot.getRGB(x*slope[0], y*slope[1]) == -16777216) {
					board[x][20-y-1] = 0;
				}
				else {
					board[x][20-y-1] = 1;
				}
			}
		}
		
		//long timeTaken = System.currentTimeMillis()-startTime;
		//System.out.println("Time taken: "+timeTaken+" msec");
		
		return board;
	}
	
	public static int DeterminePiece(int x, int y, int[] pieceColors) {
		Color pixel = new Color(0);
		
		int piece = -1;
		
		try {
			computer = new Robot();
			pixel = computer.getPixelColor(x, y);
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
		
		int colorG = pixel.getGreen();
		
		for (int n = 0; n < 7; n++) {
			if (colorG == pieceColors[n]) {
				piece = n;
			}
		}
		
		return piece;
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