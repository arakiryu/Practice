package kr.ac.kopo.day01.lotto01;

import java.util.Arrays;

public class LottoView {

	public void start() {

		int[] lottos = new int[6];
		
		for(int game = 0; game < 6; game++) {

			lottos = new LottoUtil().getLotto();
			
			System.out.println("game " + (game + 1) + " : " + Arrays.toString(lottos));
		}
	}
}
