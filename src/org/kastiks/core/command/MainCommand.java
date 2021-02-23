package org.kastiks.core.command;

import org.kastiks.core.path.InputPath;
import org.kastiks.core.path.PathElement;
import org.kastiks.exception.FatalException;
import org.kastiks.interpretor.Configuration;
import org.kastiks.interpretor.FileFilter;
import org.kastiks.interpretor.Parameters;
import org.kastiks.log.FileLogger;

public class MainCommand {
	
	private String mainOption;
	private Configuration config;
	private FileLogger fileLogger;
	
	public MainCommand(String mainOption, Configuration config, FileLogger flogger) {
		this.mainOption = mainOption;
		this.config = config;
		this.fileLogger = flogger;
	}
	
	public void execute() throws FatalException {
		InputPath sourcePaths = config.getPathConfiguration().getInputPath();
		PathElement destPath = config.getPathConfiguration().getOutputPath();
		String fileFilterStr = config.getFileFilter();
		FileFilter ffilter = null;
		if (null != fileFilterStr) {
			ffilter = new FileFilter(fileFilterStr);
		}
		boolean filterHiddenFiles = config.isFilterHiddenFiles();
		if (Parameters.MERGE_OPTION.equals(mainOption)) {
			MacroMergeCommand mcc = new MacroMergeCommand(sourcePaths, destPath, ffilter, filterHiddenFiles, fileLogger);
			mcc.execute();
		} else if (Parameters.PRUNE_OPTION.equals(mainOption)) {
			PathElement[] paths = sourcePaths.getInputPath();
			PruneCommand.prune(paths[0], destPath, fileLogger);
		}
			
	}

}
 