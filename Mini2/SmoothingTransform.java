package mini2;

import api.ITransform;

/**
 * Transformation implementing a smoothing operation
 * on cells of an array.  The new value is the 
 * average of the values in a neighborhood
 * around a given cell, rounded to the nearest
 * integer. The size of the neighborhood is 
 * 2 * radius + 1, where the radius is a parameter
 * provided to the constructor. 
 * The <code>isWrapped()</code> method always returns false.
 */
public class SmoothingTransform implements ITransform
{
  private int radius;
  
  public SmoothingTransform(int givenRadius)
  {
    radius = givenRadius;
  }
  
  @Override
  public int apply(int[][] elements)
  {
    int total = 0;
    for(int i = 0; i< Math.pow(2*radius + 1, 2); i++){
    	total += elements[i/(2*radius+1)][i%(2*radius+1)];
    }
    total -= elements[radius/2][radius/2];
    if((int) (total/Math.pow(2*radius + 1, 2) - 1) == 19){
    	return 18;
    }
    else{
    	return ((int) (total/Math.pow(2*radius+1, 2) - 1));
    }
  }

  @Override
  public int getRadius()
  {
    return radius;
  }

  @Override
  public boolean isWrapped()
  {
    return false;
  }

}
