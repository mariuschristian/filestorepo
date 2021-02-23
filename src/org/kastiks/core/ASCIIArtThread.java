/**
 * 
 */
package org.kastiks.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class ASCIIArtThread extends Thread {
	
	private PrintStream ps = System.out;
	private long refreshDelay = 100;
	private File[] asciiFiles;
	private volatile boolean  run = true;
	
	public ASCIIArtThread(PrintStream ps, long refreshDelay, File asciiBaseFolder) {
		this.ps = ps;
		this.refreshDelay = refreshDelay;
		asciiFiles = asciiBaseFolder.listFiles();
	}

	@Override
	public void run() {
		if (null != asciiFiles) {
			while (this.run == true) {
				for (File asciiFile : asciiFiles) {
					if (this.run == false)
						return;
					printAsciiFile(asciiFile);
					ps.println();
					ps.println();
					ps.println();
					try {
						Thread.sleep(refreshDelay * 10);
					} catch (InterruptedException e) {
						System.out.println("Thread Interrupted!");
					}
				}
			}
		} else {
			return;
		}
		
	}

	private void printAsciiFile(File asciiFile) {
		BufferedReader buffReader = null;
		try {
			buffReader = new BufferedReader(new FileReader(asciiFile));
			String line;
			while ((line = buffReader.readLine()) != null) {
				ps.println(line);
				Thread.sleep(refreshDelay);
			}
		} catch (Exception e) {
			
			//If this exceptions occur, something abnormal happened
			String msg = "Unexpected exception during ASCII-Art Thread run, message: " + e.getMessage();
			System.out.println(msg);
			System.out.println("ASCII-Art Thread has to exit :(");
			try {
				this.join();
			} catch (InterruptedException e1) {
				//nothing happens
			}
		} finally {
			try {
				if (null != buffReader) {
					buffReader.close();
				}
			} catch (IOException e) {
				System.out.println("IOException during stream close: " + e.getMessage());
			}
		}
		
	}

	public void setStop() {
		this.run = false;
		
	}

}
