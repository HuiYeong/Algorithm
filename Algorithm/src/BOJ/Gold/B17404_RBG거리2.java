package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B17404_RBG거리2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] number = new int[N][3];
		int[][][] dp = new int[N][3][3];
		int[] ans = new int[3];
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			number[i][0] = Integer.parseInt(st.nextToken());
			number[i][1] = Integer.parseInt(st.nextToken());
			number[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(dp[0][0], 2000);
		Arrays.fill(dp[0][1], 2000);
		Arrays.fill(dp[0][2], 2000);
		
		dp[0][0][0] = number[0][0];
		dp[0][1][1] = number[0][1];
		dp[0][2][2] = number[0][2];
		
		for (int i=1;i<N;i++) {
			if (i==N-1) {
				ans[0] = number[i][0] + Math.min(Math.min(dp[i-1][1][1], dp[i-1][2][1]), Math.min(dp[i-1][1][2], dp[i-1][2][2]));
				ans[1] = number[i][1] + Math.min(Math.min(dp[i-1][0][0], dp[i-1][2][0]), Math.min(dp[i-1][0][2], dp[i-1][2][2]));
				ans[2] = number[i][2] + Math.min(Math.min(dp[i-1][0][0], dp[i-1][1][0]), Math.min(dp[i-1][0][1], dp[i-1][1][1]));
				break;
			}
			for (int j=0;j<3;j++) {
				dp[i][0][j] = number[i][0] + Math.min(dp[i-1][1][j], dp[i-1][2][j]);
			}
			for (int j=0;j<3;j++) {
				dp[i][1][j] = number[i][1] + Math.min(dp[i-1][0][j], dp[i-1][2][j]);
			}
			for (int j=0;j<3;j++) {
				dp[i][2][j] = number[i][2] + Math.min(dp[i-1][0][j], dp[i-1][1][j]);
			}
		}
		System.out.println(Math.min(ans[0], Math.min(ans[1], ans[2])));
	}
}
