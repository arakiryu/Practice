package re_pr;

import java.util.Arrays;
import java.util.Calendar;

public class CallCalendar {
		
	
	
	Calendar cal = Calendar.getInstance();
	
		
		
		
		
	//년도 입력 한다면 1월 부터 12월 까지 나옴
	public void selectYearAllMonth(int year) {
		int[] arr = new int[31];
		String[] weekdays = {"일","월","화","수","목","금","토"};
		int jplus = 1;
		//뭐부터?
		//현재 년도니까 1월 부터 12월 까지 반복하면서
		//1 ~ 31 일을 출력해야하는데
		//1일이 어떤 요일에 있는지 알아야함
		//그리고 7일씩 끊어서 출력
		
		for (int i = 0; i < 12; i++) {
			
			 System.out.println("<" + year + "년" + (i+1) + "월" + ">");
             System.out.println("--------------------------------------");
             for (String day : weekdays) {
                 System.out.printf("%3s", day);
             }
             System.out.println();    
             //1일이 어떤 요일에 있는지 아는 메서드 
             int startdate = getdate(year , i); //항상 월 마다 첫번째로 시작하는 값이 다르기 때문에 반복하기로 결정
             cal.set(year, i,1); // 다시 값 설정
             int maximumdate= cal.getActualMaximum(Calendar.DAY_OF_MONTH);
             
            //첫 주 시작전 공백 출력
             for (int k = 0; k < startdate-1; k++) {
				System.out.print("  ");
			}
             
             //변수 초기화
             jplus = 1;
             
             //첫번째 날짜부터 7일씩 끊어서 출력
             for (int j = 0; j < maximumdate; j++) { 
            	 
            	 arr[j] = jplus++;
            	 System.out.printf("%3d" , arr[j]);
            	 
            	 if((startdate + j) % 7 == 0) {
            		 System.out.println();
            	 }
			}
             System.out.println();
             
		}
	
	}
	
	
	
	
	
	
	
	public void selectYearAndMonth(int year, int month) {
		int[] arr = new int[31];
		
		String[] weekdays = {"일","월","화","수","목","금","토"};
		
		int jplus = 1;
		
		 System.out.println("<" + year + "년" + month + "월" + ">");
         System.out.println("--------------------------------------");
         for (String day : weekdays) {
             System.out.printf("%3s", day);
         }
		System.out.println();
		
		int startdate = getdate(year , month-1);
		cal.set(year, month-1,1); 
		int maximumdate= cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		 
		//첫 주 시작전 공백 출력
        for (int k = 0; k < startdate-1; k++) {
			System.out.print("    ");
		}
		
		 
		 
		 
		 for (int j = 0; j < maximumdate; j++) { 
        	 
        	 arr[j] = jplus++;
        	 System.out.printf("%3d" , arr[j]);
        	 
        	 if((startdate + j) % 7 == 0) {
        		 System.out.println();
        	 }
		}
		
		
		 
		
	}
	
	
	
	
	
	public int getdate(int year , int i) {
		cal.set(year, i, 1);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	
	
	
	
}
