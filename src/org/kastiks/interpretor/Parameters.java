package org.kastiks.interpretor;

public class Parameters {
	public static final String HELP_PARAM_NAME = "--help";
	public static final String MERGE_OPTION = "--merge";
	public static final String DEST_PATH_PARAM_NAME = "--destination-path";
	public static final String SOURCE_PATH_PARAM_NAME = "--source-path";
	public static final String VERSION_PARAM_NAME = "--version";
	public static final String EXCLUDE_PARAM_NAME = "--exclude";
	public static final String FILTER_HIDDEN_FILES_PARAM_NAME = "--filterHiddenF";
	public static final String PRUNE_OPTION = "--prune";
	public static final String PRUNE_MASTER_PATH_PARAM_NAME = "--master-path";
	public static final String PRUNE_SLAVE_PATH_PARAM_NAME = "--slave-path";
	public static final String ART_PARAM_NAME = "--art";
//	public static final String REPORT_PARAM_NAME = "--report";
	
//	public static final String REPORT_PARAM_VALUE_ON = "on";
//	public static final String REPORT_PARAM_VALUE_OFF = "off";
	
	public static String[] allSwitches = {
		HELP_PARAM_NAME,
		MERGE_OPTION,
		DEST_PATH_PARAM_NAME,
		SOURCE_PATH_PARAM_NAME,
//		REPORT_PARAM_NAME,
		VERSION_PARAM_NAME,
		EXCLUDE_PARAM_NAME,
		FILTER_HIDDEN_FILES_PARAM_NAME,
		PRUNE_OPTION,
		PRUNE_MASTER_PATH_PARAM_NAME,
		PRUNE_SLAVE_PATH_PARAM_NAME,
		ART_PARAM_NAME
	};
	
	public static String[] switchesWithValue = {
		DEST_PATH_PARAM_NAME,
		SOURCE_PATH_PARAM_NAME,
//		REPORT_PARAM_NAME
		EXCLUDE_PARAM_NAME,
		FILTER_HIDDEN_FILES_PARAM_NAME,
		PRUNE_MASTER_PATH_PARAM_NAME,
		PRUNE_SLAVE_PATH_PARAM_NAME
	};
	
	public static String[] switchesWithoutValue = {
		HELP_PARAM_NAME,
		MERGE_OPTION,
		VERSION_PARAM_NAME,
		PRUNE_OPTION,
		ART_PARAM_NAME
	};

}
