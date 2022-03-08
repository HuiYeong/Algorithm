package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1987_알파벳 {
	private static int R, C, MAX;
	private static char[][] arr;
	private static boolean[] alpha;
	private static int[][] deltas = {{-1,0}, {0,1}, {1,0}, {0,-1}}; // 상 우 하 좌
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][];
		alpha = new boolean[26];
		for (int i=0;i<R;i++) {
			arr[i] = br.readLine().toCharArray();
		}
		alpha[arr[0][0]-65] = true;
		dfs(0, 0, 1);
		System.out.println(MAX);
	}
	
	private static void dfs(int r, int c, int cnt) {
		for (int d=0;d<4;d++) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc) && !alpha[arr[nr][nc]-65]) {
				alpha[arr[nr][nc]-65] = true;
				dfs(nr, nc, cnt+1);
			}
		}
		alpha[arr[r][c]-65] = false;
		MAX = Math.max(MAX, cnt);
		cnt = 0;
		return;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
