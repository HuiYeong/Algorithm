package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2118_두개의탑 {
	private static int N, MAX, sum, top;
	private static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		for (int i=0;i<N;i++) {
			top = Integer.parseInt(br.readLine());
			sum+=top;
			dp[i+1] = dp[i]+top; // 누적합
		}
		MAX = Integer.MIN_VALUE;
		for (int i=0;i<N+1;i++) {
			for (int j=i;j<N+1;j++) {
				MAX = Math.max(MAX, Math.min(dp[j]-dp[i], sum-(dp[j]-dp[i])));
			}
		}
		System.out.println(MAX);
	}
}
