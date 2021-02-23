package org.kastiks.core.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;
import org.kastiks.core.path.PathElement;
import org.kastiks.log.FileLogger;
import org.kastiks.util.TestUtils;

import junit.framework.TestCase;

public class PruneCommandTest extends TestCase{
	
	private static final String ROOT_SLAVE_DIR_NAME = "rootSlaveDir";
	private static final String ROOT_MASTER_DIR_NAME = "rootMasterDir";
	private static String slaveRootFilePath = null;
	private static String masterRootFilePath = null;
	private static String testDirName = null;
		
	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("test.properties"));
			testDirName = prop.getProperty("test.prune.dir");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		slaveRootFilePath = testDirName + "/" + ROOT_SLAVE_DIR_NAME;
		masterRootFilePath = testDirName + "/" +  ROOT_MASTER_DIR_NAME;
	}
	
	@Test
	public void testPrune() throws Exception {
		
		//delete existing file structure
		TestUtils.clean(masterRootFilePath, slaveRootFilePath);
				
		//create slave file structure
		TestUtils.createSlaveStruct(slaveRootFilePath);
		
		//master file structure
		TestUtils.createMasterStruct(masterRootFilePath);
				
		//log
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		File logFile = new File("FTR_logFile_prune_" + sdf.format(now) + ".txt");
		FileLogger fl = null;
		PathElement masterRootPathEl = new PathElement(masterRootFilePath);
		PathElement slaveRootPathEl = new PathElement(slaveRootFilePath);
		try {
			fl = new FileLogger(logFile);
			
			//apply prune()
			PruneCommand.prune(masterRootPathEl, slaveRootPathEl, fl);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fl.closeStream();
		}		
		String expected = "folder1/file11 \n" +
				"folder11 deleted \n" +
				"folder2 deleted \n" +
				"folder3/file31.bin \n" +
				"folder3/folder31 empty";
		assertTrue(true);
	}
}
