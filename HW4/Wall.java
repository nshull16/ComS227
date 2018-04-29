package hw4;

import java.awt.Color;

import graph.Cell;
import state.State;

/**
 * Creates white walls, that don't move, and are not passable.
 * Represented with a '#'.
 * @author Nathan Shull
 *
 */
public class Wall implements State{
	public void handle(Cell cell){
		
	}
	public Color getColor(){
		return Color.WHITE;
	}
	public boolean isPassable(){
		return false;
	}
	public char toChar(){
		return'#';
	}

}
