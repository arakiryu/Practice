package view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileView {

	public void showCurrentPath(String path) {
		System.out.println(path);
	}

	public void showInvalidPathMessage() {
		System.out.println("현재 경로가 없습니다 다시 입력");
	}

	public void showFileList(File[] files) {
		printProperty();
		System.out.println();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (File fl : files) {
			System.out.printf("%-30s %-10s %-25s %10d", fl.getName(),
					fl.isDirectory() ? "Directory" : fl.getName().substring(fl.getName().lastIndexOf('.') + 1),
					sdf.format(new Date(fl.lastModified())), fl.length());
			System.out.println();
		}
	}

	public void showRenameSuccess(String newName) {
		System.out.println("변경 되었습니다" + newName);
	}

	public void showRenameFailure() {
		System.out.println("변경 되지않았습니다 , 확장자 명까지 입력바랍니다");
	}

	public static void printProperty() {
		System.out.printf("%-30s %-10s %-25s %10s", "이름", "확장자", "수정/날짜", "파일 크기");
		System.out.println();
	}

	public static void printLowOption() {
		System.out.printf("%-20s %-10s %10s %20s", "폴더이동 : cd", "이전경로 : cd/", "이름변경 : mv");
	}

}