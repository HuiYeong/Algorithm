package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1937_욕심쟁이판다 {
	static int N, MAX;
	static int[][] dp, check, map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][N];
		check = new int[N][N];
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력완료
		MAX = Integer.MIN_VALUE;
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (check[i][j]==0) {
					check[i][j] = 1;
					dfs(i, j);
					MAX = Math.max(MAX, dp[i][j]);
				}
			}
		}
		System.out.println(MAX);
	}
	
	static void dfs(int r, int c) {
		for (int d=0;d<4;d++) {			
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc) && map[nr][nc]>map[r][c]) {
				if (dp[nr][nc]!=0 || check[nr][nc]!=0) dp[r][c] = Math.max(dp[r][c], dp[nr][nc]+1);
				else {
					check[nr][nc] = 1;
					dfs(nr, nc);
					dp[r][c] = Math.max(dp[r][c], dp[nr][nc]+1);
				}
			} 
		}
		if (dp[r][c]==0) dp[r][c] = 1;
		return;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
