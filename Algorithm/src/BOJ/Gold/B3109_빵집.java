package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3109_빵집 {
	private static char[][] map;
	private static int[][] check;
	private static int R, C, cnt;
	private static int[][] deltas = {{-1, 1}, {0, 1}, {1, 1}};
	private static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		check = new int[R][C];
		for (int r=0;r<R;r++) {
			String temp = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = temp.charAt(c);
			}
		}
		cnt = 0;
		for (int i=0;i<R;i++) {
			flag = false;
			backtrack(i, 0);
		}
		backtrack(0, 0);
		System.out.println(cnt);
	}
	
	private static void backtrack(int r, int c) {
		if (c!=0 && !Available(r, c)) {
			return;
		}
		if (c==C-1) {
			cnt++;
			flag = true;
		}
		for (int d = 0; d < deltas.length; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			backtrack(nr, nc);
			if (flag) return;
		}
	}
	
	private static boolean Available(int nr, int nc) {
		if (isIn(nr, nc) && map[nr][nc] == '.' && check[nr][nc] == 0) {
			check[nr][nc] = 1;
			return true;
		}
		return false;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
