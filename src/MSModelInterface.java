
public interface MSModelInterface {
	int getTileValue(int row, int col);
	char getTileHiddenValue(int row, int col);
	boolean isMine(int row, int col);
	boolean isInBounds(int row, int col);
	void setFlag(int row, int col);
	boolean isFlagged(int row, int col);
	void placeMines(int numMines, int row, int col);
	int getNumNeighbors(int row, int col);
	void reveal(int row, int col); // RECURSUVE
	boolean isRevealed(int row, int col);
	void removeFlag(int row, int col);
	boolean isGameWon();
	void setDifficulty();
	void reset();
}
