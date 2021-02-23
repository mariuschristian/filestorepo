package org.kastiks.core.command;

import java.io.File;

import org.kastiks.core.ASCIIArtThread;

public class ArtCommand {
	
	public static void execute() {

		// start the ASCII-Art Thread :D
		ASCIIArtThread aathread = new ASCIIArtThread(System.out, 100, new File("resources/ASCII-Art"));
		aathread.start();
	}

}
