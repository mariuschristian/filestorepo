package org.kastiks.core;

import java.io.File;

import junit.framework.TestCase;

public class ASCIIArtThreadTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRun() {
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
