package org.kastiks.core.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kastiks.core.path.PathElement;
import org.kastiks.exception.FatalException;
import org.kastiks.interpretor.FileFilter;
import org.kastiks.log.FileLogger;
import org.kastiks.util.FileDirectorySplitter;

public class MergeCommand {
	
	private static final int DEFAULT_COPY_BUFFER_LENGTH = 524288;
	
	public static void mergeDirectory(PathElement srcDir, PathElement destDir,
			FileFilter ffilter, boolean filterHiddenFiles, FileLogger fileLogger) throws FatalException {
		File srcDirFile = new File(srcDir.toString());
		File destDirFile = new File(destDir.toString());
		File destDirParent = destDirFile.getParentFile();
		doMergeDirectory(srcDirFile, destDirParent, ffilter, filterHiddenFiles, fileLogger);
	}
	
	private static void doMergeDirectory(File srcDir, File destDir, FileFilter ffilter, boolean filterHiddenFiles, FileLogger fileLogger)
			throws FatalException {
		String srcDirName = srcDir.getName();		
		File newDestDir = new File(destDir.getAbsolutePath() + "/" + srcDirName);

		// tries to create a new directory: if it already exists, it does nothing
		if (newDestDir.mkdir()) {
			fileLogger.eventFolderCreated(newDestDir);
			
			// setting "last modified" date is useless on folders, it does not work; it seems it is auto updated by system
			// when underlying files were added 
//			long lastModified = from.lastModified();
//			destDir.setLastModified(lastModified);
		}
		FileDirectorySplitter fds = new FileDirectorySplitter(srcDir.listFiles());
		File[] filesOnly = fds.getFiles();
		String srcFileName;
		for (int i=0; i<filesOnly.length; i++) {
				File toCopy = filesOnly[i];
				srcFileName = toCopy.getName();
				if (null != ffilter && ffilter.contains(srcFileName)) {
					continue;
				}
				if (filterHiddenFiles && toCopy.isHidden()) {
					continue;
				}

				// test if same file already exists
				File toTestFile = new File(newDestDir.getAbsolutePath() + "/" + srcFileName);
				if (toTestFile.exists()) {
					
					//verify the modification date
					long destLastModif = toTestFile.lastModified();
					long sourceLastModif = toCopy.lastModified();
					if (sourceLastModif > destLastModif) {
						
						//overwrite case
						copyFile(toCopy, newDestDir);
						fileLogger.eventFileOvewritten(new File(newDestDir + "/" + toCopy.getName()));
					}
				} else {
					copyFile(toCopy, newDestDir);
					fileLogger.eventFileCreated(new File(newDestDir + "/" + toCopy.getName()));
				}
		}
		File[] dirsOnly = fds.getDirectories();
		for (int i=0; i<dirsOnly.length; i++) {
			File newSrcDir = dirsOnly[i];
			doMergeDirectory(newSrcDir, newDestDir, ffilter, filterHiddenFiles, fileLogger);
		}		
	}

	private static void copyFile(File file, File destDir) throws FatalException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		File destFile = null;
		try {
			
			// create the destination file
			String destDirName = destDir.getAbsolutePath();
			String destFileName = destDirName + "/" + file.getName();
			destFile = new File(destFileName);
			
			// if the file already exists, it will not be created but reused
			destFile.createNewFile();
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			byte[] buff = new byte[DEFAULT_COPY_BUFFER_LENGTH];
			int bytesRead = 0;
			while ((bytesRead = bis.read(buff)) != -1) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			
			//If this exceptions occur, something is going wrong, something that can cause recurrent fail
			String msg = "FileNotFoundException: " + e.getMessage();
			throw new FatalException(msg, e);
		} catch (IOException e) {
			String msg = "IOException: " + e.getMessage();
			throw new FatalException(msg, e);
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
				if (null != bos) {
					bos.close();
				}

				//set the same "last modified" date as the source
				long lastModified = file.lastModified();
				destFile.setLastModified(lastModified);
//				System.out.println("date: " + new Date(lastModified));
			} catch (IOException e) {
				String msg = "IOException: " + e.getMessage();
				throw new FatalException(msg, e);
			}
		}
		
	}

}
