package org.kastiks.core.command;

import org.kastiks.interpretor.Parameters;

public class HelpCommand {
	
	public static void execute() {
		System.out.println();
		String line = "Usage: java -jar build-FTR_versionyyy.jar ARGS";
		System.out.println(line);
		System.out.println();
		line = "ARGS:";
		System.out.println(line);
		System.out.println();
		String margin = "   ";
		String margin2 = "      ";
		String space = "   ";
		line = margin + Parameters.HELP_PARAM_NAME + space + ":" + space +
		"Displays this help message";
		System.out.println(line);
		System.out.println();
		line = margin + Parameters.VERSION_PARAM_NAME + space + ":" + space + "Software version";
		System.out.println(line);
		System.out.println();
		
		line = margin + Parameters.MERGE_OPTION + space + ":" + space + "intelligent merge between source folder(s) - master - and destination folder - slave";
		System.out.println(line);
		line = margin2 + Parameters.SOURCE_PATH_PARAM_NAME + space + "path0[,path1[...]]" + space + ":" + space +
		"Sets the source folders - master. These folders will be recursively merged.";
		System.out.println(line);
		System.out.println();
		line = margin2 + Parameters.DEST_PATH_PARAM_NAME + " path" + space + ":" + space +
		"Root directory of the destination file repository - slave -. All files and folders will be merged in this root structure.";
		System.out.println(line);
		System.out.println();
		
		line = margin + Parameters.PRUNE_OPTION + space + ":" + space + "pruning of directories and files in the slave repository, which are no more present into the master repository";
		System.out.println(line);
		line = margin2 + Parameters.PRUNE_MASTER_PATH_PARAM_NAME + " path" + space + ":" + space +
		"Sets the master repository folder. Its files will be used as reference, and remain untouched in prune mode.";
		System.out.println(line);
		System.out.println();
		line = margin2 + Parameters.PRUNE_SLAVE_PATH_PARAM_NAME + " path" + space + ":" + space +
		"Sets the slave repository folder. Directories and files which are no present in the master reference will be deleted, freeing the extra space.";
		System.out.println(line);
		System.out.println();
		
		line = margin + Parameters.EXCLUDE_PARAM_NAME + " fileName0[,fileName1[...]]" + space + ":" + space +
		"Excludes the given files from the copy/synchronization. Exluding \"Thumbs.db\" is highly recommended on Windows";
		System.out.println(line);
		System.out.println();
	}

}
