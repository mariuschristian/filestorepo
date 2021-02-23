package org.kastiks.core.path;

import org.kastiks.exception.EmptyInputPathException;
import org.kastiks.exception.EmptyPathException;
import org.kastiks.exception.InvalidInputPathException;
import org.kastiks.exception.InvalidPathException;
import org.kastiks.exception.NoDirectoryException;

public class InputPath {
	
	private PathElement[] inputPath;

	public InputPath(PathElement[] inputPath) {
		this.inputPath = inputPath;
	}
	
	public PathElement[] getInputPath() {
		return inputPath;
	}

	public void setInputPath(PathElement[] inputPath) {
		this.inputPath = inputPath;
	}

	public boolean isEmpty() {
		boolean isEmpty = false;
		if (null == inputPath || 0 == inputPath.length) {
			isEmpty = true;
		} else {
			
			//in this case inputPath is not null and has at least one element
			boolean foundNotEmptyElem = false;
			for (int i=0; i<inputPath.length; i++) {
				if (!inputPath[i].isEmpty()) {
					foundNotEmptyElem = true;
					break;
				}
			}
			isEmpty = !foundNotEmptyElem;
		}
		return isEmpty;
	}
	
	public void validate() throws EmptyInputPathException, InvalidInputPathException {
		
		if (isEmpty()) {
			String msg = "Info: empty input path";
			throw new EmptyInputPathException(msg);
		} else {
			for (int i=0; i<inputPath.length; i++) {
				try {
					inputPath[i].validate();
				} catch (EmptyPathException e) {
					String msg = "Empty input path: " + e.getMessage();
					throw new InvalidInputPathException(msg, e);
				} catch (InvalidPathException e) {
					String msg = "Invalid input path: " + e.getMessage();
					throw new InvalidInputPathException(msg, e);
				} catch (NoDirectoryException e) {
					String msg = "Invalid input path, not a directory: " + e.getMessage();
					throw new InvalidInputPathException(msg, e);
				}
			}
		}
	}
}
