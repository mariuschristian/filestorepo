package org.kastiks.core.path;

import java.io.File;

import org.kastiks.exception.EmptyPathException;
import org.kastiks.exception.InvalidPathException;
import org.kastiks.exception.NoDirectoryException;

public class PathElement {
	
	private String path;
	
	private PathElement() {}
	
	public PathElement(String path) {
		this.path = path;
	}
	
	public void validate() throws EmptyPathException, InvalidPathException, NoDirectoryException {
		if (isEmpty()) {
			String msg = "empty path";
			throw new EmptyPathException(msg);
		} else {
			File file = new File(path);
			if (!file.exists()) {
				String msg = "Invalid directory: " + path;
				throw new InvalidPathException(msg);
			} else {
				if (!file.isDirectory()) {
					String msg = "Not a directory: " + path;
					throw new NoDirectoryException(msg);
				}
			}
		}
	}

	public boolean isEmpty() {
		boolean isEmpty = false;
		if (null == path || "".equals(path.trim())) {
			isEmpty = true;
		}
		return isEmpty;
	}
	
	public String toString() {
		String str = "<null>";
		if (null != path) {
			str = path;
		}
		return str;
	}

}
