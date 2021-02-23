package org.kastiks.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.kastiks.interpretor.Parameters;
import org.kastiks.log.FileLogger;
import org.kastiks.util.TestUtils;

import junit.framework.TestCase;

public class CoreTest extends TestCase {
	
	private static String test_output_path_0 ;
	private static String test_output_path_1;
	private static String test_output_path_err;
	private static String test_file_path_0;
	private static String test_input_path_elem_0;
	private static String test_input_path_elem_1;
	private static String test_input_path_elem_2;
	private static String test_input_path_elem_3;
	private static String test_input_path_0;
	private static String test_input_path_1;
	
	private static String test_prune_master_path;
	private static String test_prune_slave_path;
		
	private static String pruneTestDirName;
	private static String mergeTestDirName;
	
	private static final String REPO_NAME_0 = "repo_0";
	private static final String REPO_NAME_1 = "repo_1";
					
	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("test.properties"));
			pruneTestDirName = prop.getProperty("test.prune.dir");
			mergeTestDirName = prop.getProperty("test.merge.dir");
			test_output_path_0 = prop.getProperty("test.merge.dir.dest");
			test_output_path_err = prop.getProperty("test.merge.dir.desterr");
			test_output_path_1 = mergeTestDirName + "/" + "dest1";
			test_file_path_0 = mergeTestDirName + "/" + "dummyFile";
			test_input_path_elem_0 = prop.getProperty("test.merge.dir.src");
			test_input_path_elem_1 = mergeTestDirName + "/source-dir0/" + REPO_NAME_1;
			test_input_path_elem_2 = mergeTestDirName + "/source-dir1/" + REPO_NAME_0;
