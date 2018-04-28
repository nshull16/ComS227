package hw3;
import java.io.*;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import api.Cell;
import api.Flow;

/**
 * Utility class with methods for creating Flows from string descriptors
 * and reading string descriptors from a file.  A string descriptor is
 * an array of strings, all with the same length, in which an alphabetic
 * character at a given row and column represents a cell at that
 * row and column.  The color of the cell is determined from the character
 * by the Cell constructor.  A descriptor is invalid if not all strings
 * are the same length or if there is an alphabetic character that 
 * does not appear exactly twice.
 */
public class Util
{
  /**
   * Creates an array of Flow objects based on the string descriptor.
   * If the descriptor is invalid, this method returns null.
   * @param descriptor
   *   array of strings
   * @return
   *   array of Flow objects determined by the descriptor 
   */
  public static Flow[] createFlowsFromStringArray(String[] descriptor)
  {
	  ArrayList<Cell> c = new ArrayList<>(0);
	  for(int i = 0; i<descriptor.length; i++)
		  for(int j = 0; j<descriptor[i].length(); j++)
			if(descriptor[i].charAt(j) != '-')
				c.add(new Cell(i, j, descriptor[i].charAt(j)));
	  Flow[] f = new Flow[c.size()/2];
	  for(int i = 0; i<f.length;i++)
	  {
		  Cell c1 = c.get(0);
		  for(int j = 1; j<c.size(); j++)
			  if(c1.colorMatches(c.get(j).getColor()))
			  {
				  f[i] = new Flow(c1, c.get(j)); 
		  			c.remove(j);
		  			c.remove(0);
		  			break;
			  }
		  			
	  }
	  return f;
  }
 
  /**
   * Reads the given file and constructs a list of FlowGame objects, one for
   * each descriptor in the file.  Descriptors in the file are separated by 
   * some amount of whitespace, but the file need not end with whitespace and
   * may have extra whitespace at the beginning.  Invalid descriptors in the file
   * are ignored, so the method may return an empty list.
   * @param filename
   *   name of the file to read
   * @return
   *   list of FlowGame objects created from the valid descriptors in the file
   * @throws FileNotFoundException
   */
  public static ArrayList<FlowGame> readFile(String filename) throws FileNotFoundException
  {
    Scanner r = new Scanner(new File(filename));
    ArrayList<FlowGame> games = new ArrayList<>(0);
    while(r.hasNext())
    {
    	String s;
    	ArrayList<String> board = new ArrayList<>(0);
    	while(!(s = r.next()).trim().isEmpty() && r.hasNext())
    		board.add(s);
    	String[] b = new String[board.size()];
    	for(int i = 0; i < board.size(); i++)
    		b[i] = board.get(i);
    	for(int i = 0; i<b.length; i++)
    		System.out.println(b[i]);
    	games.add(new FlowGame(b));
    		
    }
    return games;
    
  }

}
