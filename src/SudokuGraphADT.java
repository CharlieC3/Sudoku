import java.awt.Color;
import java.util.ArrayList;

public interface SudokuGraphADT
{	
	public SudokuVertexNode add(int v, int x, int y); 	// Vertex with no edges
	public SudokuVertexNode add(int x, int y);			// Vertex with no edges, value = 0;
	public int numVertices(); 					// Returns number of vertices
	public int numEdges();						// Returns number of edges
	public void addEdge(int V, int W);	// Adds edge v-w 
	//Iterable<Integer> Adjacent(int v);  // Returns vertices adjacent to v
		
	
	class SudokuVertexNode
	{
		private int value;
		private Color color;
		private int xRow, yCol;
		private ArrayList<SudokuVertexNode> edges;
		
		public SudokuVertexNode(int v, int x, int y)
		{
			this.value = v;
			this.xRow = x;
			this.yCol = y;
			edges = new ArrayList<SudokuVertexNode>();
		}
		
		public SudokuVertexNode(int x, int y)
		{
			this.value = 0;
			this.xRow = x;
			this.yCol = y;
			edges = new ArrayList<SudokuVertexNode>();
		}
		
		public void setVertexValue(int v)
		{
			this.value = v;
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
		
		public void addEdge(SudokuVertexNode n)
		{
			this.edges.add(n);
		}
		
		public int[] getCoordinates()
		{
			int[] array = {this.xRow, this.yCol};
			return array;
		}
		
		public int getX()
		{
			return this.xRow;
		}
		
		public int getY()
		{
			return this.yCol;
		}
						
	}
}