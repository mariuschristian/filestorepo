package org.kastiks.core.command;

import org.kastiks.core.path.InputPath;
import org.kastiks.core.path.PathElement;
import org.kastiks.exception.FatalException;
import org.kastiks.interpretor.FileFilter;
import org.kastiks.log.FileLogger;


public class MacroMergeCommand {
	
	private PathElement outputPath;
	private InputPath inputPath;
	private FileFilter ffilter;
	private boolean filterHiddenFiles;
	private FileLogger fileLogger;
	
	public MacroMergeCommand(InputPath inputPath, PathElement outputPath,
			FileFilter ffilter, boolean filterHiddenFilesFlag, FileLogger fileLogger) {
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.ffilter = ffilter;
		this.filterHiddenFiles = filterHiddenFilesFlag;
		this.fileLogger = fileLogger;
	}
	
	
	
	public void execute() throws FatalException {
		PathElement[] inputPathEls = inputPath.getInputPath();
		for (int i=0; i<inputPathEls.length; i++) {
			try {
				MergeCommand.mergeDirectory(inputPathEls[i], outputPath, ffilter, filterHiddenFiles, fileLogger);
			} catch (FatalException e) {
				String msg = "Exception occured while copying files from " + inputPathEls[i].toString() +
					" to " + outputPath.toString();
				e.printStackTrace();
				throw new FatalException(msg, e);
			}
		}
	}

}
