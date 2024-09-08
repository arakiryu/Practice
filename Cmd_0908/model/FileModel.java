package model;

import java.io.File;

public class FileModel {

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isValidDirectory(String path) {
		File file = new File(path);
		return file.isDirectory();
	}

	public File[] getFileList() {
		File file = new File(path);
		return file.listFiles();
	}

	public String getParentFile() {
		File file = new File(path);
		return file.getParent();
	}

	public boolean renameFile(String oldName, String newName) {
		File oldFile = new File(path + "/" + oldName);
		File newFile = new File(path + "/" + newName);
		return oldFile.renameTo(newFile);
	}

	public String getRootPath() {
		return "C:" + File.separator;
	}
	
	
	
}
