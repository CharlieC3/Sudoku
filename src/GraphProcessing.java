public class GraphProcessing implements GraphADT
{

	static int vertices = 0;
	static int edges = 0;
	
	public void Vertex(int V) 
	{
		//new Vertex(V);
		vertices++;
	}

	public int Vertices() 
	{
		return vertices;
	}

	public int Edges() 
	{

		return edges;
	}

	public void addEdge(int V, int W) 
	{
		
	}
	
	/*
	public void addEdge(Vertex v, Vertex w)
	{
		
	}
	*/
	
	@Override
	public Iterable<Integer> Adjacent(int v) {
		// TODO Auto-generated method stub
		return null;
	}

}
