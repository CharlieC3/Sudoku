
public class Run 
{
	public static void main(String[] args) 
	{
		SudokuProcessor sudokuGrid = new SudokuProcessor();
		
		// Create 9x9 grid and add 
		// Row/Column edges
		
		// Rows
		for (int x = 0; x < 9 ; x++)
		{
			// Columns
			for (int y = 0; y < 9; y++)
			{
				// Create Node and create Column edges
				sudokuGrid.addEdges(sudokuGrid.getNode(x, y-1), sudokuGrid.add(x, y));				
			}
		}
		
		for (int y = 0; y < 9; y++)
		{
			for (int x = 0; x < 9; x++)
				// Create row edges
				sudokuGrid.addEdges(sudokuGrid.getNode(x-1, y), sudokuGrid.getNode(x, y));
		}
		
		// Create edges for 9-blocks
		
		
			

	}

}