//			test_input_path_elem_3 = mergeTestDirName + "/" + "source-dir-root1";
			test_input_path_0 = test_input_path_elem_0 + "," + test_input_path_elem_1;
			test_input_path_1 = test_input_path_elem_0 + "," + test_input_path_elem_2;
			
			test_prune_master_path = pruneTestDirName + "/" + REPO_NAME_0;
			test_prune_slave_path = pruneTestDirName + "/slave/" + REPO_NAME_0;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testMainHelp0() {
		String[] args = {
				Parameters.HELP_PARAM_NAME
		};
		
		// Help display is expected
		System.out.println("testMainHelp0(): ");
		Core.main(args);
	}
	
	public void testMainHelp1() {
		String[] args = new String[] {
				Parameters.SOURCE_PATH_PARAM_NAME, test_input_path_0,
				Parameters.DEST_PATH_PARAM_NAME, test_output_path_0,
				Parameters.VERSION_PARAM_NAME,
				Parameters.HELP_PARAM_NAME
		};
		
		// Help display is expected
		System.out.println();
		System.out.println("testMainHelp1(): ");
		Core.main(args);
	}
	
	public void testMainHelp2() {
		String[] args = {
				"--hlep"
		};
		
		// Error + Help display is expected
		System.out.println();
		System.out.println("testMainHelp2(): ");
		Core.main(args);
	}
	
	public void testMainHelp3() {
		String[] args = new String[] {};
		
		// Help display is expected
		System.out.println();
		System.out.println("testMainHelp3(): ");
		Core.main(args);
	}
	
	public void testMainNoInputPath() {
		String[] args = new String[] {
				Parameters.DEST_PATH_PARAM_NAME, test_output_path_0
		};
		
		// Info message expected
		System.out.println();
		System.out.println("testMainNoInputPath(): ");
		Core.main(args);
	}
	
	public void testMainNoOutputPath() {
		String[] args = new String[] {
				Parameters.SOURCE_PATH_PARAM_NAME, test_input_path_0,
		};
		
		// Error message expected
		System.out.println();
		System.out.println("testMainNoOutputPath(): ");
		Core.main(args);
	}
	
	public void testMainVersion0() {
		String[] args = new String[] {
				test_output_path_0,
				Parameters.VERSION_PARAM_NAME
		};
		
		// Help message expected
		System.out.println();
		System.out.println("testMainVersion0(): ");
		Core.main(args);
	}
	
	public void testMainVersion1() {
		String[] args = new String[] {
				Parameters.SOURCE_PATH_PARAM_NAME, test_input_path_0,
				Parameters.DEST_PATH_PARAM_NAME, test_output_path_0,
				Parameters.VERSION_PARAM_NAME
		};
		
		// Only version message expected
		System.out.println();
		System.out.println("testMainVersion1(): ");
		Core.main(args);
	}
	
	public void testMerge0() throws Exception {
		String[] args = new String[] {
				Parameters.MERGE_OPTION,
				Parameters.SOURCE_PATH_PARAM_NAME, test_input_path_elem_0,
				Parameters.DEST_PATH_PARAM_NAME, test_output_path_0
		};
		// No error message expected
		System.out.println();
		System.out.println("testMerge0(): ");
		Core.main(args);
	}
	
	public void testMerge1() throws Exception {
		String[] args = new String[] {
				Parameters.MERGE_OPTION,
				Parameters.SOURCE_PATH_PARAM_NAME, test_input_path_1,
				Parameters.DEST_PATH_PARAM_NAME, test_output_path_0
		};
		
		// No error message expected
		System.out.println();
		System.out.println("testMerge1(): ");
		Core.main(args);
	}
	
	public void testMerge2() throws Exception {
		String[] args = new String[] {
				Parameters.MERGE_OPTION,
				Parameters.SOURCE_PATH_PARAM_NAME, test_input_path_elem_0,
				Parameters.DEST_PATH_PARAM_NAME, test_output_path_err
		};
		System.out.println();
		System.out.println("testMerge2(): ");
		
		// InvalidPathException
		Core.main(args);
	}
	
	public void testPrune0() throws Exception {
		String[] args = new String[] {
				Parameters.PRUNE_OPTION,
				Parameters.PRUNE_MASTER_PATH_PARAM_NAME, test_prune_master_path,
				Parameters.PRUNE_SLAVE_PATH_PARAM_NAME, test_prune_slave_path
		};
		TestUtils.clean(test_prune_master_path, test_prune_slave_path);
		TestUtils.createMasterStruct(test_prune_master_path);
		TestUtils.createSlaveStruct(test_prune_slave_path);
		
		// No error message expected
		System.out.println();
		System.out.println("testPrune0(): ");
		Core.main(args);
		System.out.println("prune() done. Expected final file structure in slaveRoot:");
		String expected = "folder1/file11 \n" +
				"folder11 deleted \n" +
				"folder2 deleted \n" +
				"folder3/file31.bin \n" +
				"folder3/folder31 empty";
		System.out.println(expected);
	}
	
	public void testArtMerge0() throws Exception {
		String[] args = new String[] {
				Parameters.MERGE_OPTION,
				Parameters.SOURCE_PATH_PARAM_NAME, test_input_path_1,
				Parameters.DEST_PATH_PARAM_NAME, test_output_path_0,
				Parameters.ART_PARAM_NAME
		};
		
		// ASCII Art expected in console
		Core.main(args);
	}
	
	public void testArtPrune0() throws Exception {
		String[] args = new String[] {
				Parameters.PRUNE_OPTION,
				Parameters.PRUNE_MASTER_PATH_PARAM_NAME, test_prune_master_path,
				Parameters.PRUNE_SLAVE_PATH_PARAM_NAME, test_prune_slave_path,
				Parameters.ART_PARAM_NAME
		};
		TestUtils.clean(test_prune_master_path, test_prune_slave_path);
		TestUtils.createMasterStruct(test_prune_master_path);
		TestUtils.createSlaveStruct(test_prune_slave_path);
		
		// No error message expected
		System.out.println();
		System.out.println("testPrune0(): ");
		Core.main(args);
		System.out.println("prune() done. Expected final file structure in slaveRoot:");
		String expected = "folder1/file11 \n" +
				"folder11 deleted \n" +
				"folder2 deleted \n" +
				"folder3/file31.bin \n" +
				"folder3/folder31 empty";
		System.out.println(expected);
	}
	
	public void testArtOnly0() throws Exception {
		String[] args = new String[] {
				Parameters.ART_PARAM_NAME
		};
		
		// ASCII Art expected in console
		Core.main(args);
	}
}
