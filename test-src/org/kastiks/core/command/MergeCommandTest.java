package org.kastiks.core.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.kastiks.core.path.PathElement;
import org.kastiks.exception.FatalException;
import org.kastiks.interpretor.FileFilter;
import org.kastiks.log.FileLogger;

import junit.framework.TestCase;

public class MergeCommandTest extends TestCase {

	private static String OUTPUT_PATH;
	private static String INPUT_PATH;
	
	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("test.properties"));
			INPUT_PATH = prop.getProperty("test.merge.dir.src");
			OUTPUT_PATH = prop.getProperty("test.merge.dir.dest");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void testMergeDirectory() throws IOException {
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
			File logFile = new File("FTR_logFile_" + sdf.format(now));
			FileLogger fl = new FileLogger(logFile);
			FileFilter ffilter = new FileFilter("Thumbs.db");
			
			// no path validation here
			MergeCommand.mergeDirectory(new PathElement(INPUT_PATH),
					new PathElement(OUTPUT_PATH), ffilter, true, fl);
		} catch (FatalException e) {
			fail("CopyCommandException not expected! Message: " + e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
