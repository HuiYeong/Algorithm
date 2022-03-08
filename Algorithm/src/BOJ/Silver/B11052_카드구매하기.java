package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11052_카드구매하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		dp[1] = Integer.parseInt(st.nextToken());
		for (int i=2;i<=N;i++) {
			dp[i] = Integer.parseInt(st.nextToken());
			// i가 가질 수 있는 최대값 구하기
			int start = 1, end = i-1;
			while(start<=end) { // 가질 수 있는 모든 경우의 수
				dp[i] = Math.max(dp[i], dp[start++]+dp[end--]);
			}
		}
		System.out.println(dp[N]);
	}
}
