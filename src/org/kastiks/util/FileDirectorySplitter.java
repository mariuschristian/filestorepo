package org.kastiks.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDirectorySplitter {
	
	private List files;
	private List directories;
	
	private FileDirectorySplitter() {}
	
	public FileDirectorySplitter(File[] allFiles) {
		this.files = new ArrayList();
		this.directories = new ArrayList();
		for (int i=0; i<allFiles.length; i++) {
			if (allFiles[i].isDirectory()) {
				directories.add(allFiles[i]);
			} else {
				files.add(allFiles[i]);
			}
		}
	}
	
	public File[] getFiles() {
		File[] files = null;
		files = new File[this.files.size()];
		for (int i=0; i<files.length; i++) {
			files[i] = (File) this.files.get(i);
		}
		return files;
	}
	
	public File[] getDirectories() {
		File[] dirs = null;
		dirs = new File[this.directories.size()];
		for (int i=0; i<dirs.length; i++) {
			dirs[i] = (File) this.directories.get(i);
		}
		return dirs;
	}

}
