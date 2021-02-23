package org.kastiks.interpretor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class FileFilter {
	
	private List fileExcludes;
	
	private FileFilter() {}
	
	/**
	 * @param excludesChain
	 */
	public FileFilter(String excludesChain) {
		if (null != excludesChain) {
			fileExcludes = new ArrayList(3);
			StringTokenizer st = new StringTokenizer(excludesChain, ",");
			String token;
			for ( ; st.hasMoreTokens(); ) {
				token = st.nextToken();
				fileExcludes.add(token);
			}
		}
	}
	
	public boolean contains(String fileName) {
		boolean contains = false;
		for (Iterator it=fileExcludes.iterator(); it.hasNext(); ){
			if (fileName.equals(it.next().toString())) {
				contains = true;
				break;
			}
		}
		return contains;
	}
}
