package hw4;

import java.awt.Color;

import graph.Cell;
import main.Config;
import state.Snake;
import state.SnakeSegment;
import state.State;


/**
 * Creates a snake head with an initial timer of 0. Timer resets upon reaching MAX_SNAKE_TIMER, the timer resets.
 * Keeps track of the length, initially at 4. Upon reset, the snake finds a random cell closer to the mouse. If no cell
 * exists, the player loses the game, and the end game prompt is displayed.
 * @author Nathan Shull
 *
 */
public class SnakeHead implements Snake, State {
	
	private int timer = 0;
	private int length = 4;
	
	public SnakeHead(){
		
	}
	public int getLength(){
		return length;
	}
	public Color getColor(){
		return Color.CYAN;
	}
	
	public void handle(Cell cell){
		timer = timer + 1;
		
		if(timer % Config.MAX_SNAKE_TIMER == 0) {
			Cell temp = null;
			if(!(cell.getRandomCloser() == null)){
				temp = cell.getRandomCloser();
			}
			else if(!(cell.getRandomOpen() == null)){
				temp = cell.getRandomOpen();
			}
			else{
				Config.endGame(length);
			}
			if(temp.getState() instanceof Food || temp.getState() instanceof DungeonessCrab)
				length = length + 1;
			cell.moveState(temp);
			cell.setState(new SnakeSegment(this));
			timer = 0;
		}
	}
	
	public boolean isPassable(){
		return false;
	}
	
	public char toChar(){
		return 'S';
	}
}
