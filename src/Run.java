import java.io.IOException;

public class Run {
	public static void main(String[] args) throws IndexOutOfBoundsException, IOException {
		SudokuGrid sudokuGrid = new SudokuGrid();
		SudokuProcessor processor = null;
		// Create 9x9 grid and add
		// N/S edges, and x-y coord

		sudokuGrid = createGrid();			 
		sudokuGrid.setTestData();
		
		processor = new SudokuProcessor(sudokuGrid);
		sudokuGrid.printGrid();
		
		System.out.println("Thinking...\n");
		
		processor.solvePuzzle();		
		sudokuGrid.printGrid();

	}

	private static SudokuGrid createGrid() {
		SudokuVertexNode temp = null;
		SudokuGrid gridToCreate = new SudokuGrid();
		
		// Columns
		for (int y = 0; y < 9; y++) {
			// Rows
			for (int x = 0; x < 9; x++) {
				SudokuVertexNode newNode = new SudokuVertexNode(x, y);

				switch (x) {
				case 0:
					switch (y) {
					case 0: // First node in grid
							newNode.setNorth(null);	// Set everything null
							newNode.setSouth(null);
							newNode.setEast(null);
							newNode.setWest(null);
							break;

					default: // First column in grid
							temp = gridToCreate.getNode(x, y - 1); 	// Get node above
							temp.setSouth(newNode); 				// Set South of above node
							newNode.setNorth(temp); 				// Set North of current node
							newNode.setSouth(null);
							newNode.setEast(null);
							newNode.setWest(null);
							break;
					}
					break;
				default:
					switch (y) {
					case 0: // First row in grid
							temp = gridToCreate.getNode(x - 1, y);	// Get previous node
							temp.setEast(newNode); 					// Set East of previous node
							newNode.setNorth(null);
							newNode.setSouth(null);
							newNode.setEast(null);
							newNode.setWest(temp); 					// Set West of current node
							break;

					default: // This sets nodes NOT in the paths (x, 0) or (0, y)
							temp = gridToCreate.getNode(x - 1, y);	// Get previous node
							temp.setEast(newNode); 					// Set East of previous node
							newNode.setWest(temp); 					// Set West of current node

							temp = gridToCreate.getNode(x, y - 1);	// Get node above
							temp.setSouth(newNode); 				// Set South of above node
							newNode.setNorth(temp); 				// Set North of current node

							newNode.setSouth(null);
							newNode.setEast(null);
							break;
					}
					break;
				}
				gridToCreate.add(newNode); // Finally, add the node to the grid
			}
		}
		return gridToCreate;
	}
}
