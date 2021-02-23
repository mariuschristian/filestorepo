package org.kastiks.interpretor;

import java.util.StringTokenizer;

import org.kastiks.core.path.InputPath;
import org.kastiks.core.path.PathElement;
import org.kastiks.exception.EmptyInputPathException;
import org.kastiks.exception.EmptyOutputPathException;
import org.kastiks.exception.EmptyPathException;
import org.kastiks.exception.InvalidInputPathException;
import org.kastiks.exception.InvalidOutputPathException;
import org.kastiks.exception.InvalidPathException;
import org.kastiks.exception.NoDirectoryException;

public class PathConfiguration {
	
	/*
	 * With "merge" option:
	 * InputPath is the source(s) repository(s)
	 * OutputPath is the destination repository
	 * 
	 *  With "prune" option:
	 *  InputPath is master repository (only one)
	 *  OutputPath is the slave repository.
	 */
	private PathElement destPath;
	private InputPath sourcePath;
	
	
	public InputPath getInputPath() {
		return sourcePath;
	}
	
	public void setInputPath(InputPath inputPath) {
		this.sourcePath = inputPath;
	}
	public PathElement getOutputPath() {
		return destPath;
	}
	public void setOutputPath(PathElement outputPath) {
		this.destPath = outputPath;
	}
	
	public boolean isEmpty() {
		boolean isEmpty = false;
		if (null == destPath && null == sourcePath) {
			isEmpty = true;
		} else if(null == destPath && null!= sourcePath) {
			isEmpty = sourcePath.isEmpty();
		} else if (null != destPath && null == sourcePath) {
			isEmpty = destPath.isEmpty();
		} else if (null != destPath && null != sourcePath) {
			isEmpty = destPath.isEmpty() & sourcePath.isEmpty();
		}
		return isEmpty;
	}
	
	public void validate() throws EmptyOutputPathException, EmptyInputPathException,
		InvalidOutputPathException, InvalidInputPathException, NoDirectoryException, InvalidPathException {
		
		if (null == destPath) {
			String msg = "Fatal: output path is empty";
			throw new EmptyOutputPathException(msg);
		} else if (null == sourcePath) {
			String msg = "Info: input path is empty";
			throw new EmptyInputPathException(msg);
		} else {
			try {
				destPath.validate();
			} catch (InvalidPathException e) {
				String msg = "Invalid output path: " + e.getMessage();
				throw new InvalidOutputPathException(msg, e);
			} catch (EmptyPathException e) {
				String msg = "Fatal: output path is empty";
				throw new EmptyOutputPathException(msg);
			}
			sourcePath.validate();

			// test same folder name ending for both source path and destination path
			int slashIdx = destPath.toString().lastIndexOf('/');
			String lastFolder = destPath.toString().substring(slashIdx);
			
			// taking the first input path, multiple paths is deprecated since adapting copy command to merge
			String firstSrcPath = sourcePath.getInputPath()[0].toString();
			int slashIdx2 = firstSrcPath.lastIndexOf('/');
			String lastFolder2 = firstSrcPath.substring(slashIdx2);
			if (!lastFolder.equals(lastFolder2)) {
				String msg = "Fatal: source folder ending and destination folder ending do not match, for merging";
				throw new InvalidPathException(msg);
			}
		}
	}
	
}
