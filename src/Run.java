
public class Run 
{
	public static void main(String[] args) 
	{
		SudokuProcessor sudokuGrid = new SudokuProcessor();
		SudokuVertexNode temp = null;
		// Create 9x9 grid and add 
		// N/S edges, and x-y coord
		
		// Rows
		for (int x = 0; x < 9 ; x++)
		{
			// Columns
			for (int y = 0; y < 9; y++)
			{
				SudokuVertexNode newNode = sudokuGrid.add(x, y);
				
				if 	(y == 0) // Node at "top" of the graph; This means the last
							 // added node would be at the "bottom" of the graph in the
							 // column before it, and THAT node should have south
							 // node point to null, since there's none "below" it
					temp.setSouth(null);
				else
					temp.setSouth(newNode);
				
				if (y == 0) // Node at the "top" of the graph; Should have north
						  // node point to null, since there's none "above" it
					newNode.setNorth(null);
				else 
					newNode.setNorth(temp);
				
				sudokuGrid.addNodeToSector(newNode);
				temp = newNode;
			}
		}
		
		// How to get east-west edges?
		
		
	}

}

