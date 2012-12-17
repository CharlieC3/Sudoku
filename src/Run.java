/**
 * 
 * @author Rachael, Charlie
 *
 */
public class Run {
	
	/**
	 * Creates a 9x9 grid, pre-populates the nodes with data, then solves the puzzle.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SudokuGrid sudokuGrid = new SudokuGrid();		// Create grid and pre-populate it
		SudokuProcessor processor = new SudokuProcessor(sudokuGrid);
		
		sudokuGrid.printGrid();		
		processor.solvePuzzle();		
		sudokuGrid.printGrid();
		
		System.exit(0);
	}
}