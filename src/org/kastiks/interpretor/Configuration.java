package org.kastiks.interpretor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

import org.kastiks.core.path.InputPath;
import org.kastiks.core.path.PathElement;
import org.kastiks.exception.EmptyInputPathException;
import org.kastiks.exception.EmptyOutputPathException;
import org.kastiks.exception.InvalidInputPathException;
import org.kastiks.exception.InvalidOutputPathException;
import org.kastiks.exception.InvalidPathException;
import org.kastiks.exception.NoDirectoryException;
import org.kastiks.util.PathElementUtils;

public class Configuration {
	
	public static final String PATH_SEPARATOR = ",";
	public static final boolean FILTER_HIDDEN_FILES = true;
	
	private String mainOption; 
	private Map basicConfiguration;
	private PathConfiguration pathConfiguration;
	private String fileFilter;
	private boolean filterHiddenFiles = FILTER_HIDDEN_FILES;
	
	private Configuration() {}
	
	public Configuration(Parameter[] parameters) {
		this.basicConfiguration = new HashMap(parameters.length);
		initBasicConfiguration(parameters);
		setFilterHiddenFiles(isFilterHiddenFilesOn());
		this.setFileFilter((String) basicConfiguration.get(Parameters.EXCLUDE_PARAM_NAME));
		this.pathConfiguration = new PathConfiguration();
		initPathConfiguration();
	}
	
	public String getMainOption() {
		return this.mainOption;
	}

	public PathConfiguration getPathConfiguration() {
		return this.pathConfiguration;
	}
	
	public String getConfigValue(String configKey) {
		return (String) basicConfiguration.get(configKey);
	}
	
	public Set getConfigKeys() {
		return basicConfiguration.keySet();
	}
	
	public boolean containsHelp() {
		boolean containsHelp = false;
		if (null != basicConfiguration) {
			if (basicConfiguration.containsKey(Parameters.HELP_PARAM_NAME)) {
				containsHelp = true;
			}
		}
		return containsHelp;
	}
	
	public boolean containsVersion() {
		boolean containsVersion = false;
		if (null != basicConfiguration) {
			if (basicConfiguration.containsKey(Parameters.VERSION_PARAM_NAME)) {
				containsVersion = true;
			}
		}
		return containsVersion;
	}

	public boolean containsArt() {
		boolean test = false;
		if (null != basicConfiguration) {
			if (basicConfiguration.containsKey(Parameters.ART_PARAM_NAME)) {
				test = true;
			}
		}
		return test;
	}
	
	//if no args
	public boolean isBlank() {
		boolean isDefault = true;
		if (!basicConfiguration.isEmpty()) {
			isDefault = false;
		}
		return isDefault;
	}
	
	public void validate () throws EmptyOutputPathException, EmptyInputPathException,
		InvalidOutputPathException, InvalidInputPathException, NoDirectoryException, InvalidPathException {
		
		// no need to validate basicConfiguration, it is already validated by InputCommandValidator
		
		pathConfiguration.validate();
	}
	
	public void setFileFilter(String fileFilter) {
		this.fileFilter = fileFilter;
	}

	public String getFileFilter() {
		return fileFilter;
	}

	private boolean isFilterHiddenFilesOn() {
		boolean ret = true;
		String value = this.getConfigValue(Parameters.FILTER_HIDDEN_FILES_PARAM_NAME);
		if ("N".equalsIgnoreCase(value)) {
			ret = false;
		}
		return ret;
	}

	private void initBasicConfiguration(Parameter[] parameters) {		
		for (int i=0; i<parameters.length; i++) {
			Parameter param = parameters[i];
			String paramName = param.getKey();
			if (Parameters.MERGE_OPTION.equals(paramName) ||
					Parameters.PRUNE_OPTION.equals(paramName) ) {
				this.mainOption = paramName;
				continue;
			}
			this.basicConfiguration.put(param.getKey(), param.getValue());
		}
	}

	private void initPathConfiguration() {
		if (!this.isBlank()) {
			Set keys = this.getConfigKeys();
			for (Iterator it = keys.iterator(); it.hasNext(); ) {
				String key = (String) it.next();
				String paramValue = this.getConfigValue(key);
				populatePathConfiguration(key, paramValue);
			}
		} else {
			
			// if config is Default, meaning no parameters, there is nothing to do
		}
	}
	
	private void populatePathConfiguration(String key, String paramValue) {
		if (Parameters.SOURCE_PATH_PARAM_NAME.equals(key)) {

			//parsing the given path
			StringTokenizer st = new StringTokenizer(paramValue, PATH_SEPARATOR);
			List inputPaths = new ArrayList();
			for ( ; st.hasMoreTokens(); ) {
				String path = st.nextToken();
				inputPaths.add(new PathElement(path));
			}
			InputPath inputPath = new InputPath(PathElementUtils.listToArray(inputPaths));
			this.pathConfiguration.setInputPath(inputPath);
		} else if (Parameters.DEST_PATH_PARAM_NAME.equals(key) ||
					Parameters.PRUNE_SLAVE_PATH_PARAM_NAME.equals(key)) {
			
			//output-path case, only one path is expected
			//slave path is stored into output path variable
			this.pathConfiguration.setOutputPath(new PathElement(paramValue));
		} else if (Parameters.PRUNE_MASTER_PATH_PARAM_NAME.equals(key)) {
			
			//master path is stored into input path variable
			PathElement[] masterPathEl = { new PathElement(paramValue) };
			InputPath inputPath = new InputPath(masterPathEl);
			this.pathConfiguration.setInputPath(inputPath);
		}
		
	}

	public boolean isFilterHiddenFiles() {
		return filterHiddenFiles;
	}

	public void setFilterHiddenFiles(boolean filterHiddenFiles) {
		this.filterHiddenFiles = filterHiddenFiles;
	}
	
	public String toString() {
		String configStr = null;
		configStr = "Main option: " + this.mainOption + "\n";
		Set <String> keys = basicConfiguration.keySet();
		for (Iterator it = keys.iterator(); it.hasNext(); ) {
			String key = (String) it.next();
			String line = key + " = " + this.getConfigValue(key) + "\n";
			configStr += line;
		}
		return configStr;
	}

	public boolean isMerge() {
		return Parameters.MERGE_OPTION.equals(mainOption) ? true : false;
	}
	
	public boolean isPrune() {
		return Parameters.PRUNE_OPTION.equals(mainOption) ? true : false;
	}

	public boolean isArtOnly() {
		boolean test = false;
		if (this.containsArt() && !this.isMerge() && !this.isPrune()) {
			test = true;
		}
		return test;
	}
}
