public class SudokuProcessor implements SudokuGraphADT
{

	static int vertices = 0;
	static int edges = 0;
	static SudokuVertexNode first;
	
	public SudokuVertexNode add(int v, int x, int y) 
	{
		if (first == null)
		{
			first = new SudokuVertexNode(v, x, y);
			vertices++;
			return first;
		}
		else 
		{
			vertices++;
			return new SudokuVertexNode(v, x, y);
		}
	}

	public int numVertices() 
	{
		return vertices;
	}

	public int numEdges() 
	{
		return edges;
	}

	public SudokuVertexNode getNode(int x, int y)
	{		
		return null;
	}
	
	public void addEdge(int v, int w) 
	{
		
	}
	
	public void addEdges(SudokuVertexNode v, SudokuVertexNode w)
	{
		v.addEdge(w);
		w.addEdge(v);
	}

	@Override
	public SudokuVertexNode add(int x, int y) 
	{
		if (first == null)
		{	
			vertices++;
			first = new SudokuVertexNode(x, y);
			return first;
		}
		else 
		{
			vertices++;
			return new SudokuVertexNode(x, y);
		}
	}



}
