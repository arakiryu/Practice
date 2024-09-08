package pr_test;

import java.util.Arrays;
import java.util.Random;

public class Lotto_2 {

	public static void main(String[] args) {
		
		boolean[] arr = new boolean[45];
		
		int []numarr = new int[45];
		int []six = new int[6];
		
		Random random = new Random();
		
		
		
		
		for (int i = 0; i < numarr.length; i++) {
			numarr[i] = i+1;
		}
		
		
		
		for (int k = 0; k < 6; k++) {
			int n = 0;
		while(n<6) {
			
		for (int i = 0; i < six.length; i++) {
			six[i] = random.nextInt(45)+1;
			for (int j = 0; j < numarr.length; j++) {			
					
					if(six[i] == numarr[j]) {
						if(arr[j] == true) {
							six[i] = random.nextInt(45)+1;
							j = -1;
						}else {
							arr[j] = true;
							n++;
							break;
						}
			}
		}	
	}
		System.out.println(Arrays.toString(six));
		
			}
		}
	}
}
