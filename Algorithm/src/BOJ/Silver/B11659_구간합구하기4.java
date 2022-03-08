package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11659_구간합구하기4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] numbers = new int[N];
		int[] dp = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			dp[i+1] = dp[i] + numbers[i];
		}
		int start = 0, end = 0;
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			sb.append(dp[end]-dp[start-1]+"\n");
		}
		System.out.println(sb);
	}
}
