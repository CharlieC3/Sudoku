import java.awt.Color;
import java.util.ArrayList;

public class SudokuVertexNode 
{
	private SudokuVertexNode north, south, east, west;
	private int sector, key, value;
	
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

	public int getSector() {
		return sector;
	}

	public void setSector(int sector) {
		this.sector = sector;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
