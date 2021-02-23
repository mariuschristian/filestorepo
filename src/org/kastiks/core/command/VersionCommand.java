package org.kastiks.core.command;

import org.kastiks.core.GlobalReferences;

public class VersionCommand {
	
	public static void execute() {
		System.out.println();
		String line = "Version: " + GlobalReferences.PROGRAM_VERSION;
		System.out.println(line);
		System.out.println();
	}
}
