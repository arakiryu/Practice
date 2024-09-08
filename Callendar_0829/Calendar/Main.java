package re_pr;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		//달력
		//1월 ~ 12월
		//년도 입력 받기
		//일수 1~31까지 
		//시작 날짜가 무슨 요일인지 알아야함
		Calendar cal = Calendar.getInstance();
		Scanner sc = new Scanner(System.in);
		CallCalendar call = new CallCalendar();
		
		
		
		System.out.print("선택 입력 : ");
		int a = sc.nextInt();
		
		
		
		switch(a) {
		
		case 1:
			System.out.println("년도 입력");
			int year = sc.nextInt();
			call.selectYearAllMonth(year);
		
			break;
		
		case 2:
			System.out.println("년도 입력 =>");
			int year1 = sc.nextInt();
			System.out.println("월 입력 =>");
			int month = sc.nextInt();
			call.selectYearAndMonth(year1, month);
			
			break;
			
		case 3:
			System.out.println("종료");
			return;
		}
		
		
		
		
		
		
		
		
		
	}

}
