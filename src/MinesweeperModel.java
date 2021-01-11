import java.util.Random;

public class MinesweeperModel implements MSModelInterface{
	
	Tile[][] board;
	boolean isGameOver;
	int numFlags;

	public MinesweeperModel(int rows, int cols) {
		// TODO Auto-generated constructor stub
		isGameOver = false;
		createBoard(10, 10);
	}

	public Tile[][] createBoard(int rows, int cols) {
		// TODO Auto-generated method stub
		board = new Tile[rows][cols];
		int i = 0;
		while (i < rows) {
			int j = 0;
			while (j < cols) {
				board[i][j] = new Tile();
				j++;
			}
			i++;
		}
		return board;
	}
	
	@Override
	public int getTileValue(int row, int col) {
		Tile t = board[row][col];
		return t.getValue();
	}
	
	@Override
	public char getTileHiddenValue(int row, int col) {
		Tile t = board[row][col];
		return t.getHiddenValue();
	}
	
	@Override
	public boolean isMine(int row, int col) {
		// TODO Auto-generated method stub
		Tile t = board[row][col];
		return t.isMine();
	}

	@Override
	public boolean isInBounds(int row, int col) {
		// TODO Auto-generated method stub
		if (row >= 0 && row < board[0].length) {
			if (col >= 0 && col < board.length) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean isFlagged(int row, int col) {
		// TODO Auto-generated method stub
		Tile t = board[row][col];
		if (t.getHiddenValue() == '!') {
			return true;
		}
		return false;
	}
	
	@Override
	public void setFlag(int row, int col) {
		// TODO Auto-generated method stub
		Tile t = board[row][col];
		t.setHiddenValue('!');
		numFlags--;
		
	}
	
	@Override
	public void removeFlag(int row, int col) {
		// TODO Auto-generated method stub
		Tile t = board[row][col];
		t.setHiddenValue('_');
	}

	@Override
	public void placeMines(int numMines, int row, int col) {
		// TODO Auto-generated method stub
		numFlags = numMines;
		while (numMines > 0) {
			Random rand = new Random();
			int row1 = rand.nextInt(board.length);
			int col1 = rand.nextInt(board[0].length);
			if (row != row1 || col != col1) {
				if (!isMine(row1, col1)) {
					board[row1][col1].value = -1;
					board[row1][col1].isMine = true;
					numMines--;
				}
			}
		}
		int i = 0;
		while (i < board.length) {
			int j = 0;
			while (j < board[0].length) {
				if (!board[i][j].isMine()) {
					board[i][j].setValue(getNumNeighbors(i, j));
				}
				j++;
			}
			i++;
		}
	}
	
	@Override
	public int getNumNeighbors(int row, int col) {
		int numMineNeighbors = 0;
		if (isInBounds(row-1, col-1)) {
			if (board[row-1][col-1].isMine()) {
				numMineNeighbors++;
			}
		}
		if (isInBounds(row-1, col)) {
			if (board[row-1][col].isMine()) {
				numMineNeighbors++;
			}
		}
		if (isInBounds(row-1, col+1)) {
			if (board[row-1][col+1].isMine()) {
				numMineNeighbors++;
			}
		}
		if (isInBounds(row, col-1)) {
			if (board[row][col-1].isMine()) {
				numMineNeighbors++;
			}
		}
		if (isInBounds(row, col+1)) {
			if (board[row][col+1].isMine()) {
				numMineNeighbors++;
			}
		}
		if (isInBounds(row+1, col-1)) {
			if (board[row+1][col-1].isMine()) {
				numMineNeighbors++;
			}
		}
		if (isInBounds(row+1, col)) {
			if (board[row+1][col].isMine()) {
				numMineNeighbors++;
			}
		}
		if (isInBounds(row+1, col+1)) {
			if (board[row+1][col+1].isMine()) {
				numMineNeighbors++;
			}
		}
		return numMineNeighbors;
	}

	@Override
	public void reveal(int row, int col) {
		// TODO Auto-generated method stub
		Tile t = board[row][col];
		if (!t.isHidden()) {
			// do nothing
		}
		else {
				t.setHidden(false);
				if (t.getValue() == 0) {
					if (isInBounds(row-1, col-1)) {
						if (!board[row-1][col-1].isMine()) {
							reveal(row-1, col-1);
						}
					}
					if (isInBounds(row-1, col)) {
						if (!board[row-1][col].isMine()) {
							reveal(row-1, col);
						}
					}
					if (isInBounds(row-1, col+1)) {
						if (!board[row-1][col+1].isMine()) {
							reveal(row-1, col+1);
						}
					}
					if (isInBounds(row, col-1)) {
						if (!board[row][col-1].isMine()) {
							reveal(row, col-1);
						}
					}
					if (isInBounds(row, col+1)) {
						if (!board[row][col+1].isMine()) {
							reveal(row, col+1);
						}
					}
					if (isInBounds(row+1, col-1)) {
						if (!board[row+1][col-1].isMine()) {
							reveal(row+1, col-1);
						}
					}
					if (isInBounds(row+1, col)) {
						if (!board[row+1][col].isMine()) {
							reveal(row+1, col);
						}
					}
					if (isInBounds(row+1, col+1)) {
						if (!board[row+1][col+1].isMine()) {
							reveal(row+1, col+1);
						}
					}
			}
		}
	}

	@Override
	public boolean isRevealed(int row, int col) {
		Tile t = board[row][col];
		return !t.isHidden();
	}
	
	@Override
	public boolean isGameWon() {
		boolean correct = true;
		int i = 0;
		while (i < board.length) {
			int j = 0;
			while (j < board[0].length) {
				if (board[i][j].isMine()) {
					if (board[i][j].getHiddenValue() == '!') {
						correct = true;
					}
					else {
						correct = false;
					}
				}
				j++;
			}
			i++;
		}
		return correct;
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	@Override
	public void setDifficulty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}

class Tile {
	public boolean isHidden;
	public char hiddenValue;
	public int value;
	public boolean isMine;
	
	public Tile() {
		isHidden = true;
		hiddenValue = '_';
		value = 0;
		isMine = false;
	}
	
	public boolean isHidden() {
		return isHidden;
	}
	
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
	
	public char getHiddenValue() {
		return hiddenValue;
	}

	public void setHiddenValue(char hiddenValue) {
		this.hiddenValue = hiddenValue;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isMine() {
		if (getValue() == -1) {
			return true;
		}
		else {
			return false;
		}
	}
}
