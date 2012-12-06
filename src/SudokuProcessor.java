import java.util.ArrayList;

public class SudokuProcessor
{

	static int vertices = 0;
	static SudokuVertexNode first;
	private ArrayList<SudokuVertexNode> sector1, sector2, sector3,
										sector4, sector5, sector6,
										sector7, sector8, sector9;

	public SudokuVertexNode add(int v, int x, int y) 
	{
		if (first == null)
		{
			first = new SudokuVertexNode(v, x, y);
			vertices++;
			initilizeSectors();
			return first;
		}
		else 
		{
			vertices++;
			return new SudokuVertexNode(v, x, y);
		}
	}
	
	public void initilizeSectors()
	{
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
	
	public void addNodeToSector(SudokuVertexNode n)
	{
		int x = n.getX();
		int y = n.getY();
		
		switch(x)
		{
		case 0: case 1: case 2:
			switch(y)
			{
			case 0: case 1: case 2: sector1.add(n); break;
			case 3: case 4: case 5: sector4.add(n); break;
			case 6: case 7: case 8: sector7.add(n); break;
			}
			
		case 3: case 4: case 5:
			switch(y)
			{
			case 0: case 1: case 2: sector2.add(n); break;
			case 3: case 4: case 5: sector5.add(n); break;
			case 6: case 7: case 8: sector8.add(n); break;
			}
			
		case 6: case 7: case 8: 
			switch(y)
			{
			case 0: case 1: case 2: sector3.add(n); break;
			case 3: case 4: case 5: sector6.add(n); break;
			case 6: case 7: case 8: sector9.add(n); break;
			}
		}
	}
	
	public int numVertices() 
	{
		return vertices;
	}

	public SudokuVertexNode getNode(int x, int y)
	{		
		return null;
	}
	

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
