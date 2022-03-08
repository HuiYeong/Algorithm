package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11441_합구하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		int[] dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			dp[i+1] = dp[i]+numbers[i];
		}
		int M = Integer.parseInt(br.readLine());
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
