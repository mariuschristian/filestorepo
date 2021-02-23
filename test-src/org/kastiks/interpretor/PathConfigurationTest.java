package org.kastiks.interpretor;

import org.kastiks.core.path.InputPath;
import org.kastiks.core.path.PathElement;
import org.kastiks.exception.EmptyInputPathException;
import org.kastiks.exception.EmptyOutputPathException;
import org.kastiks.exception.EmptyPathException;
import org.kastiks.exception.InvalidInputPathException;
import org.kastiks.exception.InvalidOutputPathException;
import org.kastiks.exception.NoDirectoryException;

import junit.framework.TestCase;

public class PathConfigurationTest extends TestCase {
	
	private static final String TEST_OUTPUT_PATH_0 = "/home/alfred/output";
	private static final String TEST_OUTPUT_PATH_1 = "/home/alfred/tmp/output";
	private static final String TEST_FILE_PATH_0 = "/home/alfred/tmp/dummyFile";
	private static final String TEST_INPUT_PATH_ELEM_0 = "/home/alfred/input0";
	private static final String TEST_INPUT_PATH_ELEM_1 = "/home/alfred/input1";
	private static final String TEST_INPUT_PATH_ELEM_2 = "/home/alfred/tmp/input0";
	private static final String TEST_INPUT_PATH_0 = TEST_INPUT_PATH_ELEM_0 + ";" + TEST_INPUT_PATH_ELEM_1;	
	
	public void testIsEmpty() {
		PathConfiguration pathConfig = new PathConfiguration();
		assertTrue(pathConfig.isEmpty());
		
		pathConfig.setInputPath(new InputPath(new PathElement[]{}));
		assertTrue(pathConfig.isEmpty());
		
		pathConfig.setOutputPath(new PathElement(""));
		assertTrue(pathConfig.isEmpty());
		
		pathConfig.setInputPath(new InputPath(new PathElement[]{new PathElement("")}));
		assertTrue(pathConfig.isEmpty());
		
		pathConfig.setOutputPath(new PathElement("TEST_OUTPUT_PATH_0"));
		assertFalse(pathConfig.isEmpty());
		
		pathConfig.setOutputPath(new PathElement(""));
		pathConfig.setInputPath(new InputPath(new PathElement[]{new PathElement(""), new PathElement(TEST_INPUT_PATH_ELEM_0)}));
		assertFalse(pathConfig.isEmpty());
	}
	
	public void testValidate() {
		PathConfiguration pathConfig = new PathConfiguration();
		try {
			pathConfig.validate();
			fail("EmptyPathException should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyPathException);
		}
		
		pathConfig = new PathConfiguration();
		pathConfig.setInputPath(null);
		try {
			pathConfig.validate();
			fail("EmptyPathException should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyPathException);
		}
		
		pathConfig = new PathConfiguration();
		pathConfig.setOutputPath(null);
		try {
			pathConfig.validate();
			fail("EmptyPathException should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyPathException);
		}
		
		pathConfig = new PathConfiguration();
		pathConfig.setOutputPath(new PathElement(TEST_OUTPUT_PATH_0));
		pathConfig.setInputPath(new InputPath(new PathElement[]{new PathElement(TEST_INPUT_PATH_ELEM_0)}));
		try {
			pathConfig.validate();
			fail("InvalidOutputPathException should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof InvalidOutputPathException);
		}
		
		pathConfig = new PathConfiguration();
		pathConfig.setOutputPath(new PathElement(""));
		pathConfig.setInputPath(new InputPath(new PathElement[]{new PathElement(TEST_INPUT_PATH_ELEM_0)}));
		try {
			pathConfig.validate();
			fail("EmptyOutputPathException should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyOutputPathException);
		}
		
		pathConfig = new PathConfiguration();
		pathConfig.setOutputPath(new PathElement(TEST_FILE_PATH_0));
		pathConfig.setInputPath(new InputPath(new PathElement[]{new PathElement(TEST_INPUT_PATH_ELEM_0)}));
		try {
			pathConfig.validate();
			fail("NoDirectoryException should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof NoDirectoryException);
		}
		
		pathConfig = new PathConfiguration();
		pathConfig.setOutputPath(new PathElement(TEST_INPUT_PATH_ELEM_2));
		pathConfig.setInputPath(new InputPath(new PathElement[]{new PathElement(TEST_INPUT_PATH_ELEM_0)}));
		try {
			pathConfig.validate();
			fail("InvalidInputPathException should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputPathException);
		}
		
		pathConfig = new PathConfiguration();
		pathConfig.setOutputPath(new PathElement(TEST_INPUT_PATH_ELEM_2));
		pathConfig.setInputPath(new InputPath(new PathElement[]{new PathElement(TEST_FILE_PATH_0)}));
		try {
			pathConfig.validate();
			fail("InvalidInputPathException should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof InvalidInputPathException);
		}
		
		pathConfig = new PathConfiguration();
		pathConfig.setOutputPath(new PathElement(TEST_INPUT_PATH_ELEM_2));
		pathConfig.setInputPath(new InputPath(new PathElement[]{new PathElement("")}));
		try {
			pathConfig.validate();
			fail("EmptyInputPathException should have been thrown!");
		} catch (Exception e) {
			assertTrue(e instanceof EmptyInputPathException);
		}
		
		pathConfig = new PathConfiguration();
		pathConfig.setOutputPath(new PathElement(TEST_OUTPUT_PATH_1));
		pathConfig.setInputPath(new InputPath(new PathElement[]{new PathElement(TEST_INPUT_PATH_ELEM_2)}));
		try {
			pathConfig.validate();
		} catch (Exception e) {
			fail("pathConfig should have been valid! Surprising Exception: " + e.getMessage());
		}
	}

}
