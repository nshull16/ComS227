package hw4;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import graph.Cell;

/**
 * 
 * @author Nathan Shull
 *
 */
public class SquareMap extends graph.GraphMap{
	/**
	 * Create a polygon for the cell with the given row and column.
	 * @param col The column index of a Cell
	 * @param row The row index of a Cell
	 * @return A polygon with correct pixel coordinates for returning the cell
	 */
	@Override
	public Polygon createPolygon(int col, int row){
		int yoff = getDistance() / 2 + (row*getDistance());
		int xoff = getDistance() / 2 + (col*getDistance());
		int[] xaxis = {xoff, xoff, xoff + getDistance(), xoff + getDistance()};
		int[] yaxis = {yoff, yoff + getDistance(), yoff + getDistance(), yoff};
		return new Polygon(xaxis,yaxis, 4);
		
	}
	/**
	 * Gets the width of the window in pixels for returning,
	 * including the border area.
	 * @return The width in pixels
	 */
	@Override
	public int getPixelWidth(){
		return getDistance() * getCells()[0].length + getDistance();
	}
	/**
	 * Gets the height of the window in pixels for returning,
	 * including the border area.
	 * @return The height in pixels
	 */
	@Override
	public int getPixelHeight(){
		return getDistance() * getCells().length + getDistance();
	}
	/**
	 * Create an array of neighbors for the cell with given row and column.
	 * @param col The column index of a Cell
	 * @param row The row index of a Cell
	 * @return An array containing adjacent cells
	 */
	@Override
	public Cell[] createNeighbors(int col, int row){
		Cell[][] map = getCells();
		ArrayList<Cell> cells = new ArrayList<Cell>();
		
		if (col - 1 >= 0) {
			cells.add(map[row][col - 1]);
		}
		if (col + 1 < map[0].length) {
			cells.add(map[row][col + 1]);
		}
		if(row + 1 < map.length){
			cells.add(map[row+1][col]);
		}
		if(row - 1 >= 0){
			cells.add(map[row-1][col]);
		}
		
		return cells.toArray(new Cell[0]);
	}
	/**
	 * Gets the column and row indices for the cell closest to the given
	 * pixel coordinate, returned as a Point object in which 
	 * x is the column and y is the row.
	 * @param x The x coordinate in pixels
	 * @param y The y coordinate in pixels
	 * @return column and row indices for the cell closest to the given coordinate
	 */
	@Override
	protected Point selectClosestIndex(int x, int y){
		int xPoint = (x-getDistance()/2) / getDistance();
		int yPoint = (y-getDistance()/2) / getDistance();
		Point location = new Point(xPoint, yPoint);
		return location;
	}
}
