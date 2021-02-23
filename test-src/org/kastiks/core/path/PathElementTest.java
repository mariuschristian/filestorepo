package org.kastiks.core.path;

import org.kastiks.exception.EmptyPathException;
import org.kastiks.exception.InvalidPathException;
import org.kastiks.exception.NoDirectoryException;

import junit.framework.TestCase;

public class PathElementTest extends TestCase {
	
	private static final String TEST_OUTPUT_PATH_0 = "/home/alfred/output";
	private static final String TEST_OUTPUT_PATH_1 = "/home/alfred/tmp/output";
	private static final String TEST_OUTPUT_PATH_2 = "/home/alfred/tmp/dummyFile";
	private static final String TEST_INPUT_PATH_ELEM_0 = "/home/alfred/input0";
	private static final String TEST_INPUT_PATH_ELEM_1 = "/home/alfred/input1";
	private static final String TEST_INPUT_PATH_ELEM_2 = "/home/alfred/tmp/input0";
	private static final String TEST_INPUT_PATH_ELEM_3 = "/home/alfred/tmp/input1";
	private static final String TEST_INPUT_PATH_0 = TEST_INPUT_PATH_ELEM_0 + ";" + TEST_INPUT_PATH_ELEM_1;
	private static final String TEST_INPUT_PATH_1 = TEST_INPUT_PATH_ELEM_2 + ";" + TEST_INPUT_PATH_ELEM_3;
	
	public void testValidate() {
		PathElement pathEl = new PathElement("");
		try {
			pathEl.validate();
			fail("Exception should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyPathException);
			System.out.println(e.getMessage());
		}
		
		pathEl = new PathElement(TEST_OUTPUT_PATH_0);
		try {
			pathEl.validate();
			fail("Exception should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof InvalidPathException);
			System.out.println(e.getMessage());
		}
		
		pathEl = new PathElement(TEST_OUTPUT_PATH_2);
		try {
			pathEl.validate();
			fail("Exception should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof NoDirectoryException);
			System.out.println(e.getMessage());
		}
		
		pathEl = new PathElement(TEST_INPUT_PATH_ELEM_3);
		try {
			pathEl.validate();
		} catch (Exception e) {
			fail("Exception is not expected! : " + e.getMessage());
		}
	}

}
