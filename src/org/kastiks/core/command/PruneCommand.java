package org.kastiks.core.command;

import java.io.File;
import java.io.IOException;

import org.kastiks.core.path.PathElement;
import org.kastiks.exception.FatalException;
import org.kastiks.log.FileLogger;
import org.kastiks.util.FileDirectorySplitter;
import org.kastiks.util.FileUtils;

public class PruneCommand {
		
	public static void prune(PathElement masterRoot, PathElement slaveRoot, FileLogger log) throws FatalException {
		File masterRootFile = new File(masterRoot.toString());
		File slaveRootFile = new File(slaveRoot.toString());
		doPrune(masterRootFile, slaveRootFile, log);
	}
	
	private static void doPrune(File masterDir, File slaveDir, FileLogger log) throws FatalException {
//		log.println(">>> Info: doPrune(master, salve): "  + masterDir.toString() + " | " + slaveDir.toString());
		FileDirectorySplitter slaveFDS = new FileDirectorySplitter(slaveDir.listFiles());
		File [] slaveFilesOnly = slaveFDS.getFiles();
		for (int i=0; i<slaveFilesOnly.length; i++) {
			File slaveFile = slaveFilesOnly[i];
			String slaveFileName = slaveFile.getName();
			File masterFile = new File (masterDir.getAbsolutePath() + "/" + slaveFileName);
			if (!masterFile.exists()) {
				boolean isDeleted = slaveFile.delete();
				if (!isDeleted) {
					String msg = "Could not delete 'slave' file: " + slaveFile.toString() + ", suspicious case, aborting.";
					log.println(msg);
					throw new FatalException(msg);
				} else {
					log.eventFileDeleted(slaveFile);
				}
			}
		}
		File[] slaveDirsOnly = slaveFDS.getDirectories();
		for (int i=0; i<slaveDirsOnly.length; i++) {
			File slaveDirFile = slaveDirsOnly[i];
			String slaveDirFileName = slaveDirFile.getName();
			File masterDirFile = new File (masterDir.getAbsolutePath() + "/" + slaveDirFileName);
			if (!masterDirFile.exists()) {
				FileUtils.deleteDir(slaveDirFile, log);
			} else {
				doPrune(masterDirFile, slaveDirFile, log);
			}
		}		
	}
}
