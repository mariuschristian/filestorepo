package org.kastiks.log;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import junit.framework.TestCase;

public class ReporterTest extends TestCase {

	public void testReportFinal() {
		long now = System.currentTimeMillis();
		long before = now - 1000000;
		long delta = now - before;
		double second = ((double) delta) / 1000;
		System.out.println("seconds :" + second);
		double min = second/60;
		System.out.println("min :" + min);
		NumberFormat nf = new DecimalFormat("###0.0#");
		System.out.println("formated ###0.0# min: " + nf.format(min));
		assertTrue("16.67".equals(nf.format(min)));
	}

}
