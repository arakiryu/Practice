package main;

import java.util.Scanner;

import controller.FileController;
import model.FileModel;
import view.FileView;

public class Main {
	public static void main(String[] args) {

		FileModel model = new FileModel();
		FileView view = new FileView();
		FileController controller = new FileController(model, view);

		Scanner sc = new Scanner(System.in);
		boolean tf = true;

		controller.startView();

		while (tf) {
			String command = sc.nextLine();
			String[] cmdarr = command.split(" ");

			switch (cmdarr[0]) {
			case "ls":
				controller.showFileList();
				break;
			case "cd":
				if (cmdarr.length > 1) {
					controller.enterThePath(cmdarr[1]);
				} else {
					System.out.println("경로를 입력해주세요.");
				}
				break;
			case "cd..":
				controller.backToThePath();
				break;
			case "mv":
				if (cmdarr.length == 3) {
					controller.changeName(cmdarr[1], cmdarr[2]);
				} else {
					System.out.println("올바른 형식: mv 원래이름 새이름");
				}
				break;
			case "exit":
				tf = false;
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("알 수 없는 명령어입니다.");
			}
		}
	}
}
