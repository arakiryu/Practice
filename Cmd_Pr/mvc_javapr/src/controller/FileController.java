package controller;

import view.FileView;

import java.io.File;

import model.FileModel;


public class FileController {
	
	private FileModel model;
	private FileView view;
	

    public FileController(FileModel model, FileView view) {
        this.model = model;
        this.view = view;
    }
    
    
    public void enterThePath(String path) {
        if (model.isValidDirectory(path)) {
            model.setPath(path);
            view.showCurrentPath(path);
        } else {
            view.showInvalidPathMessage();
        }
    }
    
    public void showFileList() {
    	File[] files = model.getFileList();
    	view.showFileList(files);
    }
    
    public void backToThePath() {
    	String parentPath = model.getParentFile();
    	model.setPath(parentPath);
    	view.showCurrentPath(parentPath);
    }
    
    public void changeName(String oldName , String newName) {
    	boolean success = model.renameFile(oldName, newName);
    	if(success) {
    		view.showRenameSuccess(newName);
    	}else {
    		view.showRenameFailure();
    	}
    }
    
    public void startView() {
    	String rootpath = model.getRootPath();
    	model.setPath(rootpath);
    	view.showCurrentPath(rootpath);
    }
    
    
}
