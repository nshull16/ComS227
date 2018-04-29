package mini2;

import api.ITransform;

/**
 * Utility class for applying transformations to 2d arrays.
 * A transformation computes a new value for a cell that is
 * based on the square neighborhood of cells that surround it.
 * Each transformation is an implementation of 
 * the ITransform interface.
 */
public class GridUtil
{
  /**
   * Applies the given transformation to all cells of the given
   * array and produces a new array of the same size.  The original
   * array is not modified.
   * @param arr
   *   given array
   * @param transform
   *   transformation to apply
   * @return
   *   new array consisting of transformed values
   */
  public static int[][] applyAll(int[][] arr, ITransform transform)
  {
    // THIS METHOD IS FULLY IMPLEMENTED AND SHOULD NOT BE MODIFIED
    int numRows = arr.length;
    int numCols = arr[0].length;
    int[][] result = new int[numRows][numCols];
    for (int row = 0; row < numRows; row += 1)
    {
      for (int col = 0; col < numCols; col += 1)
      {
        int[][] subArray = getSubArray(arr, row, col, transform.getRadius(), transform.isWrapped());
        int newValue = transform.apply(subArray);
        result[row][col] = newValue;
      }
    }
    return result;
  }
   
  /**
   * Displays the contents of a 2d int array
   * @param arr
   *   a 2d array of ints
   */
  public static void printArray(int[][] arr)
  {
    for (int row = 0; row < arr.length; row += 1)
    {
      for (int col = 0; col < arr[0].length; col += 1)
      {
        System.out.printf("%3d", arr[row][col]);
      }
      System.out.println();
    }
  }
  
  /**
   * Returns the neighborhood surrounding the specified cell.  In general,
   * the returned array is a square sub-array of <code>arr</code>, 
   * with width and height 2 * <code>radius</code> + 1, whose center is 
   * <code>arr[centerRow][centerCol]</code>.  For cells within
   * <code>radius</code> of the edge, the value for out-of-range
   * indices is determined by
   * the <code>wrapped</code> parameter, as follows:
   * <ul>
   * <li>if <code>wrapped</code> is false, cells for out-of-range indices
   * are filled with zeros
   * <li>if <code>wrapped</code> is true, cells for out-of-range indices
   * are determined by "wrapping" the indices:
   * <ul>
   * <li>for a row index, the array height is added to or subtracted from the index
   * to bring it within range
   * <li>for a column index, the array width is added to or subtracted from
   * the index to bring it within range
   * </ul>
   * </ul>
   * Note that the size of neighborhood, neighborhood size 2 * <code>radius</code> + 1, 
   * is not allowed to be greater than either the width or height of the given array.
   * @param arr
   *   the original array
   * @param centerRow
   *   row index of center cell
   * @param centerCol
   *   column index of center cell
   * @param radius
   *   radius of the neighborhood (return array has width and height
   *   equal to 2 * <code>radius</code> + 1
   * @param wrapped
   *   true if out-of-range indices should be wrapped, false if cells
   *   should be filled with zeros
   * @return
   *   a new 2d array consisting of the cells surrounding the given center
   *   cell
   * @throws IllegalArgumentException
   *   if the neighborhood size 2 * <code>radius</code> + 1 is greater
   *   than the given array's width or height
   */
  public static int[][] getSubArray(int[][] arr, int centerRow, int centerCol, int radius, boolean wrapped)
  {
	  int width = 2*radius+1;
	  int[][] a= new int [width][width];
	  int arrRow=radius*-1;
	  int arrCol=radius*-1;
	  
	  if(wrapped==false){
		  
		 for(int i=0; i<a.length;i++){
		 arrCol=radius*-1;
			 for(int j=0;j<a[0].length;j++){
				 if(arrRow+centerRow>arr.length-1||arrCol+centerCol>arr[0].length-1||arrCol+centerCol<0||arrRow+centerRow<0){
					 a[i][j]=0;
				 }
				 else{
					 a[i][j]=arr[arrRow+centerRow][arrCol+centerCol];
				 }
				arrCol+=1;
			 }
			 arrRow+=1;
		 }
	  }
	  if(wrapped==true){
			 for(int i=0; i<a.length;i++){
			 arrCol=radius*-1;
				 for(int j=0;j<a[0].length;j++){
					 	if(arrCol+centerCol>arr[0].length-1&&arrRow+centerRow>arr.length-1){
					 			a[i][j]=arr[centerRow+arrRow-arr.length][centerCol+arrCol-arr[0].length];	 
					 	}
					 	else if(arrCol+centerCol<0&&arrRow+centerRow<0){
					 		a[i][j]=arr[centerRow+arrRow+arr.length][centerCol+arrCol+arr[0].length];
					 	}
					 	else if(arrRow+centerRow>arr.length-1&&arrCol+centerCol<0){
					 		a[i][j]=arr[centerRow+arrRow-arr.length][centerCol+arrCol+arr[0].length];
					 	}
					 	else if(arrCol+centerCol>arr[0].length-1&&arrRow+centerRow<0){
					 		a[i][j]=arr[centerRow+arrRow+arr.length][centerCol+arrCol-arr[0].length];
					 	}
 						else if(arrRow+centerRow>arr.length-1){
						 a[i][j]=arr[centerRow+arrRow-arr.length][arrCol+centerCol];
 						}
 						else if(arrCol+centerCol>arr[0].length-1){
						 a[i][j]=arr[arrRow+centerRow][centerCol+arrCol-arr[0].length];
 						}
 						else if(arrCol+centerCol<0){
						 a[i][j]=arr[arrRow+centerRow][centerCol+arrCol+arr[0].length];
 						}
 						else if(arrRow+centerRow<0){
						 a[i][j]=arr[centerRow+arrRow+arr.length][arrCol+centerCol];
 						}
					 else{
						 a[i][j]=arr[arrRow+centerRow][arrCol+centerCol];
					 }  
					arrCol+=1;
				 }
				 arrRow+=1;
			 }
	  
		  
		  
	if (2*radius+1>arr.length||2*radius+1>arr[0].length||2*radius+1<0) //neighborhood size is greater than the number of rows or columns
	{
		throw new IllegalArgumentException();
	}
	    	
	  }
	return a;

  }
}
