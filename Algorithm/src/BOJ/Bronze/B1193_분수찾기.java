package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 수학 문제
 * 1 - 1개, 2 - 2개, 3 - 3개, 4 - 4개 ,,, 임을 확인하여 사이즈 관리
 * 사이즈가 짝수일 때는 분자가 사이즈 값, 홀수일 때는 분모가 사이즈 값이므로 짝수, 홀수를 나눠 생각
 */
public class B1193_분수찾기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int size = 1, num = 0;
		while(true) {
			if (num + size >= N) {
				if (size%2==0) System.out.println((N-num)+"/"+(size-(N-num-1)));
				else System.out.println((size-(N-num-1))+"/"+(N-num));
				System.exit(0);
			}
			num+=size++;
		}
	}
}
