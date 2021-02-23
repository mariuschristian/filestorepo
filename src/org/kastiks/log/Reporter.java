package org.kastiks.log;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @deprecated
 */

public class Reporter {
	
	private static final int REPORT_LINE_THRESHOLD = 50; 
	private PrintStream printStream;
	private long filesCopied = 0;
	private long startTime = 0;

	private Reporter() {}
	
	public Reporter(PrintStream ps) {
		this.printStream = ps;
		this.startTime = System.currentTimeMillis();
	}
	
	public void reportDir(String dirName) {
		this.printStream.println("in " + dirName);
	}
	
	public void incrFilesCopied() {
		filesCopied++;
		if (filesCopied % REPORT_LINE_THRESHOLD == 0) {
			reportLine();
		}
	}
	
	private void reportLine() {
		this.printStream.println("Number of files copied so far: " + filesCopied + " ...");
	}
	
	public void reportFinal() {
		this.printStream.println();
		this.printStream.println("***");
		this.printStream.println("* Total number of files copied: " + filesCopied);
		long now = System.currentTimeMillis();
		long delta = now - this.startTime;
		double min = ((double) delta) / 60000;
		NumberFormat nf = new DecimalFormat("###0.0#");
		this.printStream.println("* Total time taken to synchronize: " + nf.format(min) + " min");
		this.printStream.println("***");
	}

}
