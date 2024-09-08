package kr.ac.kopo.day01.lotto01;

import java.util.Random;

public class LottoUtil {
	
	public int[] getLotto() {
		
		int[] lottos = new int[6];
		Random ran = new Random();
		

		for(int i = 0; i < lottos.length; i++) {
			lottos[i] = ran.nextInt(45) + 1;
			for(int j = 0; j < i; j++) {
				if(lottos[i] == lottos[j]) {
					i--;
					break;
				}
			}
		}
		
		
		return lottos;
	}
	
	
}
