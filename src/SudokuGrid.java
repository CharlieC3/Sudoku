import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class is initialized as an empty grid containing 9 blank ArrayLists (sectors).
 * The ArrayLists get populated by 9 nodes each to form a grid. Nodes must be added in order.
 * Conventionally, point (0,0) on the grid would point to the bottom left node,
 * but for our purposes, (0,0) points to the top left while (9,9) points to the bottom right node.
 * Do not question our logic.
 * 
 * @author Rachael, Charlie
 *
 */
public class SudokuGrid {

	int vertices = 0;
	private ArrayList<SudokuVertexNode> sector1, sector2, sector3, sector4,
			sector5, sector6, sector7, sector8, sector9;

	public SudokuGrid()	{
		initilizeSectors();
	}
	
	public void initilizeSectors() {
		sector1 = new ArrayList<SudokuVertexNode>();
		sector2 = new ArrayList<SudokuVertexNode>();
		sector3 = new ArrayList<SudokuVertexNode>();
		sector4 = new ArrayList<SudokuVertexNode>();
		sector5 = new ArrayList<SudokuVertexNode>();
		sector6 = new ArrayList<SudokuVertexNode>();
		sector7 = new ArrayList<SudokuVertexNode>();
		sector8 = new ArrayList<SudokuVertexNode>();
		sector9 = new ArrayList<SudokuVertexNode>();
	}

	public void addNodeToSector(SudokuVertexNode nodeToAdd) {
		int x = nodeToAdd.getX();
		int y = nodeToAdd.getY();

		switch(x) {
		case 0: case 1: case 2:
			switch(y) {
				case 0: case 1: case 2: nodeToAdd.setSector(1);
										sector1.add(nodeToAdd); 
					break;
				case 3: case 4: case 5: nodeToAdd.setSector(4);
										sector4.add(nodeToAdd); 
					break;
				case 6: case 7: case 8: nodeToAdd.setSector(7);
										sector7.add(nodeToAdd); 
					break;
			}
			break;
			
		case 3: case 4: case 5:
			switch(y) {
				case 0: case 1: case 2: nodeToAdd.setSector(2);
										sector2.add(nodeToAdd); 
					break;
				case 3: case 4: case 5: nodeToAdd.setSector(5);
										sector5.add(nodeToAdd); 
					break;
				case 6: case 7: case 8: nodeToAdd.setSector(8);
										sector8.add(nodeToAdd); 
					break;
			}
			break;
			
		case 6: case 7: case 8: 
			switch(y) {
				case 0: case 1: case 2: nodeToAdd.setSector(3);
										sector3.add(nodeToAdd); 
					break;
				case 3: case 4: case 5: nodeToAdd.setSector(6);
										sector6.add(nodeToAdd); 
					break;
				case 6: case 7: case 8: nodeToAdd.setSector(9);
										sector9.add(nodeToAdd); 
					break;
			}
			break;
		}
	}

	public int numVertices() {
		return vertices;
	}

	public SudokuVertexNode getNode(int x, int y) {
		SudokuVertexNode nodeToReturn = sector1.get(0);
		
		if (x ==0 & y == 0){
			return nodeToReturn;
		}
		
		//Searches the grid by starting at (0,0)
		for (int i = 0; i < x; i++) {
			nodeToReturn = nodeToReturn.getEast();
		}
		
		for (int k = 0; k < y; k++) {
			nodeToReturn = nodeToReturn.getSouth();
		}
		
		
		return nodeToReturn;
	}

	/**
	 * Adds a node to a sector
	 * @param x
	 * @param y
	 * @return
	 */
	public void add(SudokuVertexNode nodeToAdd) {
		addNodeToSector(nodeToAdd);
		vertices++;
	}
	
	public SudokuVertexNode getFirst()	{
		return this.getNode(0, 0);
	}
	
	public void setTestData()
	{
		this.getNode(1,0).setValue(8);
		this.getNode(5,0).setValue(5);
		this.getNode(0,1).setValue(5);
		this.getNode(5,1).setValue(2);
		this.getNode(6,1).setValue(1);
		this.getNode(7,1).setValue(9);
		this.getNode(8,1).setValue(8);
		this.getNode(0,2).setValue(2);
		this.getNode(4,2).setValue(8);
		this.getNode(7,2).setValue(4);
		this.getNode(8,2).setValue(3);
		this.getNode(0,3).setValue(8);
		this.getNode(2,3).setValue(3);
		this.getNode(4,3).setValue(1);
		this.getNode(4,5).setValue(5);
		this.getNode(6,5).setValue(6);
		this.getNode(8,5).setValue(4);
		this.getNode(0,6).setValue(6);
		this.getNode(1,6).setValue(9);
		this.getNode(4,6).setValue(3);
		this.getNode(8,6).setValue(7);
		this.getNode(0,7).setValue(7);
		this.getNode(1,7).setValue(2);
		this.getNode(2,7).setValue(4);
		this.getNode(3,7).setValue(8);
		this.getNode(8,7).setValue(5);
		this.getNode(3,8).setValue(6);
		this.getNode(7,8).setValue(8);
		
	}
	
