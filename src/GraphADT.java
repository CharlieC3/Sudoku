public interface GraphADT
{
	public void Vertex(int V); 	// Vertex with no edges
	public int Vertices(); 		// Returns number of vertices
	public int Edges();			// Returns number of edges
	public void addEdge(int V, int W);	// Adds edge v-w 
	Iterable<Integer> Adjacent(int v);  // Returns vertices adjacent to v
		
	
	class Vertex
	{
		private int value;
		
		public Vertex(int v)
		{
			value = v;
		}
				
	}
}