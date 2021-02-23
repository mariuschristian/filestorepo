package org.kastiks.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;

public class TestUtils {

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
	}
	
	public static void clean(String masterRootFilePath, String slaveRootFilePath) throws Exception {
		
		//delete existing file structure
		
		File slaveRootFile = new File (slaveRootFilePath);
		File masterRootFile = new File (masterRootFilePath);
		org.apache.commons.io.FileUtils.deleteDirectory(slaveRootFile);
		org.apache.commons.io.FileUtils.deleteDirectory(masterRootFile);

	}
	
	public static void createMasterStruct(String masterRootFilePath) throws Exception {
		File folder1 = new File (masterRootFilePath + "/" + "folder1");
		folder1.mkdirs();
		String folder1Path = folder1.getAbsolutePath();
		File file11 = new File (folder1Path + "/" + "file11");
		file11.createNewFile();
		
		//folder2 nu e
		
		File folder3 = new File (masterRootFilePath + "/" + "folder3");
		folder3.mkdirs();
		String folder3Path = folder3.getAbsolutePath();
		File file31 = new File (folder3Path + "/" + "file31.bin");
		file31.createNewFile();
		String folder31Path = folder3Path + "/" + "folder31"; 
		File folder31 = new File(folder31Path);
		folder31.mkdirs();
	}

	public static void createSlaveStruct(String slaveRootFilePath) throws Exception {
		File slaveFolder1 = new File (slaveRootFilePath + "/" + "folder1");
		slaveFolder1.mkdirs();
		File slaveFolder2 = new File (slaveRootFilePath + "/" + "folder2");
		slaveFolder2.mkdirs();
		File slaveFolder3 = new File (slaveRootFilePath + "/" + "folder3");
		slaveFolder3.mkdirs();
		String slaveFolder1Path = slaveFolder1.getAbsolutePath();
		File slaveFile11 = new File (slaveFolder1Path + "/" + "file11");
		slaveFile11.createNewFile();
		File slaveFile12 = new File (slaveFolder1Path + "/" + "file12.txt");
		slaveFile12.createNewFile();
		File slaveFile13 = new File (slaveFolder1Path + "/" + "file13.txt");
		slaveFile13.createNewFile();
		String slaveFolder2Path = slaveFolder2.getAbsolutePath();
		File slaveFile21 = new File (slaveFolder2Path + "/" + "file21");
		slaveFile21.createNewFile();
		String slaveFolder3Path = slaveFolder3.getAbsolutePath();
		File slaveFile31 = new File (slaveFolder3Path + "/" + "file31.bin");
		RandomAccessFile randF = null;
		try {
			randF = new RandomAccessFile(slaveFile31, "rw");
			randF.setLength(1024 * 1024);
		} finally {
			randF.close();
		}
		String slaveFolder11PathString = slaveFolder1Path + "/" + "folder11";
		File slaveFolder11 = new File (slaveFolder11PathString);
		slaveFolder11.mkdirs();
		File slaveFile111 = new File (slaveFolder11PathString + "/" + "file111");
		slaveFile111.createNewFile();
		String slaveFolder31Path = slaveFolder3Path + "/" + "folder31"; 
		File slaveFolder31 = new File(slaveFolder31Path);
		slaveFolder31.mkdirs();
		File slaveFile311 = new File (slaveFolder31Path + "/" + "file311");
		slaveFile311.createNewFile();
	}
	
}
