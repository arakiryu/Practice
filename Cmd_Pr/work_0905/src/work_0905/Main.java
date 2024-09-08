package work_0905;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		FileOption fo = new FileOption();

		Scanner sc = new Scanner(System.in);
		boolean tf = true;
		
		
		while(true) {
			String command = sc.nextLine();
			String[] arr = command.split(" ");
			
			if(arr[0].equals("ls") ) {
				fo.showFileList(fo.getPath());
			}else if(arr[0].equals("cd")) {
				fo.enterThePath(arr[1]);
			}else if(arr[0].equals("cd..")) {
				fo.backToThePath();
			}else if(arr[0].equals("mv")) {
				if(arr.length == 3) {
					fo.changeName(arr[1] , arr[2]);
				}else {
					System.out.println("다시 입력하세요");
				}
				
			}else if(arr[0].equals("exit")){
				tf = false;
			}else {
				System.out.println("잘못된 입력 다시입력하세요");
			}
			
			
			
		}
		
		
		
		
		
	}

}
