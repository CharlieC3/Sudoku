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
				case 0: case 1: case 2: sector1.add(nodeToAdd); 
					break;
				case 3: case 4: case 5: sector4.add(nodeToAdd); 
					break;
				case 6: case 7: case 8: sector7.add(nodeToAdd); 
					break;
			}
			break;
			
		case 3: case 4: case 5:
			switch(y) {
				case 0: case 1: case 2: sector2.add(nodeToAdd); 
					break;
				case 3: case 4: case 5: sector5.add(nodeToAdd); 
					break;
				case 6: case 7: case 8: sector8.add(nodeToAdd); 
					break;
			}
			break;
			
		case 6: case 7: case 8: 
			switch(y) {
				case 0: case 1: case 2: sector3.add(nodeToAdd); 
					break;
				case 3: case 4: case 5: sector6.add(nodeToAdd); 
					break;
				case 6: case 7: case 8: sector9.add(nodeToAdd); 
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
}
