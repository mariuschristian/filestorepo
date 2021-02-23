package org.kastiks.core;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kastiks.core.command.ArtCommand;
import org.kastiks.core.command.HelpCommand;
import org.kastiks.core.command.MacroMergeCommand;
import org.kastiks.core.command.MainCommand;
import org.kastiks.core.command.VersionCommand;
import org.kastiks.core.path.InputPath;
import org.kastiks.core.path.PathElement;
import org.kastiks.exception.EmptyInputPathException;
import org.kastiks.exception.EmptyOutputPathException;
import org.kastiks.exception.FatalException;
import org.kastiks.exception.InvalidInputPathException;
import org.kastiks.exception.InvalidOutputPathException;
import org.kastiks.exception.InvalidPathException;
import org.kastiks.exception.NoDirectoryException;
import org.kastiks.interpretor.Configuration;
import org.kastiks.interpretor.Parameter;
import org.kastiks.interpretor.FileFilter;
import org.kastiks.interpretor.validator.InputCommandValidator;
import org.kastiks.interpretor.validator.InputParameterException;
import org.kastiks.log.FileLogger;

class Core {
	
	private static Configuration configuration;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Parameter[] params = null;
		try {
			params = InputCommandValidator.validate(args);
			configuration = new Configuration(params);
			if (configuration.isBlank() || configuration.containsHelp()) {
				HelpCommand.execute();
			} else if (configuration.containsVersion()) {
				VersionCommand.execute();
			} else if (configuration.isArtOnly()) {
				ArtCommand.execute();
			} else {
				FileLogger flogger = null;
				ASCIIArtThread aathread = null;
				try {
					configuration.validate();
					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
					String mainOption = configuration.getMainOption();
					File logFile = new File("FTR_" + mainOption.substring(2) + "_logFile_" + sdf.format(now) + ".txt");
					try {
						flogger = new FileLogger(logFile);
						if (configuration.containsArt()) {
							
							//start the ASCII-Art Thread :D
							aathread = new ASCIIArtThread(System.out, 100, new File("resources/ASCII-Art"));
							aathread.start();
						}
						MainCommand mainCmd = new MainCommand(mainOption, configuration, flogger);
						mainCmd.execute();
						flogger.reportFinal();
						System.out.println();
						System.out.println();
						System.out.println(">>> Log file created: " + logFile.getAbsolutePath());
						System.out.println(">>> Job done master.");
					} catch (IOException e) {
						System.out.println("IOException: possibly could not create log file, program exits!");
						System.exit(-1);
					}
				} catch (EmptyOutputPathException e) {
					System.out.println(e.getMessage());
				} catch (EmptyInputPathException e) {
					System.out.println(e.getMessage() + ", nothing to do");
				} catch (InvalidOutputPathException e) {
					System.out.println(e.getMessage());
				} catch (InvalidInputPathException e) {
					System.out.println(e.getMessage());
				} catch (NoDirectoryException e) {
					System.out.println(e.getMessage());
				} catch (InvalidPathException e) {
					System.out.println(e.getMessage());
				} catch (FatalException e) {
					System.out.println("Fatal Exception:");
					System.out.println(e.getMessage());
					System.out.println(">>> Program exits!");
					if (null != flogger) {
						flogger.println("Fatal Exception:");
						flogger.println(e.getMessage());
						flogger.println(">>> Program exits!");
					}
					System.exit(-1);
				} finally {
					if (flogger != null) {
//						flogger.flush();
						flogger.closeStream();
					}
					if (aathread != null) {
						aathread.setStop();
						try {
							aathread.join();
						} catch (InterruptedException e) {
							// if the ASCII art thread was interrupted for some reason, we have nothing to do, it is not important.
							// Anyway, there is no case in FTR code which interrupts this thread.
						}
					}
				}
			}
		} catch(InputParameterException e) {
			System.out.println(e.getMessage());
			HelpCommand.execute();
		}
	}
	
}
