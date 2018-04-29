package hw4;

import graph.Cell;
import main.Config;

/**
 * Create a subtype of food that walks. Everytime the timer resets,
 * it randomly looks for an open cell.
 * @author Nathan Shull
 *
 */
public class DungeonessCrab extends Food {

	private int timer = 0;
	/**
	 * Updates the cell off the state and timer. When the timer reaches
	 * MAX_FOOD_TIMER, the cell looks for a random adjacent spot to move to.
	 * @param cell to update
	 */
	@Override
	public void handle (Cell cell){
		timer++;
		if(timer % Config.MAX_FOOD_TIMER == 0){
			if(cell.getRandomOpen() != null)
				cell.moveState(cell.getRandomOpen());
		}
		timer = timer % Config.MAX_FOOD_TIMER;
	}
	@Override
	public char toChar(){
		return 'D';
	}
}
