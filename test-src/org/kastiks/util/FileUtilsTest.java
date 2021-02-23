package org.kastiks.util;

import java.io.File;
import java.io.IOException;

import org.kastiks.log.FileLogger;

import junit.framework.TestCase;

public class FileUtilsTest extends TestCase {
	
	private static final String ROOT_DIR = "/media/m/P-BOOT/filesRoot";
	private static final String DIR_1 = "dir1";
	private static final String DIR_2 = "dir2";
	private static final String DIR_3 = "dir3";
	private static final String ROOT_DIR2 = "/media/m/P-BOOT/slave/repo_0";
	
	private FileLogger log = null;

	public FileUtilsTest() {
	}

	public FileUtilsTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	protected void setUp() {
		try {
			log = new FileLogger(new File("log_FileUtilsTest.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testDeleteDir1() throws Exception {
		String dirPath = ROOT_DIR + "/" + DIR_1;
		FileUtils.deleteDir(new File(dirPath), log);
	}
	
	public void testDeleteDir2() throws Exception {
		String dirPath = ROOT_DIR + "/" + DIR_2;
		FileUtils.deleteDir(new File(dirPath), log);
	}
	
	public void testDeleteDir3() throws Exception {
		String dirPath = ROOT_DIR + "/" + DIR_3;
		FileUtils.deleteDir(new File(dirPath), log);
	}
	
	public void testDeleteDir4() throws Exception {
		String dirPath = ROOT_DIR2;
		FileUtils.deleteDir(new File(dirPath), log);
	}
}