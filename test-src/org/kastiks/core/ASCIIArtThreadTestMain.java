package org.kastiks.core;

import java.io.File;

import junit.framework.TestCase;

public class ASCIIArtThreadTestMain  {
	public static void main(String[] args) {
		ASCIIArtThread aathread = new ASCIIArtThread(System.out, 100, new File("resources/ASCII-Art"));
		aathread.start();
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
