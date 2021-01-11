import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

public class MinesweeperController extends Application{
	
	private MinesweeperModel model;
	private Scanner scan;
	int numMines;
	int steps;
	int rows;
	int cols;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		model = new MinesweeperModel(rows, cols);
		steps = 0;
		MinesweeperView view = new MinesweeperView();
	}

	public MinesweeperController(int rows, int cols, int numMines) {
		// TODO Auto-generated constructor stub
		this.numMines = numMines;
		steps = 0;
		this.rows = rows;
		this.cols = cols;
	}

	public void play() {
		scan = new Scanner(System.in);
		steps = 0;
		while (!model.isGameOver()) {
			printBoard();
			System.out.println("Would you like to flag (f) or reveal (r)? --> ");
			if (scan.next().equalsIgnoreCase("f")) {
				System.out.println("Enter a row number: --> ");
				int row = scan.nextInt();
				System.out.println("Enter a column number: --> ");
				int col = scan.nextInt();
				if (model.isInBounds(row, col)) {
					if (model.isRevealed(row, col)) {
						System.out.println("You can't place a flag here.");
					}
					else {
						if (model.isFlagged(row, col)) {
							model.removeFlag(row, col);
						}
						else {
							model.setFlag(row, col);
						}
					}
				}
				else {
					System.out.println("That isn't in bounds!");
				}
			}
			else if (scan.next().equalsIgnoreCase("r")) {
				System.out.println("Enter a row number: --> ");
				int row = scan.nextInt();
				System.out.println("Enter a column number: --> ");
				int col = scan.nextInt();
				if (model.isInBounds(row, col)) {
					if (model.isRevealed(row, col)) {
						System.out.println("That tile has already been revealed!");
					}
					else {
						steps++;
						if (steps == 1) {
							model.placeMines(numMines, row, col);
						}
						model.reveal(row, col);
						if (model.isMine(row, col)) {
							model.setGameOver(true);
						}
					}
				}
				else {
					System.out.println("That isn't in bounds!");
				}
			}
			else {
				System.out.println("Please enter a valid command.");
			}
			if (steps > 1 && model.isGameWon()) {
				return;
			}
		}
		if (model.isGameWon()) {
			System.out.println("Congrats! You won!");
		}
		else {
			System.out.println("You lost. Try again!");
		}
	}
	
	public void printBoard() {
		System.out.print("   ");
		for (int i = 0; i < 10; i++) {
			System.out.printf("%d ", i);
		}
		for (int i = 10; i < rows; i++) {
			System.out.printf("%d", i%10);
		}
		System.out.println();
		for (int i = 0; i < rows; i++) {
			System.out.printf("%2d ",i);
			for (int j = 0; j < cols; j++) {
				if (model.isRevealed(i, j)) {
					if (model.getTileValue(i, j) == 0) {
						System.out.print("  ");
					}
					else {
						System.out.print(model.getTileValue(i, j) + " ");
					}
				}
				else {
					System.out.print(model.getTileHiddenValue(i, j) + " ");
				}
			}
			System.out.println();
		}
	}

}