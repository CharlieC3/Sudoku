import java.io.IOException;

/**
 * 
 * @author Rachael, Charlie
 *
 */
public class Run {
	
	/**
	 * Creates a 9x9 grid, pre-populates the nodes with data, then solves the puzzle.
	 * @param args
	 * @throws IndexOutOfBoundsException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IndexOutOfBoundsException, IOException {
		SudokuGrid sudokuGrid = new SudokuGrid();		// Create grid and pre-populate it
		SudokuProcessor processor = null;
		
		processor = new SudokuProcessor(sudokuGrid);
		sudokuGrid.printGrid();
		
		processor.solvePuzzle();		
		sudokuGrid.printGrid();
		
		System.exit(0);
	}
}
