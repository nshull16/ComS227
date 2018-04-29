package mini2;

import api.ITransform;

/**
 * Transformation implementing Conway's Game of Life.
 * All cells have value 0 or 1. The new value is based on the center 
 * cell along with the sum S of its eight neighbors, according to 
 * the rules:
 * <ul>
 *   <li>if S is less than 2 the result is 0
 *   <li>if S is greater than 3 the result is 0
 *   <li>if the center cell is 1 and S is 2 or 3, the result is 1
 *   <li>if the center cell is 0 and S is exactly 3, the result is 1
 * </ul>
 * See
 * http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 * <p>
 * The radius is 1 and the <code>isWrapped()</code> method 
 * always returns true.
 */
public class ConwayTransform implements ITransform
{

  @Override
  public int apply(int[][] elements)
  {
    if(elements.length != 3 || elements[0].length != 3){
    	throw new IllegalArgumentException();
    }
    
    int total = 0;
    for(int i = 0; i < 9; i++){
    	total += elements[i/3][i%3];
    }
    
    total -= elements[1][1];
    if(total < 2 || total > 3){
    	return 0;
    }
    
    if((total == 2 || total == 3) && elements[1][1] == 1){
    	return 1;
    }
    
    if(elements[1][1] == 0 && total == 3){
    	return 1;
    }
    
    else{
    	return elements[1][1];
    }
    
  }

  @Override
  public int getRadius()
  {
    return 1;
  }

  @Override
  public boolean isWrapped()
  {
    return true;
  }
  
}
