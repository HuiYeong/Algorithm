package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2003_수들의합2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		int[] dp = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			dp[i+1] = dp[i]+numbers[i];
		}
		
		int ans = 0;
		for (int i=0;i<=N;i++) {
			for (int j=i+1;j<=N;j++) {
				if (Math.abs(dp[j]-dp[i])==M) ans++;
			}
		}
		System.out.println(ans);
	}
}
