import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
	private boolean solved = false;
	
	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

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
	
	public void setTestData() throws IOException {

		BufferedReader sudokuValues = null;
		 
		try {

			int currentValue;

			sudokuValues = new BufferedReader(new FileReader(
					"sudoku_values.txt"));
			for (int x = 0, y = 0; y < 9; y++) {
				x = 0;
				for (; x < 9; x++) {
					do	{
						currentValue = Character.getNumericValue(sudokuValues
								.read());
					} while (currentValue == -1);
					
						this.getNode(x, y).setValue(currentValue);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void printGrid()	{
		
		if (solved) {
			System.out
					.println("*****************SOLVED SUDOKU GRID*****************");
		} else {
			System.out
					.println("****************UNSOLVED SUDOKU GRID****************");
		}

		for	(int x = 0, y = 0; y < 9; y++)	{
			x = 0;
			for (; x < 9; x++){
				switch (x){
					case 0:  System.out.print("\t     " + this.getNode(x, y).getValue() + "  ");
							 break;
					case 8:  System.out.println(this.getNode(x, y).getValue());
							 break;
					default: System.out.print(this.getNode(x, y).getValue() + "  ");
							 break;
				}
			}
		}
		System.out.println("\n");
	}
	
	public ArrayList<SudokuVertexNode> getSector(int sector)	{
		
		switch (sector)	{
			case 1:	return sector1;
			case 2:	return sector2;
			case 3:	return sector3;
			case 4:	return sector4;
			case 5:	return sector5;
			case 6:	return sector6;
			case 7:	return sector7;
			case 8:	return sector8;
			case 9:	return sector9;
			default: System.out.println("Error: Could not find sector " + sector + " to return.");
					 System.exit(1);
		}
		return null;
	}
}
