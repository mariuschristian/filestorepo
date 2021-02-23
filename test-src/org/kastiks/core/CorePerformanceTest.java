package org.kastiks.core;

import junit.framework.TestCase;

public class CorePerformanceTest extends TestCase {

	private static final String TEST_OUTPUT_PATH_0 = "/home/alfred/test/FTR/perf/output";
	private static final String TEST_INPUT_PATH_ELEM_0 = "/home/alfred/test/FTR/perf/root0";
	private static final String TEST_INPUT_PATH_ELEM_1 = "/home/alfred/test/FTR/perf/root1";
	private static final String TEST_INPUT_PATH_0 = TEST_INPUT_PATH_ELEM_0;
	private static final String TEST_INPUT_PATH_1 = TEST_INPUT_PATH_ELEM_0 + ":" + TEST_INPUT_PATH_ELEM_1;
	
	public void testPerf0() {
		String[] args = new String[] {
				"--input-path", TEST_INPUT_PATH_0,
				"--output-path", TEST_OUTPUT_PATH_0
		};
		long time0 = System.currentTimeMillis();
		Core.main(args);
		long time1 = System.currentTimeMillis();
		System.out.println("Test Perf0 Duration (ms) : " + (time1 - time0));
	}
}
