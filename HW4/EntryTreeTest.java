package edu.iastate.cs228.hw4;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Nathan Shull
 *
 */
public class EntryTreeTest {
	private PrintStream old;
	protected ByteArrayOutputStream out;

	@Before
	public void printingStreams() {
		out = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(out);
		old = System.out;
		System.setOut(stream);
	}

	@After
	public void streamReassingment() {
		System.setOut(old);
	}

	EntryTree<Integer, String> tree = new EntryTree<Integer, String>();

	@Test
	public void creation() {
		assertEquals(tree.root.key(), null);
		assertEquals(tree.root.value(), null);
		assertEquals(tree.root.parent(), null);
		assertEquals(tree.root.child(), null);
		assertEquals(tree.root.next(), null);
		assertEquals(tree.root.prev(), null);
	}

	@Test
	public void addChild() {
		Integer[] path = new Integer[1];
		path[0] = (Integer) 1;
		tree.add(path, "Testing");

		assertEquals(tree.root.key(), null);
		assertEquals(tree.root.value(), null);
		assertEquals(tree.root.parent(), null);
		assertNotEquals(tree.root.child(), null);
		assertEquals(tree.root.next(), null);
		assertEquals(tree.root.prev(), null);
		assertEquals(tree.root.child().key(), (Integer) 1);
		assertEquals(tree.root.child().value(), "Testing");
		assertEquals(tree.root.child().next(), null);
		assertEquals(tree.root.child().prev(), null);
		assertEquals(tree.root.child().child(), null);
		assertEquals(tree.root.child().parent(), tree.root);

	}

	@Test
	public void addSibling() {
		Integer[] path = new Integer[1];
		path[0] = 1;
		tree.add(path, "Testing");
		path[0] = 2;
		tree.add(path, "Dat boi");
		assertEquals(tree.root.key(), null);
		assertEquals(tree.root.value(), null);
		assertEquals(tree.root.parent(), null);
		assertNotEquals(tree.root.child(), null);
		assertEquals(tree.root.next(), null);
		assertEquals(tree.root.prev(), null);
		assertEquals(tree.root.child().key(), (Integer) 1);
		assertEquals(tree.root.child().value(), "Testing");
		assertNotEquals(tree.root.child().next(), null);
		assertEquals(tree.root.child().prev(), null);
		assertEquals(tree.root.child().child(), null);
		assertEquals(tree.root.child().parent(), tree.root);
		assertEquals(tree.root.child().next().key(), (Integer) 2);
		assertEquals(tree.root.child().next().value(), "Dat boi");
		assertEquals(tree.root.child().next().prev(), tree.root.child());
		assertEquals(tree.root.child().next().child(), null);
		assertEquals(tree.root.child().next().parent(), tree.root);

	}

	// Test printing
	@Test
	public void testPrint() {
		tree.showTree();
		System.out.flush();
		assertEquals("null->null\n", out.toString());
	}

	// Test printing after add
	@Test
	public void testPrintAdd() {
		Integer[] path = new Integer[1];
		path[0] = (Integer) 1;
		tree.add(path, "Testing");
		tree.showTree();
		System.out.flush();
		assertEquals("null->null\n 1->Testing\n", out.toString());
	}

	// Test Remove
	@Test
	public void testPrintRemove() {
		Integer[] path = new Integer[1];
		path[0] = (Integer) 1;
		Integer[] path2 = { 1, 2, 3 };
		tree.add(path, "Testing");
		tree.add(path2, "REMOVAL TEST");
		tree.remove(path2);
		tree.showTree();
		System.out.flush();
		assertEquals("null->null\n 1->Testing\n", out.toString());
	}

	// Test Prefix
	@SuppressWarnings("deprecation")
	@Test
	public void testPrefix() {
		Integer[] path1 = { 1, 2, 4 };
		Integer[] path2 = { 1, 2, 3 };
		Integer[] pathprefix = { 1, 2 };
		tree.add(path1, "Tesing");
		assertEquals(pathprefix, tree.prefix(path2));
	}

	// Test Search
	@Test
	public void testSearch() {
		Integer[] path1 = { 1, 2, 4 };
		Integer[] path2 = { 1, 2, 3 };
		tree.add(path1, "Testing");
		assertEquals("Testing", tree.search(path1));
		assertEquals(null, tree.search(path2));
	}
}
