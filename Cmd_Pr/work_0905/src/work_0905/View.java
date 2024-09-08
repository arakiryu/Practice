package work_0905;

public class View {
	
	public static void printProperty() {
		
		System.out.printf("%-30s %-10s %-25s %10s" , "이름" , "확장자" , "수정/날짜" , "파일 크기");
		System.out.println();
	}
	
	public static void printLowOption() {
		System.out.printf("%-20s %-10s %10s %20s" , "폴더이동 : cd" ,"이전경로 : cd/" , "이름변경 : mv");
	}

}
