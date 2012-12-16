import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class processes the zero Nodes in the SudokuGrid object and injects them with values
 * uses five different methods. The 1st, 2nd, 3rd and 4th methods use the Greedy method;
 * They fill in zero Nodes according to a pattern with the first valid value. However,
 * they each use a different pattern to do so. The 1st method goes row by row, the 2nd method
 * goes row by row in reverse order (from (8, 8) to (0, 0)), the 3rd goes column by column
 * and the 4th goes column by column in reverse.
 * 
 * The 4th method tries to resolve the puzzle using brute force. It collects all zero nodes
 * in an ArrayList, randomly selects a node, and injects a random number between 1 - 9.
 * After the value is injected, the node is removed from the ArrayList to prevent it from
 * getting chosen again. This continues until there are no more nodes left in the ArrayList
 * (meaning the puzzle was successfully solved), or until a certain number of iterations have
 * been reached. At that point, the grid is erased and reset with its original
 * pre-filled values. The brute force method will continue to repeat this process until the
 * puzzle is solved.
 *  
 * @author Charlie
 *
 */
public class SudokuProcessor {
	private SudokuGrid sudokuGrid;
	
	public SudokuProcessor (SudokuGrid sudokuGrid)	{
		this.sudokuGrid = sudokuGrid;
	}
	
	public void solvePuzzle() throws IndexOutOfBoundsException, IOException {
		
		//Try first method to solve puzzle
		tryRowByRow();
		
		if (this.checkForZeros())	{
			tryRowByRowReverse();		//Try first method in reverse to solve puzzle
			if (this.checkForZeros())	{
				tryColumnByColumn();	//Try solving the puzzle column by column
										//instead of row by row
				if (this.checkForZeros())	{
					tryColumnByColumnReverse();		//Try solving the puzzle column by column in reverse
					if (this.checkForZeros())	{
						tryBruteForce();	//Try brute force method to solve puzzle
					}
				}
			}
		}
		sudokuGrid.setSolved(true);
	}
	
	/**
	 * This method tries to solve the puzzle row by row, from (0, 0) to (8, 8). It searches
	 * the grid and attempt to replace all zero nodes with the first valid value. If it fails,
	 * it will try again with a different startingValue. For example, on the first run, it will
	 * attempt to inject a value between 1 - 9 into all zero Nodes starting. On the second run,
	 * it will attempt to inject 2 - 9, 1 in the zero Nodes. On the third run, it will attempt
	 * to inject 3 - 9, 1 - 2 in the zero Nodes and so forth.
	 * @throws IOException
	 */
	private void tryRowByRow() throws IOException {
		int valueOfCurrentNode, valueToBeInjected;
		boolean zeroFound = true;
		
		for (int startingValue = 1; startingValue < 10 && zeroFound; startingValue++) {
			for (int x = 0, y = 0; y < 9; y++) {	//Start looking for nodes with a value of 0 at (0, 0)
				x = 0;
				for (; x < 9; x++) {
					valueOfCurrentNode = sudokuGrid.getNode(x, y).getValue(); //Get value of currently selected Node
					
					if (valueOfCurrentNode == 0) { 			//If a node with a pre-filled value of 0 is found,
															//find out what value can be injected and inject it
						valueToBeInjected = getValueToInject(x, y, startingValue); 	
						
						if (valueToBeInjected != 0) { 						//If the valueToBeInjected is valid
							sudokuGrid.getNode(x, y).setValue(valueToBeInjected); //inject it into the Node
						}
					} //End of outer If statement
				} //End of inner For loop
			} //End of middle For loop
			zeroFound = this.checkForZeros();
			
			if (zeroFound)	{
				this.resetAllNodes();
				sudokuGrid.setTestData();
			}
		}	//End of outer For loop
		
	}
	
	/**
	 * This method tries to solve the puzzle row by row in reverse, from (8, 8) to (0, 0). It searches
	 * the grid and attempt to replace all zero nodes with the first valid value. If it fails,
	 * it will try again with a different startingValue. For example, on the first run, it will
	 * attempt to inject a value between 1 - 9 into all zero Nodes starting. On the second run,
	 * it will attempt to inject 2 - 9, 1 in the zero Nodes. On the third run, it will attempt
	 * to inject 3 - 9, 1 - 2 in the zero Nodes and so forth.
	 * @throws IOException
	 */
	private void tryRowByRowReverse() throws IOException {
		int valueOfCurrentNode, valueToBeInjected;
		boolean zeroFound = true;
		
		for (int startingValue = 1; startingValue < 10 && zeroFound; startingValue++) {
			for (int x = 8, y = 8; y > -1; y--) {	//Start looking for nodes with a value of 0 at (8, 8)
				x = 8;
				for (; x > -1; x--) {
					valueOfCurrentNode = sudokuGrid.getNode(x, y).getValue(); //Get value of currently selected Node
					
					if (valueOfCurrentNode == 0) { 			//If a node with a pre-filled value of 0 is found,
															//find out what value can be injected and inject it
						valueToBeInjected = getValueToInject(x, y, startingValue); 	
						
						if (valueToBeInjected != 0) { 						//If the valueToBeInjected is valid
							sudokuGrid.getNode(x, y).setValue(valueToBeInjected); //inject it into the Node
						}
					} //End of outer If statement
				} //End of inner For loop
			} //End of middle For loop
			zeroFound = this.checkForZeros();
			
			if (zeroFound)	{
				this.resetAllNodes();
				sudokuGrid.setTestData();
			}
		}	//End of outer For loop
	}
	
