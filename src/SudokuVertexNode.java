public class SudokuVertexNode 
{
	private SudokuVertexNode north, south, east, west;
	private int value, sector;
	
	private int xRow, yCol;
	
	public SudokuVertexNode(int v, int x, int y)
	{
		this.value = v;
		this.xRow = x;
		this.yCol = y;
	}
	
	public SudokuVertexNode(int x, int y)
	{
		this.value = 0;
		this.xRow = x;
		this.yCol = y;
	}
	
	public int getX()
	{
		return this.xRow;
	}
	
	public int getY()
	{
		return this.yCol;
	}
	
	public SudokuVertexNode getNorth() {
		return north;
	}

	public void setNorth(SudokuVertexNode north) {
		this.north = north;
	}

	public SudokuVertexNode getSouth() {
		return south;
	}

	public void setSouth(SudokuVertexNode south) {
		this.south = south;
	}

	public SudokuVertexNode getEast() {
		return east;
	}

	public void setEast(SudokuVertexNode east) {
		this.east = east;
	}

	public SudokuVertexNode getWest() {
		return west;
	}

	public void setWest(SudokuVertexNode west) {
		this.west = west;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getSector() {
		return sector;
	}

	public void setSector(int sector) {
		this.sector = sector;
	}
}