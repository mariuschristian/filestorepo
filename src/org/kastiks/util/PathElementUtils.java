package org.kastiks.util;

import java.util.Iterator;
import java.util.List;

import org.kastiks.core.path.PathElement;

public class PathElementUtils {

	public static PathElement[] listToArray(List list) {
		PathElement[] pathEl = null;
		pathEl = new PathElement[list.size()];
		int count = 0;
		for (Iterator it = list.iterator(); it.hasNext(); ) {
			pathEl[count] = (PathElement) it.next();
			count++;
		}
		return pathEl;
	}

}
