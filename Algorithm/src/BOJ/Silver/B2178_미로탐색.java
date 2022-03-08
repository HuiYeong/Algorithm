package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2178_미로탐색 {
	private static int[][] maze;
	private static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static int N, M, MIN;
	private static Queue<pair> queue;
	
	public static class pair {
		int r, c, dis;

		public pair(int r, int c, int dis) {
			super();
			this.r = r;
			this.c = c;
			this.dis = dis;
		}

		@Override
		public String toString() {
			return r + " " + c+" "+dis;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		for (int i=0;i<N;i++) {
			String split = br.readLine();
			for (int j=0;j<M;j++) {
				maze[i][j] = split.charAt(j)-'0';
			}
		}
		queue = new ArrayDeque<>();
		bfs(0, 0, 1);
		System.out.println(MIN);
	}
	
	private static void bfs(int r, int c, int dis) {
		queue.offer(new pair(r, c, dis));
		
		while (!queue.isEmpty()) {
			pair now = queue.poll();
			if (now.r==N-1 && now.c==M-1) {
				MIN = now.dis;
				return;
			}
				
			for (int d=0;d<deltas.length;d++) {
				int nr = now.r+deltas[d][0];
				int nc = now.c+deltas[d][1];
				if (isIn(nr, nc) && maze[nr][nc]==1) {
					maze[nr][nc] = 0;
					queue.offer(new pair(nr, nc, now.dis+1));
				}
			}
		}
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<M;
	}
}
