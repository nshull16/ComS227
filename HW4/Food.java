package hw4;

import java.awt.Color;

import graph.Cell;
import main.Config;
import state.State;

/**
 * Creates a food represented by F that has a timer, updates in color depending on time, 
 * and is passable by snakes.
 * @author Nathan Shull
 *
 */
public class Food implements State{
	private int timer = 0;
	
	/**
	 * Timer updates each time it is called
	 * @param cell current cell belonging to the current state
	 */
	public void handle(Cell cell){
		timer += 1;
		timer = timer % Config.MAX_FOOD_TIMER;
	}
	
	public Color getColor(){
		return Config.FOOD_COLORS[timer];
	}
	
	public boolean isPassable(){
		return true;
	}
	
	public char toChar(){
		return 'F';
	}

}
