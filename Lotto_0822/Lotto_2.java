package pr_test;


import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        for (int game = 1; game <= 6; game++) {  // 6게임 생성
            int[] randomarr = new int[6];
            int[] num = new int[45];

            for (int i = 0; i < num.length; i++) {
                num[i] = i + 1;
            }

            int n = 0;

            while (n < 6) {
                boolean tf = false;
                int randomindex = random.nextInt(num.length - n);
                int selectnum = num[randomindex];

                for (int i = 0; i < n; i++) {
                    if (randomarr[i] == selectnum) {
                        tf = true;
                        break;
                    }
                }

                if (!tf) {
                    randomarr[n] = selectnum;
                    int temp = num[num.length - n - 1];
                    num[num.length - n - 1] = num[randomindex];
                    num[randomindex] = temp;
                    n++;
                }
            }

        

            // 각 게임의 결과를 한 줄에 출력
            System.out.print("게임 " + game + " : ");
            for (int number : randomarr) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }
}