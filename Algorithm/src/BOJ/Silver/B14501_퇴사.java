package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14501_퇴사 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] T = new int[1000];
		int[] P = new int[1000];
		int[] dp = new int[1000];
		StringTokenizer st = null;
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken()); 
		}
		for (int i=N;i>0;i--) {
			int next = i+T[i];
			if (next > N+1) dp[i] = dp[i+1];
			else dp[i] = Math.max(dp[i+1], dp[next]+P[i]);
		}
		System.out.println(dp[1]);
	}
}
