package org.kastiks.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FileLogger {
	
	private PrintWriter pw;
	private long filesCreated = 0;
	private long filesOverwritten = 0;
	private long filesDeleted = 0;
	private long foldersDeleted = 0;
	private long foldersCreated = 0;
	private long startTime = 0;
	
	public FileLogger(File logFile) throws IOException {
		pw = new PrintWriter(new FileWriter(logFile), true);
		this.startTime = System.currentTimeMillis();
	}
	
	public void println(String str) {
		pw.println(str);
	}
	
	public void eventFolderCreated(File folder) {
		pw.println("Folder created:   " + folder.getAbsolutePath());
		foldersCreated++;
	}
	
	public void eventFileCreated(File file) {
		pw.println("File created:     " + file.getAbsolutePath());
		filesCreated++;
	}

	public void eventFileOvewritten(File file) {
		pw.println("File overwritten: " + file.getAbsolutePath());
		filesOverwritten++;
	}
	
	public void eventFileDeleted (File file) {
		pw.println("File DELETED: " + file.getAbsolutePath());
		filesDeleted++;
	}
	
	public void eventFolderDeleted (File dir) {
		pw.println("Directory DELETED: " + dir.getAbsolutePath());
		foldersDeleted++;
	}
	
	public void flush() {
		pw.flush();
	}
	
	public void closeStream() {
		pw.close();
	}	
	
	public void reportFinal() {
		StringBuffer sb = new StringBuffer();
		String lb = System.getProperty("line.separator");
		sb.append(lb);
		sb.append(lb);
		sb.append("*****************************************************" + lb);
		sb.append("* Total number of files created:     " + filesCreated + lb);
		sb.append("* Total number of files overwritten: " + filesOverwritten + lb);
		sb.append("* Total number of files deleted:     " + filesDeleted + lb);
		sb.append("* Total number of folders created:   " + foldersCreated + lb);
		sb.append("* Total number of folders deleted:   " + foldersDeleted + lb);
		
		long now = System.currentTimeMillis();
		long delta = now - this.startTime;
		double min = ((double) delta) / 60000;
		NumberFormat nf = new DecimalFormat("###0.0#");
		sb.append("* Total time taken to copy: " + nf.format(min) + " min" + lb);
		sb.append("*****************************************************" + lb);
		String report = sb.toString(); 
		pw.write(report);
		System.out.print(report);
	}
}
