import java.awt.Color;
import java.lang.reflect.Array;

public interface GraphADT
{	
	public void Vertex(int V); 	// Vertex with no edges
	public int Vertices(); 		// Returns number of vertices
	public int Edges();			// Returns number of edges
	public void addEdge(int V, int W);	// Adds edge v-w 
	Iterable<Integer> Adjacent(int v);  // Returns vertices adjacent to v
		
	
	class SudokuVertexNode
	{
		private int value;
		private Color color;
		private int xRow, yCol;
		
		public SudokuVertexNode(int v, int x, int y)
		{
			this.value = v;
			this.xRow = x;
			this.yCol = y;
		}
		
		public int getVertexValue()
		{
			return this.value;
		}
		
		public void setColor(Color c)
		{
			this.color = c;
		}
		
		public Color getColor()
		{
			return this.color;
		}
		
		public int[] getCoordinates()
		{
			int[] array = {this.xRow, this.yCol};
			return array;
		}
						
	}
}