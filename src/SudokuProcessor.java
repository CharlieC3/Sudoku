import java.io.IOException;
import java.util.Iterator;

/**
 * This class attempts to solve the puzzle using recursion.
 * @author Charlie
 *
 */
public class SudokuProcessor {
	private SudokuGrid sudokuGrid;
	int iterator = 0;
	
	/**
	 * Grid to use is passed in when the processor object is created.
	 * @param sudokuGrid
	 */
	public SudokuProcessor (SudokuGrid sudokuGrid)	{
		this.sudokuGrid = sudokuGrid;
	}
	
	/**
	 * Method used to call the recursive solver method.
	 * @throws IndexOutOfBoundsException
	 * @throws IOException
	 */
	public void solvePuzzle() throws IndexOutOfBoundsException, IOException {
		
		if (solveWithRecursion(0, 0))	{
			sudokuGrid.setSolved(true);
		} else	{
			System.out.println("Puzzle not solvable.");
			System.exit(0);
		}
	}
	
	/**
	 * This method solves the puzzle row by row, from (0, 0) to (8, 8) using recursion.
	 * It finds a zero Node and tries to inject the first valid value into the Node.
	 * It will then attempt to resolve the rest of the puzzle by following the same process.
	 * If it is not solvable, it goes back to the previous zero Node filled in and tries a
	 * different value. This can continue to fetter back to the first zero Node filled in
	 * if the initial value chosen prevents the app from solving the puzzle.
	 * @throws IOException
	 */
	private boolean solveWithRecursion(int x, int y) throws IOException {
		int valueOfCurrentNode;

		if (x == 9) {			//If the end of the grid has been reached
			x = 0;				//reset x and increment y
			if (++y == 9) {
				return true;
			}
		}

		valueOfCurrentNode = sudokuGrid.getNode(x, y).getValue(); //Get value of currently selected Node

		if (valueOfCurrentNode != 0) { 				//If a node with a pre-filled value of 0 is found,
			return solveWithRecursion(x + 1, y);	//System.out.println("x = " + x + "y = " + y);
			 										//find out what value can be injected and inject it
		}
		
		for (int valueToInject = 1; valueToInject <= 9; ++valueToInject) {
			//System.out.println("Iterations = " + ++iterator);

			if (checkValueToInject(x, y, valueToInject)) { 			//If the valueToBeInjected is valid
				sudokuGrid.getNode(x, y).setValue(valueToInject); 	//inject it into the Node
				if (solveWithRecursion(x + 1, y)) {					//Then recursively call this method
					return true;									//with the next x value
				}
			}
		} //End of For loop
		sudokuGrid.getNode(x, y).setValue(0);
		return false;
	}
	
	/**
	 * Checks the passed in value by checking the row, column then sector.
	 * If the value fails validation at any point, further validations are
	 * not called for the value at (x, y) to save time.
	 * @param x
	 * @param y
	 * @param valueToCheck
	 * @return
	 */
	private boolean checkValueToInject(int x, int y, int valueToCheck) {
		if (checkRow(valueToCheck, x, y) != 0) { 			//Checks the row for matching values. If a matching value
															//is found, further methods are not called with this value
															//to save cycles
			if (checkColumn(valueToCheck, x, y) != 0) { 	//Checks the column for matching values. If a matching value
															//is found, the sector is not checked with this value
															//to save cycles
				if (checkSector(valueToCheck, x, y) != 0) {
					return true; 					//Return valueToCheck to be injected
				}
			}
		}
		return false;
	}

	/**
	 * Check the row for the passed in valueToCheck
	 * @param valueToCheck
	 * @param X
	 * @param Y
	 * @return
	 */
	private int checkRow(int valueToCheck, int X, int Y) {
		for (int x = 0; x < 9; x++) {
			if (sudokuGrid.getNode(x, Y).getValue() == valueToCheck) {
				return 0;		//Return 0 if the value already exists in the row
			}
		}
		return valueToCheck; 	// No match was found, meaning the valueToCheck is
								// a possible candidate to be injected
	}
	
	/**
	 * Check the column for the passed in valueToCheck
	 * @param valueToCheck
	 * @param X
	 * @param Y
	 * @return
	 */
	private int checkColumn(int valueToCheck, int X, int Y) {
		// Check the column for the passed in valueToCheck
		for (int y = 0; y < 9; y++) {
			if (sudokuGrid.getNode(X, y).getValue() == valueToCheck) {
				return 0;		//Return 0 if the value already exists in the column
			}
		}
		return valueToCheck; 	// No match was found, meaning the valueToCheck is
								// a possible candidate to be injected

	}
	
	/**
	 * Check the sector for the passed in valueToCheck
	 * @param valueToCheck
	 * @param X
	 * @param Y
	 * @return
	 * @throws IndexOutOfBoundsException 
	 */
	private int checkSector(int valueToCheck, int X, int Y) throws IndexOutOfBoundsException {
		Iterator<SudokuVertexNode> sectorToCheck = null;
		
		switch (sudokuGrid.getNode(X, Y).getSector())	{
		case 1:		sectorToCheck = sudokuGrid.getSector(1).iterator();
					break;
		case 2:		sectorToCheck = sudokuGrid.getSector(2).iterator();
					break;
		case 3:		sectorToCheck = sudokuGrid.getSector(3).iterator();
					break;
		case 4:		sectorToCheck = sudokuGrid.getSector(4).iterator();
					break;
		case 5:		sectorToCheck = sudokuGrid.getSector(5).iterator();
					break;
		case 6:		sectorToCheck = sudokuGrid.getSector(6).iterator();
					break;
		case 7:		sectorToCheck = sudokuGrid.getSector(7).iterator();
					break;
		case 8:		sectorToCheck = sudokuGrid.getSector(8).iterator();
					break;
		case 9:		sectorToCheck = sudokuGrid.getSector(9).iterator();
					break;
		default:	throw new IndexOutOfBoundsException("Error: Could not find specified index of " + sectorToCheck);
		}
		
		//Check all nodes in the sector
		while (sectorToCheck.hasNext())	{
			SudokuVertexNode nodeToCheck = sectorToCheck.next();
			if (nodeToCheck.getValue() == valueToCheck)	{
				return 0;		//Return 0 if the value already exists in the sector
			}
		}
		
		return valueToCheck;	// No match was found, meaning the valueToCheck is
								// confirmed to be injected
	}
}
