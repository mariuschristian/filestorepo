package org.kastiks.util;

import java.io.File;

import org.kastiks.exception.FatalException;
import org.kastiks.log.FileLogger;

public class FileUtils {

	private FileUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static void deleteDir(File dirToDel, FileLogger log) throws FatalException {
		File rootParentDir = dirToDel.getParentFile();
		if (rootParentDir == null) {
			String msg = "Error getting parent directory of " + dirToDel.toString() + ", something odd, aborting.";
			log.println(msg);
			throw new FatalException(msg);
		} else {
			doDeleteDir(dirToDel, rootParentDir, log);
//			log.flush();
		}
	}
	
	private static void doDeleteDir(File dirToDel, File parentRoot, FileLogger log) throws FatalException {
		
		//if the directory to delete does not exist, or we reached the top root directory, quit - recursive ending case
		if (dirToDel == null || !dirToDel.exists() || parentRoot.equals(dirToDel)) {
			return;
		} 
		
//		log.println(">>> Info: deleteDir() : " + dirToDel);
		File[] files = dirToDel.listFiles();
		if (null == files) {
			String msg = dirToDel.toString() + " not a directory or IO issue!. Possible dangerous case, aborting.";
			log.println(msg);
			throw new FatalException(msg);
		}
		
		//files is not null case
		FileDirectorySplitter filesSpitter = new FileDirectorySplitter(files);
		File[] childrenDirs = filesSpitter.getDirectories();
		
		//if "leaf" case, 0 dirs left
		if (childrenDirs.length == 0) {
			File[] leafFiles = filesSpitter.getFiles();
			for (int i=0; i<leafFiles.length; i++) {
				boolean isDeleted = leafFiles[i].delete();
				if (!isDeleted) {
					String msg = "Could not delete file: " + leafFiles[i].toString() + ", suspicious case, aborting.";
					log.println(msg);
					throw new FatalException(msg);
				} else {
					log.eventFileDeleted(leafFiles[i]);
				}
			}
			
			//stores parent file of the current dir for upwards recursive call
			File parentDir = dirToDel.getParentFile();
			if (parentDir == null) {
				String msg = "Error getting parent directory of " + dirToDel.toString() + ", something odd, aborting.";
				log.println(msg);
				throw new FatalException(msg);
			} else {
				
				//all files have been deleted successfully case. Deleting parent directory
				boolean isDirDeleted = dirToDel.delete();
				if (!isDirDeleted) {
					String msg = "Could not delete emptied directory: " + dirToDel.toString() + ", suspicious case, aborting.";
					log.println(msg);
					throw new FatalException(msg);
				} else {
					log.eventFolderDeleted(dirToDel);
				}
				doDeleteDir(parentDir, parentRoot, log);
			}
		} else {
			
			//contains dirs case
			for (int i=0; i<childrenDirs.length; i++) {
				doDeleteDir(childrenDirs[i], parentRoot, log);
			}
		}
	}
	
	/*
	 * was used for debugging. Standard API delete function replaces it. 
	 * 
	private static boolean dirsEqual(File dir1, File dir2, FileLogger log) throws FatalException {
		boolean test = false;
		if (!dir1.isDirectory() || !dir2.isDirectory()) {
			String msg = "Fatal Error: comparing two directories, one of them or both are not directories. " + 
					dir1.toString() + " and " + dir2.toString();
			log.println(msg);
			throw new FatalException(msg);
		} else {
			String absPath1 = dir1.getAbsolutePath();
			String absPath2 = dir2.getAbsolutePath();
			if (absPath1.equals(absPath2)) {
				System.out.println("Dirs equals : " + dir1.toString() + " | " + dir2.toString());
				test = true;
			}
		}
		return test;
	}
	*/

}