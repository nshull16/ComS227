package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

/**
 * @author Nathan Shull
 * 
 *         An application class
 */
public class Dictionary {
	public static void main(String[] args) {
		EntryTree<Character, String> et = new EntryTree<Character,String>();
		FileReader input = null;
		try
		{
			input = new FileReader(args[0]);
		}
		catch(FileNotFoundException e)
		{
			String current;
			try
			{
				current = new File(".").getCanonicalPath();
				System.out.println("Correct location: " + current);
			}
			catch(IOException i)
			{
				
			}
			System.exit(-1);
		}
		System.out.println("Notes on the output format of an entry tree: ");
		System.out.println("A node with Character o (key) and String Larry (value) is shown in the form: o->Larry");
		System.out.println("A child node is shown with right indentation below its parent node.");
		System.out.println("The children of a node are shown at the same level of indentation. \n");
		Scanner scan = new Scanner(input);
		while(scan.hasNextLine())
		{
			String nextLine;
			nextLine = scan.nextLine();
			String[] sArr = nextLine.split("\\s+");
			if(sArr[0].equalsIgnoreCase("Search"))
			{
				System.out.println("Command: " + sArr[0] + " " + sArr[1]);
				System.out.println("Result from a search: " + et.search(charArray(sArr[1])));
			}
			if(sArr[0].equalsIgnoreCase("Prefix"))
			{
				System.out.println("Command: " + sArr[0] + " " + sArr[1]);
				System.out.println("Result from a prefix: ");
				Character[] chararray = et.prefix(charArray(sArr[1]));
				for(int i = 0; i < chararray.length; i++)
				{
					System.out.print(chararray[i]);
				}
				System.out.println("");
			}
			if(sArr[0].equalsIgnoreCase("Add"))
			{
				System.out.println("Command: " + sArr[0] + " " + sArr[1] + " " + sArr[2]);
				System.out.println("Result from an add: " + et.add(charArray(sArr[1]), sArr[2]));
			}
			if(sArr[0].equalsIgnoreCase("Remove"))
			{
				System.out.println("Command: " + sArr[0] + " " + sArr[1]);
				System.out.println("Result from a remove: " + et.remove(charArray(sArr[1])));
			}
			if(sArr[0].equalsIgnoreCase("ShowTree"))
			{
				System.out.println("Command: " + sArr[0] + "\n");
				System.out.println("Result from a showTree");
				et.showTree();
			}
			System.out.println("");
		}
	}
	
	private static Character[] charArray(String s)
	{
		if(s == null)
		{
			return null;
		}
		Character[] arr = new Character[s.length()];
		for(int i = 0; i < s.length(); i++)
		{
			arr[i] = new Character(s.charAt(i));
		}
		return arr;
	}
}
