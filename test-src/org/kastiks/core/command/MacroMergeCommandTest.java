package org.kastiks.core.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kastiks.core.path.InputPath;
import org.kastiks.core.path.PathElement;
import org.kastiks.exception.FatalException;
import org.kastiks.interpretor.FileFilter;
import org.kastiks.log.FileLogger;

import junit.framework.TestCase;

public class MacroMergeCommandTest extends TestCase {

	private static final String TEST_OUTPUT_PATH_0 = "/home/alfred/output";
	private static final String TEST_OUTPUT_PATH_1 = "/home/alfred/test/FTR/output";
	private static final String TEST_FILE_PATH_0 = "/home/alfred/tmp/dummyFile";
	private static final String TEST_INPUT_PATH_ELEM_0 = "/home/alfred/input0";
	private static final String TEST_INPUT_PATH_ELEM_1 = "/home/alfred/input1";
	private static final String TEST_INPUT_PATH_ELEM_2 = "/home/alfred/test/FTR/root0";
	private static final String TEST_INPUT_PATH_ELEM_3 = "/home/alfred/test/FTR/root1";
	private static final String TEST_INPUT_PATH_0 = TEST_INPUT_PATH_ELEM_0 + ";" + TEST_INPUT_PATH_ELEM_1;
	private static final String TEST_INPUT_PATH_1 = TEST_INPUT_PATH_ELEM_2 + ";" + TEST_INPUT_PATH_ELEM_3;
	
	public void testExecute() throws IOException {
		PathElement[] inputPathEls = new PathElement[]{
				new PathElement(TEST_INPUT_PATH_ELEM_2),
				new PathElement(TEST_INPUT_PATH_ELEM_3)
				};
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
		File logFile = new File("FTR_logFile_" + sdf.format(now));
		try {
			FileLogger fl = new FileLogger(logFile);
			PathElement outputPathEl = new PathElement(TEST_OUTPUT_PATH_1);
			InputPath inputPath = new InputPath(inputPathEls);
			FileFilter ffilter = new FileFilter("Thumbs.db");
			MacroMergeCommand mcc = new MacroMergeCommand(inputPath, outputPathEl, ffilter, true, fl);
			mcc.execute();
		} catch (FatalException e) {
			fail("MacroCopyCommandException not expected! Message: " + e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
