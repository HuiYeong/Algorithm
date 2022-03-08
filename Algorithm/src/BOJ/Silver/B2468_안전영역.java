package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2468_안전영역 {
	private static int N, maxH, cnt, MAX;
	private static int[][] map;
	private static boolean[][] check;
	private static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static Queue<move> bfs; 
	public static class move {
		int r, c;

		public move(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		maxH = Integer.MIN_VALUE;
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, map[i][j]);
			}
		}
		// 입력완료
		bfs = new LinkedList<>();
		MAX = Integer.MIN_VALUE;
		for (int i=0;i<=maxH;i++) {
			cnt = 0; check = new boolean[N][N];
			for (int r=0;r<N;r++) {
				for (int c=0;c<N;c++) {
					if (map[r][c]>i && check[r][c]==false) {
						bfsL(i, r, c);
						cnt++;
					}
				}
			}	
			MAX = Math.max(MAX, cnt);
		}
		System.out.println(MAX);
	}
	
	private static void bfsL(int i, int r, int c) {
		bfs.add(new move(r, c));
		check[r][c] = true;
		
		while(!bfs.isEmpty()) {
			move now = bfs.poll();
			for (int d=0;d<4;d++) {
				int nr = now.r+deltas[d][0];
				int nc = now.c+deltas[d][1];
				if (isIn(nr, nc) && map[nr][nc]>i && check[nr][nc]==false) {
					check[nr][nc] = true;
					bfs.add(new move(nr, nc));
				}
			}
		}
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
