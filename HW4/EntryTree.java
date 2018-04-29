package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Nathan Shull
 *
 * An entry tree class
 * Add Javadoc comments to each method
 */
public class EntryTree<K, V> {
	/**
	 * dummy root node made public for grading
	 */
	protected Node root;
	
	/**
	 * prefixlen is the largest index such that the keys in the subarray keyarr
	 * from index 0 to index prefixlen - 1 are, respectively, with the nodes on
	 * the path from the root to a node. prefixlen is computed by a private
	 * method shared by both search() and prefix() to avoid duplication of code.
	 */
	protected int prefixlen;

	protected class Node implements EntryNode<K, V> {
		protected Node child; // link to the first child node
		protected Node parent; // link to the parent node
		protected Node prev; // link to the previous sibling
		protected Node next; // link to the next sibling
		protected K key; // the key for this node
		protected V value; // the value at this node

		public Node(K aKey, V aValue) {
			key = aKey;
			value = aValue;
			child = null;
			parent = null;
			prev = null;
			next = null;
		}

		@Override
		public EntryNode<K, V> parent() {
			return parent;
		}

		@Override
		public EntryNode<K, V> child() {
			return child;
		}

		@Override
		public EntryNode<K, V> next() {
			return next;
		}

		@Override
		public EntryNode<K, V> prev() {
			return prev;
		}

		@Override
		public K key() {
			return key;
		}

		@Override
		public V value() {
			return value;
		}
	}

	/**
	 * Constructor that creates a root, initially with aKey and aValue being null.
	 */
	public EntryTree() {
		root = new Node(null, null);
	}

	/**
	 * Returns the value of the entry with a specified key sequence, or null if
	 * this tree contains no entry with the key sequence.
	 * 
	 * @param keyarr
	 * @return Value of the node at the specified key sequence
	 */
	public V search(K[] keyarr) {
		//Checks for see if keyarr is null or if the length is 0
		if((keyarr == null) || (keyarr.length == 0))
		{
			return null;
		}
		Node current = root;
		//Iterate through keyarr and search for the correct key value
		for(int i = 0; i < keyarr.length; i++)
		{
			if(keyarr[i] == null)
				throw new NullPointerException();
			current = keyOfChild(current, keyarr[i]);
			if(current == null)
				return null;
		}
		return current.value;
	}

	/**
	 * The method returns an array of type K[] with the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to a node. The length of the returned
	 * array is the length of the longest prefix.
	 * 
	 * @param keyarr
	 * @return The correct prefix of the array
	 */
	public K[] prefix(K[] keyarr) {
		//Checks to see if keyarr is null or if the length is 0
		if((keyarr == null) || (keyarr.length == 0))
		{
			return null;
		}
		prefixlen = 0;
		Node current = root;
		for(int i = 0; i < keyarr.length; i++)
		{
			if(keyarr[i] == null)
			{
				throw new NullPointerException();
			}
			current = keyOfChild(current, keyarr[i]);
			if(current == null)
			{
				break;
			}
			prefixlen += 1;
		}
		K[] answer = Arrays.copyOf(keyarr, prefixlen);
		return answer;
	}

	/**
	 * The method locates the node P corresponding to the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to the node. If the length of the
	 * prefix is equal to the length of keyarr, then the method places aValue at
	 * the node P and returns true. Otherwise, the method creates a new path of
	 * nodes (starting at a node S) labelled by the corresponding suffix for the
	 * prefix, connects the prefix path and suffix path together by making the
	 * node S a child of the node P, and returns true.
	 * 
	 * @param keyarr The key array to go through when inserting the new node and updating with aValue
	 * @param aValue The value at which to insert the new node
	 * @return False if aValue/keyarr is null, or the keyarr has a length of 0. True
	 * 			if the addition of a node with aValue is successful.
	 */
	public boolean add(K[] keyarr, V aValue) {
		//Checks to see if aValue or keyarr is null, or if the length of keyarr is 0
		if(aValue == null || keyarr.length == 0 || keyarr == null)
		{
			return false;
		}
		Node current = root;
		//Iterate through keyarr, moving down a level, and finding a child with the given key.
		//Creates a new child if no child with the key exists.
		for(int i = 0; i < keyarr.length; i++)
		{
			if(keyarr[i] == null)
			{
				throw new NullPointerException();
			}
			Node childOfNode = keyOfChild(current, keyarr[i]);
			if(childOfNode == null)
			{
				childOfNode = new Node(keyarr[i], null);
				childOfNode.parent = current;
				if(current.child == null)
				{
					childOfNode.parent.child = childOfNode;
				}
				else
				{
					Node currentChild = current.child;
					while(currentChild.next != null)
					{
						currentChild = currentChild.next;
					}
					currentChild.next = childOfNode;
					childOfNode.prev = currentChild;
				}
			}
			current = childOfNode;
		}
		current.value = aValue;
		return true;
	}

	/**
	 * Removes the entry for a key sequence from this tree and returns its value
	 * if it is present. Otherwise, it makes no change to the tree and returns
	 * null.
	 * 
	 * @param keyarr The keyarr to remove
	 * @return Returns null if keyarr is null or has a length of 0. Also returns null if there is not a valid path. If valid, returns the value at keyarr.
	 */
	public V remove(K[] keyarr) {
		if((keyarr == null) || (keyarr.length == 0))
		{
			return null;
		}
		Node current = root;
		for(int i = 0; i < keyarr.length; i++)
		{
			if(keyarr[i] == null)
			{
				throw new NullPointerException();
			}
			current = keyOfChild(current, keyarr[i]);
			if(current == null)
			{
				return null;
			}
		}
		V answer = current.value;
		current.value = null;
		removeNodes(current);
		return answer;
	}

	/**
	 * The method prints the tree on the console in the output format shown in
	 * an example output file.
	 */
	public void showTree() {
		Node current = root;
		printTree(current, 0);
	}

	/**
	 * This private helper method takes a Node and a key value. It returns the
	 * child of the Node that is equal to the given key value.
	 * @param current The parent node of the current child
	 * @param key The key to search for in the children Node's
	 * @return Return null if the node has no child, or if there is no child with the given key value. Otherwise returns the child node with the correcy key.
	 */
	private Node keyOfChild(Node current, K key)
	{
		if(current.child == null)
		{
			return null;
		}
		current = current.child;
		while((current != null) && !current.key.equals(key))
		{
			current = current.next;
		}
		return current;
	}
	
	/**
	 * Private helper method to remove Nodes. First checks to see if a node needs to be deleted, which
	 * would be if it has no children or a null value. If yes, it removes all links and also checks if the parent needs to be deleted.
	 * @param current The node being evaluated for deletion.
	 */
	private void removeNodes(Node current)
	{
		if(current == null)
		{
			return;
		}
		if(current.value == null && current.child == null)
		{
			if(current.parent.child.equals(current) && current.parent != null)
			{
				current.parent.child = current.next;
				removeNodes(current.parent);
			}
			if(current.prev != null)
			{
				current.prev.next = current.next;
			}
			if(current.next != null)
			{
				current.next.prev = current.prev;
			}
		}
	}
	
	/**
	 * This private helper method prints all the children of the given node, current.
	 * @param current The node to start printing at
	 * @param height The number of levels below the tree
	 */
	private void printTree(Node current, int height)
	{
		if(current == null)
		{
			return;
		}
		while(current != null)
		{
			for(int i = 0; i < height; i++)
			{
				System.out.print("\t");
			}
			System.out.print(current.key + "->" + current.value + "\n");
			printTree(current.child, height + 1);
			current = current.next;
		}
	}
	
	
}
