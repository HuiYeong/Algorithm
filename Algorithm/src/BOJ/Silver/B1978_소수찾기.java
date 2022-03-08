package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1978_소수찾기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		for (int i=0;i<N;i++) {
			boolean flag = true;
			for (int j=2;j<numbers[i];j++) {
				if (numbers[i]%j==0) {
					flag = false;
					break;
				}
			}
			if (numbers[i]==1) flag = false;
			if (flag) ans++;
		}
		System.out.println(ans);
	}
}
