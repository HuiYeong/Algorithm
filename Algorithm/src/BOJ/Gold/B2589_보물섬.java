package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2589_보물섬 {
	static int R, C, MAX, cnt;
	static char[][] map;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<rc> bfs;
	static boolean[][] visit;
	static class rc {
		int r, c, d;

		public rc(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		bfs = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			map[r] = input.toCharArray();
		}
		// 입력 완료
		MAX = Integer.MIN_VALUE;
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]=='L') {
					visit[r][c] = true;
					bfs.offer(new rc(r, c, 0));
					bfsL(r, c);
					MAX = Math.max(MAX, cnt);
				}
			}
		}
		System.out.println(MAX);
	}
	
	static void bfsL(int r, int c) {
		while(!bfs.isEmpty()) {
			rc now = bfs.poll();
			for (int d=0;d<4;d++) {
				int nr = now.r+deltas[d][0];
				int nc = now.c+deltas[d][1];
				if (isIn(nr, nc)&&map[nr][nc] =='L' && visit[nr][nc]==false) {
					visit[nr][nc] = true;
					cnt = now.d+1;
					bfs.offer(new rc(nr, nc, now.d+1));
				}
			}
		}
		visit = new boolean[R][C]; // 방문체크 초기화
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