	public void colorizeGrid()
	{
		
	}
	
	public void printGrid()	{
		System.out.println("********************SUDOKU GRID********************");
		
		for	(int x = 0, y = 0; y < 9; y++)	{
			x = 0;
			for (; x < 9; x++){
				switch (x){
					case 0:  System.out.print("             " + this.getNode(x, y).getValue() + "  ");
							 break;
					case 8:  System.out.println(this.getNode(x, y).getValue());
							 break;
					default: System.out.print(this.getNode(x, y).getValue() + "  ");
							 break;
				}
			}
		}
	}

	public void solvePuzzle() throws IndexOutOfBoundsException {
		int valueOfCurrentNode, valueToBeInjected;
		boolean zeroFound = true;

		for (int startingValue = 1; startingValue < 10 && zeroFound; startingValue++) {
			for (int x = 0, y = 0; y < 9; y++) {	//Start looking for nodes with a value of 0 at (0, 0)
				x = 0;
				for (; x < 9; x++) {
					valueOfCurrentNode = this.getNode(x, y).getValue(); //Get value of currently selected Node

					if (valueOfCurrentNode == 0) { 			//If a node with a pre-filled value of 0 is found,
															//find out what value can be injected and inject it
						valueToBeInjected = getValueToInject(x, y, startingValue); 	

						if (valueToBeInjected != 0) { 						//If the valueToBeInjected is valid
							this.getNode(x, y).setValue(valueToBeInjected); //inject it into the Node
							//System.out.println(valueToBeInjected
							//		+ " injected in node (" + x + ", " + y
							//		+ ").");
						}
					} //End of outer If statement
				} //End of inner For loop
			} //End of middle For loop
			zeroFound = this.checkForZeros();
			
			if (zeroFound)	{
				System.out.println("Number of tries to solve puzzle: " + startingValue);
				this.printGrid();
				System.out.println("Zero found");
				this.resetAllNodes();
				this.setTestData();
			}
		}	//End of outer For loop
	}

	/**
	 * Resets all nodes to a value of 0 by iterating through each sector
	 */
	private void resetAllNodes() {
		System.out.println("Reseting all nodes");
		Iterator<SudokuVertexNode> sectorToReset = null;
		
		for (int i = 1; i < 10; i++)	{
			switch (i)	{
			case 1:		sectorToReset = sector1.iterator();
						break;
			case 2:		sectorToReset = sector2.iterator();
						break;
			case 3:		sectorToReset = sector3.iterator();
						break;
			case 4:		sectorToReset = sector4.iterator();
						break;
			case 5:		sectorToReset = sector5.iterator();
						break;
			case 6:		sectorToReset = sector6.iterator();
						break;
			case 7:		sectorToReset = sector7.iterator();
						break;
			case 8:		sectorToReset = sector8.iterator();
						break;
			case 9:		sectorToReset = sector9.iterator();
						break;
			default:	throw new IndexOutOfBoundsException("Error: Could not find specified index of " + i);
			}
			
			while (sectorToReset.hasNext())	{
				SudokuVertexNode nodeToReset = sectorToReset.next();
				nodeToReset.setValue(0);
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
				valueOfCurrentNode = this.getNode(x, y).getValue();		//Get value of currently selected Node
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
			if (this.getNode(x, Y).getValue() == valueToCheck) {
				return 0;		//Return 0 if the value already exists in the row
			}
		}
		return valueToCheck; 	// No match was found, meaning the valueToCheck is
								// a possible candidate to be injected
	}
	
	private int checkColumn(int valueToCheck, int X, int Y) {
		// Check the column for the passed in valueToCheck
		for (int y = 0; y < 9; y++) {
			if (this.getNode(X, y).getValue() == valueToCheck) {
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
		
		switch (this.getNode(X, Y).getSector())	{
		case 1:		sectorToCheck = sector1.iterator();
					break;
		case 2:		sectorToCheck = sector2.iterator();
					break;
		case 3:		sectorToCheck = sector3.iterator();
					break;
		case 4:		sectorToCheck = sector4.iterator();
					break;
		case 5:		sectorToCheck = sector5.iterator();
					break;
		case 6:		sectorToCheck = sector6.iterator();
					break;
		case 7:		sectorToCheck = sector7.iterator();
					break;
		case 8:		sectorToCheck = sector8.iterator();
					break;
		case 9:		sectorToCheck = sector9.iterator();
					break;
		default:	throw new IndexOutOfBoundsException("Error: Could not find specified sector of " + sectorToCheck);
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
