package org.kastiks.interpretor;

import java.util.Iterator;
import java.util.Set;

import org.kastiks.core.path.InputPath;
import org.kastiks.exception.FatalException;

import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {

	private static final String TEST_OUTPUT_PATH_0 = "C:/eclipse/ws/FTR/test/dest_rep";
	private static final String TEST_INPUT_PATH_ELEM_0 = "C:/eclipse/ws/FTR/test/source_rep";
	private static final String TEST_INPUT_PATH_ELEM_1 = "C:/eclipse/ws/FTR/test/source_rep2";
	private static final String TEST_INPUT_PATH_0 = TEST_INPUT_PATH_ELEM_0 + "," + TEST_INPUT_PATH_ELEM_1;
	
	public void testConfigurationDefault()  {
		Parameter[] params = new Parameter[0];
		Configuration config = new Configuration(params);
		PathConfiguration pathConfig = config.getPathConfiguration();
		assertTrue(pathConfig.isEmpty());
	}
	
	public void testPathConfigurationInputPath()  {
		Parameter param0 = new Parameter(Parameters.SOURCE_PATH_PARAM_NAME, TEST_INPUT_PATH_0);
		Parameter[] params = new Parameter[1];
		params[0] = param0;
		Configuration config = new Configuration(params);
		InputPath inputPath = config.getPathConfiguration().getInputPath();
		assertTrue(TEST_INPUT_PATH_ELEM_0.equals(inputPath.getInputPath()[0].toString()));
		assertTrue(TEST_INPUT_PATH_ELEM_1.equals(inputPath.getInputPath()[1].toString()));
	}
	
	public void testPathConfigurationOutputPath()  {
		Parameter param0 = new Parameter(Parameters.DEST_PATH_PARAM_NAME, TEST_OUTPUT_PATH_0);
		Parameter[] params = new Parameter[1];
		params[0] = param0;
		Configuration config = new Configuration(params);
		String outputPath = config.getPathConfiguration().getOutputPath().toString();
		assertTrue(TEST_OUTPUT_PATH_0.equals(outputPath));
	}
	
	public void testPathConfigurationInputOutputPath()  {
		Parameter param0 = new Parameter(Parameters.SOURCE_PATH_PARAM_NAME, TEST_INPUT_PATH_0);
		Parameter param1 = new Parameter(Parameters.DEST_PATH_PARAM_NAME, TEST_OUTPUT_PATH_0);
		Parameter[] params = new Parameter[2];
		params[0] = param0;
		params[1] = param1;
		Configuration config = new Configuration(params);
		String outputPath = config.getPathConfiguration().getOutputPath().toString();
		assertTrue(TEST_OUTPUT_PATH_0.equals(outputPath));
		InputPath inputPath = config.getPathConfiguration().getInputPath();
		assertTrue(TEST_INPUT_PATH_ELEM_0.equals(inputPath.getInputPath()[0].toString()));
		assertTrue(TEST_INPUT_PATH_ELEM_1.equals(inputPath.getInputPath()[1].toString()));
	}
	
	public void testBasicConfigurationHelp()  {
		Parameter param0 = new Parameter(Parameters.HELP_PARAM_NAME);
		Parameter[] params = new Parameter[1];
		params[0] = param0;
		Configuration config = new Configuration(params);
		Set keys = config.getConfigKeys();
		Iterator it = keys.iterator();
		String key = (String) it.next();
		assertTrue(Parameters.HELP_PARAM_NAME.equals(key));
		assertTrue(null == config.getConfigValue(key));
		assertFalse(it.hasNext());
	}
	
	public void testBasicConfigurationOutput()  {
		Parameter param0 = new Parameter(Parameters.DEST_PATH_PARAM_NAME, TEST_OUTPUT_PATH_0);
		Parameter[] params = new Parameter[1];
		params[0] = param0;
		Configuration config = new Configuration(params);
		Set keys = config.getConfigKeys();
		Iterator it = keys.iterator();
		String key = (String) it.next();
		assertTrue(Parameters.DEST_PATH_PARAM_NAME.equals(key));
		assertTrue(TEST_OUTPUT_PATH_0.equals(config.getConfigValue(key)));
		assertFalse(it.hasNext());
	}
	
	public void testBasicConfigurationInput()  {
		Parameter param0 = new Parameter(Parameters.SOURCE_PATH_PARAM_NAME, TEST_INPUT_PATH_0);
		Parameter[] params = new Parameter[1];
		params[0] = param0;
		Configuration config = new Configuration(params);
		Set keys = config.getConfigKeys();
		Iterator it = keys.iterator();
		String key = (String) it.next();
		assertTrue(Parameters.SOURCE_PATH_PARAM_NAME.equals(key));
		assertTrue(TEST_INPUT_PATH_0.equals(config.getConfigValue(key)));
		assertFalse(it.hasNext());
	}
	
	public void testBasicConfigurationInputOutput()  {
		Parameter param0 = new Parameter(Parameters.SOURCE_PATH_PARAM_NAME, TEST_INPUT_PATH_0);
		Parameter param1 = new Parameter(Parameters.DEST_PATH_PARAM_NAME, TEST_OUTPUT_PATH_0);
		Parameter[] params = new Parameter[2];
		params[0] = param0;
		params[1] = param1;
		Configuration config = new Configuration(params);
		Set keys = config.getConfigKeys();
		Iterator it = keys.iterator();
		String key0 = (String) it.next();
		assertTrue(Parameters.SOURCE_PATH_PARAM_NAME.equals(key0));
		String key1 = (String) it.next();
		assertTrue(Parameters.DEST_PATH_PARAM_NAME.equals(key1));
		assertFalse(it.hasNext());
	}
	
	public void testBasicContainsHelp()  {
		Parameter param0 = new Parameter(Parameters.HELP_PARAM_NAME);
		Parameter[] params = new Parameter[1];
		params[0] = param0;
		Configuration config = new Configuration(params);
		assertTrue(config.containsHelp());
	}
	
	public void testDefaultConfiguration()  {
		Parameter[] params = new Parameter[0];
		Configuration config = new Configuration(params);
		assertTrue(config.isBlank());
	}
	
	public void testFilterHiddenFilesFlag()  {
		Parameter param0 = new Parameter(Parameters.SOURCE_PATH_PARAM_NAME, TEST_INPUT_PATH_0);
		Parameter param1 = new Parameter(Parameters.DEST_PATH_PARAM_NAME, TEST_OUTPUT_PATH_0);
		
		Parameter[] params = new Parameter[3];
		params[0] = param0;
		params[1] = param1;
		params[2] = new Parameter(Parameters.EXCLUDE_PARAM_NAME, "thumbs.db");
		Configuration config = new Configuration(params);
		assertTrue(config.isFilterHiddenFiles());
				
		Parameter param2 = new Parameter(Parameters.FILTER_HIDDEN_FILES_PARAM_NAME, "y");
		params[2] = param2;
		config = new Configuration(params);
		assertTrue(config.isFilterHiddenFiles());
		
		param2 = new Parameter(Parameters.FILTER_HIDDEN_FILES_PARAM_NAME, "N");
		params[2] = param2;
		config = new Configuration(params);
		assertFalse(config.isFilterHiddenFiles());
		
		param2 = new Parameter(Parameters.FILTER_HIDDEN_FILES_PARAM_NAME, "toto");
		params[2] = param2;
		config = new Configuration(params);
		assertTrue(config.isFilterHiddenFiles());
	}
	
	public void testFileFilter()  {
		Parameter param0 = new Parameter(Parameters.SOURCE_PATH_PARAM_NAME, TEST_INPUT_PATH_0);
		Parameter param1 = new Parameter(Parameters.DEST_PATH_PARAM_NAME, TEST_OUTPUT_PATH_0);
		
		Parameter[] params = new Parameter[3];
		params[0] = param0;
		params[1] = param1;
		params[2] = new Parameter(Parameters.EXCLUDE_PARAM_NAME, "thumbs.db");
		Configuration config = new Configuration(params);
		assertNotNull(config.getFileFilter());
	}
	
	public void testNoFileFilter()  {
		Parameter param0 = new Parameter(Parameters.SOURCE_PATH_PARAM_NAME, TEST_INPUT_PATH_0);
		Parameter param1 = new Parameter(Parameters.DEST_PATH_PARAM_NAME, TEST_OUTPUT_PATH_0);
		
		Parameter[] params = new Parameter[2];
		params[0] = param0;
		params[1] = param1;
		Configuration config = new Configuration(params);
		assertNull(config.getFileFilter());
	}

}