	/**
	 * This method tries to solve the puzzle column by column. It searches
	 * the grid and attempt to replace all zero nodes with the first valid value. If it fails,
	 * it will try again with a different startingValue. For example, on the first run, it will
	 * attempt to inject a value between 1 - 9 into all zero Nodes starting. On the second run,
	 * it will attempt to inject 2 - 9, 1 in the zero Nodes. On the third run, it will attempt
	 * to inject 3 - 9, 1 - 2 in the zero Nodes and so forth.
	 * @throws IOException
	 */
	private void tryColumnByColumn() throws IOException {
		int valueOfCurrentNode, valueToBeInjected;
		boolean zeroFound = true;
		
		for (int startingValue = 1; startingValue < 10 && zeroFound; startingValue++) {
			for (int x = 0, y = 0; x < 9; x++) {	//Start looking for nodes with a value of 0 at (0, 0)
				y = 0;
				for (; y < 9; y++) {
					valueOfCurrentNode = sudokuGrid.getNode(x, y).getValue(); //Get value of currently selected Node

					if (valueOfCurrentNode == 0) { 			//If a node with a pre-filled value of 0 is found,
															//find out what value can be injected and inject it
						valueToBeInjected = getValueToInject(x, y, startingValue); 	

						if (valueToBeInjected != 0) { 						//If the valueToBeInjected is valid
							sudokuGrid.getNode(x, y).setValue(valueToBeInjected); //inject it into the Node
						}
					} //End of outer If statement
				} //End of inner For loop
			} //End of middle For loop
			zeroFound = this.checkForZeros();
			
			if (zeroFound)	{
				this.resetAllNodes();
				sudokuGrid.setTestData();
			}
		}	//End of outer For loop
		
	}

	/**
	 * This method tries to solve the puzzle column by column in reverse. It searches
	 * the grid and attempt to replace all zero nodes with the first valid value. If it fails,
	 * it will try again with a different startingValue. For example, on the first run, it will
	 * attempt to inject a value between 1 - 9 into all zero Nodes starting. On the second run,
	 * it will attempt to inject 2 - 9, 1 in the zero Nodes. On the third run, it will attempt
	 * to inject 3 - 9, 1 - 2 in the zero Nodes and so forth.
	 * @throws IOException
	 */
	private void tryColumnByColumnReverse() throws IOException {
		int valueOfCurrentNode, valueToBeInjected;
		boolean zeroFound = true;
		
		for (int startingValue = 1; startingValue < 10 && zeroFound; startingValue++) {
			for (int x = 8, y = 8; x > -1; x--) {	//Start looking for nodes with a value of 9 at (8, 8)
				y = 8;
				for (; y > -1; y--) {
					valueOfCurrentNode = sudokuGrid.getNode(x, y).getValue(); //Get value of currently selected Node
					
					if (valueOfCurrentNode == 0) { 			//If a node with a pre-filled value of 0 is found,
						//find out what value can be injected and inject it
						valueToBeInjected = getValueToInject(x, y, startingValue); 	
						
						if (valueToBeInjected != 0) { 						//If the valueToBeInjected is valid
							sudokuGrid.getNode(x, y).setValue(valueToBeInjected); //inject it into the Node
						}
					} //End of outer If statement
				} //End of inner For loop
			} //End of middle For loop
			zeroFound = this.checkForZeros();
			
			if (zeroFound)	{
				this.resetAllNodes();
				sudokuGrid.setTestData();
			}
		}	//End of outer For loop
		
	}
	
	private void tryBruteForce() throws IOException {
		Random rand = new Random(),
				randValue = new Random();
		int nodeToSolve, valueToBeInjected, i = 0;
		SudokuVertexNode zeroNode;
		
		ArrayList<SudokuVertexNode> zeroNodes = getAllZeroNodes();			//Find and get all nodes with a value of 0
		
		while (this.checkForZeros())	{
			
			nodeToSolve = rand.nextInt(zeroNodes.size());
			zeroNode = zeroNodes.get(nodeToSolve);
			valueToBeInjected = randValue.nextInt(9) + 1;
											
				valueToBeInjected = checkValueToInject(zeroNode.getX(), zeroNode.getY(), valueToBeInjected);		//Check to see if the valueToBeInjected is valid
				
				if (valueToBeInjected != 0) { 						//If the valueToBeInjected is valid
					zeroNode.setValue(valueToBeInjected); 			//inject it into the Node
					zeroNodes.remove(nodeToSolve);					//Removed solved node from list of zero nodes
					}
				i++;
				
				if (i > 700 && this.checkForZeros())	{
					i = 0;
					this.resetAllNodes();
					sudokuGrid.setTestData();
					zeroNodes = getAllZeroNodes();
				}
			}
		}
	
