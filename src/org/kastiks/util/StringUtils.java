package org.kastiks.util;

import java.util.Iterator;
import java.util.List;

public class StringUtils {
	
	public static boolean contains(String key, String[] strings) {
		boolean contains = false;
		for (int i = 0; i<strings.length; i++) {
			if (strings[i].equals(key)) {
				contains = true;
			}
		}
		return contains;
	}
	
	public static String[] listToArray(List list) {
		String[] str = null;
		str = new String[list.size()];
		int count = 0;
		for (Iterator it = list.iterator(); it.hasNext(); ) {
			str[count] = (String) it.next();
			count++;
		}
		return str;
	}

}
