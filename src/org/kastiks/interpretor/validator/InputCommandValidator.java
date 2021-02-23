package org.kastiks.interpretor.validator;

import org.kastiks.interpretor.Parameter;
import org.kastiks.interpretor.Parameters;
import org.kastiks.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCommandValidator {
	
//	private final static int MAX_PARAMS = 4;
	
	private InputCommandValidator() {};
	
	/**
	 * @param args
	 * @return
	 */
	public static Parameter[] validate(String[] args) throws InputParameterException {
		List<Parameter> params = null;
		if (0 != args.length) {
			params = new ArrayList<Parameter>();
			int i = 0;
//			int nParams = 0;
			while (true) {
				String paramStr = args[i];
				if (!StringUtils.contains(paramStr, Parameters.allSwitches)) {

//					throw Validation Exception
					String msg = "Invalid parameter: " + paramStr; 
					throw new InputParameterException(msg);
				} else if (StringUtils.contains(paramStr, Parameters.switchesWithValue)) {
					String paramValue = args[++i];
//					validateParamValue(paramStr, paramValue);
					Parameter param = new Parameter(paramStr, paramValue);
					params.add(param);
//					nParams++;
				} else {

					// parameter without value case
					Parameter param = new Parameter(paramStr);
					params.add(param);
				}
				if (i == args.length - 1) {
					break;
				}
				i++;
			}
		} else {
			params = new ArrayList<Parameter> (0);
		}
		doValidate(params);
		return arrayListToArray(params);
	}
	
	private static void validateSinglePath(String path) throws InputParameterException {
		File file = new File(path);
		if (!file.exists()) {
			String msg = "Invalid folder path, does not exist: " + file.getAbsolutePath();
			throw new InputParameterException(msg);
		}
		if (!file.isDirectory()) {
			String msg = "Invalid folder path, not a directory: " + file.getAbsolutePath();
			throw new InputParameterException(msg);
		}
	}
	
	private static void validateManyPaths(String pathsConcat) throws InputParameterException {
		StringTokenizer tokens = new StringTokenizer(pathsConcat, ",");
		for (; tokens.hasMoreTokens(); ) {
			String path = tokens.nextToken();
			validateSinglePath(path);
		}
	}
	
	private static void doValidate(List params) throws InputParameterException {
		for (Iterator it = params.iterator(); it.hasNext(); ) {
			Parameter param = (Parameter) it.next();
			String key = param.getKey();
			String paramValue = param.getValue();
			if (Parameters.DEST_PATH_PARAM_NAME.equals(key) ||
					Parameters.PRUNE_SLAVE_PATH_PARAM_NAME.equals(key) ||
					Parameters.PRUNE_MASTER_PATH_PARAM_NAME.equals(key)) {
				validateSinglePath(paramValue);
			} else if (Parameters.SOURCE_PATH_PARAM_NAME.equals(key)) {
				validateManyPaths(paramValue);
			} else if (Parameters.FILTER_HIDDEN_FILES_PARAM_NAME.equals(key)) {
				validateFilterHiddenFilesValue(paramValue);
			}
		}
	}

	private static void validateFilterHiddenFilesValue(String paramValue) throws InputParameterException {
		Pattern pat = Pattern.compile("[yYnN]");
		Matcher matcher = pat.matcher(paramValue);
		if (!matcher.matches()) {
			String msg = "Unknown filterHiddenF value (" + paramValue + ")";
			throw new InputParameterException(msg);
		}
		
	}

	private static Parameter[] arrayListToArray(List list) {
		Parameter[] params = null;
		params = new Parameter[list.size()];
		int count = 0;
		for (Iterator it = list.iterator(); it.hasNext(); ) {
			params[count] = (Parameter) it.next();
			count++;
		}
		return params;
	}
	
//	boolean lookup(String inputParam, String[] referenceParams) {
//		boolean found = false;
//		for (int i=0; i<referenceParams.length; i++) {
//			if (referenceParams[i].compareTo(inputParam) == 0) {
//				found = true;
//				break;
//			}
//		}
//		return found;
//	}

}
