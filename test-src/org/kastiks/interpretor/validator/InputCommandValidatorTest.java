package org.kastiks.interpretor.validator;

import org.kastiks.interpretor.Parameter;
import org.kastiks.interpretor.Parameters;

import junit.framework.TestCase;

public class InputCommandValidatorTest extends TestCase {
	
	public void testValidate() {
		String[] args0 = new String[] {
				"--help"
				};
		Parameter[] params = InputCommandValidator.validate(args0);
		assertTrue(params.length == 1);
		assertTrue("--help".equals(params[0].getKey()));
		assertTrue(null == params [0].getValue());
		
		String[] args1 = new String[] {
				Parameters.DEST_PATH_PARAM_NAME, "/dir0/dir1",
				Parameters.SOURCE_PATH_PARAM_NAME, "/dir2:/dir3/dir4"
				};
		params = InputCommandValidator.validate(args1);
		assertTrue(params.length == 2);
		assertTrue(Parameters.DEST_PATH_PARAM_NAME.equals(params[0].getKey()));
		assertTrue("/dir0/dir1".equals(params[0].getValue()));
		assertTrue(Parameters.SOURCE_PATH_PARAM_NAME.equals(params[1].getKey()));
		assertTrue("/dir2:/dir3/dir4".equals(params[1].getValue()));
		
		String[] args2 = new String[0];
		params = InputCommandValidator.validate(args2);
		assertTrue(params.length == 0);
		
		String[] args3 = new String[] {
				"-bad"
				};
		try {
			params = InputCommandValidator.validate(args3);
			fail("InputParameterException was expected to be thrown!");
		} catch (InputParameterException e) {
			
			//Expected case
			System.out.println("Expected case: " + e.getMessage());
		}
	}

}
