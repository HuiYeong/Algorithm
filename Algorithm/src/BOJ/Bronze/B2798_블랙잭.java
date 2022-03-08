package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2798_블랙잭 {
	private static int[] choosed;
	private static int N, M, max, sum;
	private static int[] card;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		card = new int[N];
		choosed = new int[3];
		st = new StringTokenizer(in.readLine(), " ");
		for (int i=0;i<N;i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		combination(0, 0);
		System.out.println(max);
	}
	
	private static void combination(int cnt, int start) {
		if (cnt==3) {
			sum = 0;
			for (int i=0;i<3;i++) {
				sum += choosed[i];
			}
			if (sum <= M) {
				max = Math.max(max, sum);
			}
			return;
		}
		
		for (int i=start;i<N;i++) {
			choosed[cnt] = card[i];
			combination(cnt+1, i+1);
		}
	}
}
