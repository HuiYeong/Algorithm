package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2839_설탕배달 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int origin = N;
		int Answer3 = 0;
		int Answer5 = 0;
		int Answer = 0;
		
		// 3먼저
		while(true) {
			if (N>0 && (N%5==0 || N==0)) {
				Answer3+=N/5;
				break;
			}
			else if (N<0) {
				Answer3 = -1;
				break;
			}
			N-=3;
			Answer3++;
		}
		
		// 5
		N = origin;
		while(true) {
			if (N>0 && (N%3==0 || N==0)) {
				Answer5+=N/3;
				break;
			}
			else if (N<0) {
				Answer5=-1;
				break;
			}
			N-=5;
			Answer5++;
		}
		
		if (Answer3==-1 && Answer5==-1) Answer = -1;
		else if (Answer3==-1||Answer5==-1) Answer = Answer3==-1?Answer5:Answer3;
		else {
			Answer = Math.min(Answer3, Answer5);
		}
		
		System.out.println(Answer);
	}
}