	private ArrayList<SudokuVertexNode> getAllZeroNodes() {
		int valueOfCurrentNode;
		boolean zeroFound = false;
		ArrayList<SudokuVertexNode> zeroNodes = new ArrayList<SudokuVertexNode>();
		
		for	(int x = 0, y = 0; y < 9 && !zeroFound; y++)	{
			x = 0;
			for (; x < 9; x++){
				valueOfCurrentNode = sudokuGrid.getNode(x, y).getValue();		//Get value of currently selected Node
				if (valueOfCurrentNode == 0)	{						//If a zero is found, set zeroFound to true
																		//to exit loops
					zeroNodes.add(sudokuGrid.getNode(x, y));
				}
			}
		}
		return zeroNodes;
	}

	private int checkValueToInject(int x, int y, int valueToCheck) {
		if (checkRow(valueToCheck, x, y) != 0) { 			//Checks the row for matching values. If a matching value
															//is found, further methods are not called with this value
															//to save cycles
			if (checkColumn(valueToCheck, x, y) != 0) { 	//Checks the column for matching values. If a matching value
															//is found, the sector is not checked with this value
															//to save cycles
				if (checkSector(valueToCheck, x, y) != 0) {
					return valueToCheck; 					//Return valueToCheck to be injected
				}
			}
		}
		return 0;
	}

	/**
	 * Resets all nodes to a value of 0 by iterating through each sector
	 */
	private void resetAllNodes() {
		Iterator<SudokuVertexNode> sectorToReset = null;
		
		for (int i = 1; i < 10; i++)	{
			switch (i)	{
			case 1:		sectorToReset = sudokuGrid.getSector(1).iterator();
						break;
			case 2:		sectorToReset = sudokuGrid.getSector(2).iterator();
						break;
			case 3:		sectorToReset = sudokuGrid.getSector(3).iterator();
						break;
			case 4:		sectorToReset = sudokuGrid.getSector(4).iterator();
						break;
			case 5:		sectorToReset = sudokuGrid.getSector(5).iterator();
						break;
			case 6:		sectorToReset = sudokuGrid.getSector(6).iterator();
						break;
			case 7:		sectorToReset = sudokuGrid.getSector(7).iterator();
						break;
			case 8:		sectorToReset = sudokuGrid.getSector(8).iterator();
						break;
			case 9:		sectorToReset = sudokuGrid.getSector(9).iterator();
						break;
			default:	throw new IndexOutOfBoundsException("Error: Could not find specified index of " + i);
			}
			
			if (null != sectorToReset) { 			//Null check
				while (sectorToReset.hasNext()) {
					SudokuVertexNode nodeToReset = sectorToReset.next();
					nodeToReset.setValue(0);
				}
			} else	{
				System.out.println("Error: Could not find sector " + sectorToReset + " to reset.");
				System.exit(1);
			}
		}
	}

	/**
	 * Checks the grid for any existing zeros
	 * @return 
	 */
	private boolean checkForZeros() {
		int valueOfCurrentNode;
		boolean zeroFound = false;
		
		for	(int x = 0, y = 0; y < 9 && !zeroFound; y++)	{
			x = 0;
			for (; x < 9 && !zeroFound; x++){
				valueOfCurrentNode = sudokuGrid.getNode(x, y).getValue();		//Get value of currently selected Node
				if (valueOfCurrentNode == 0)	{						//If a zero is found, set zeroFound to true
																		//to exit loops
					zeroFound = true;
				}
			}
		}
		return zeroFound;
	}

	/**
	 * Check for values 1 - 9 in row, column, then sector. The value returned
	 * will be the first value with no match found in the row/column/sector.
	 * Otherwise, if a match was found for all values 1 - 9 then an Exception
	 * will be thrown since there must be at least one valid number to inject
	 * for an empty cell in Sudoku.
	 * 
	 * @param X
	 * @param Y
	 * @param startingValue 
	 * @return
	 * @throws IndexOutOfBoundsException 
	 */
	private int getValueToInject(int X, int Y, int startingValue) throws IndexOutOfBoundsException {
		
		/*
		 * This for loop iterates through values 1 - 9 and checks it
		 * against the selected Node's row, column then sector,
		 * graduating to the next step only if the previous step
		 * does not find a match (A match means the value already exists).
		 */
		for (int i = 0, valueToCheck = startingValue; i < 9; i++, valueToCheck++) {
			
			if (checkRow(valueToCheck, X, Y) != 0) {		//Checks the row for matching values. If a matching value
															//is found, further methods are not called with this value
															//to save cycles
				if (checkColumn(valueToCheck, X, Y) != 0) {	//Checks the column for matching values. If a matching value
															//is found, the sector is not checked with this value
															//to save cycles
					if (checkSector(valueToCheck, X, Y) != 0)	{
						return valueToCheck;	//Return valueToCheck to be injected
					}
				}
			}
			if (valueToCheck == 9)	{
				valueToCheck = 0;
			}
		}
		
		return 0;	//Returns 0 if a match was found, signifying an error in game logic
	}

	/**
	 * Check the column for the passed in valueToCheck
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
