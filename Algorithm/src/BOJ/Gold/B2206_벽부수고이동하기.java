package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2206_벽부수고이동하기 {
	static int N, M, cnt;
	static int[][] map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Move> queue;
	static boolean[][][] visited;
	static class Move {
		int r, c, wallB;

		public Move(int r, int c, int wallB) {
			super();
			this.r = r;
			this.c = c;
			this.wallB = wallB;
		}

		@Override
		public String toString() {
			return r+" "+c+" "+wallB;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r=0;r<N;r++) {
			String input = br.readLine();
			for (int c=0;c<M;c++) {
				map[r][c] = input.charAt(c)-'0';
			}
		}
		// 입력완료
		visited = new boolean[N][M][2];
		queue = new LinkedList<>();
		queue.offer(new Move(0, 0, 1));
		visited[0][0][1] = true;
		cnt = 1;
		boolean flag = bfs();
		if (flag) System.out.println(cnt);
		else System.out.println(-1);
	}
	
	static boolean bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i=0;i<size;i++) {
				Move now = queue.poll();
				if (now.r==N-1 && now.c==M-1) return true;
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc)) {
						if (map[nr][nc]==0 && !visited[nr][nc][now.wallB]) {
							visited[nr][nc][now.wallB] = true;
							queue.offer(new Move(nr, nc, now.wallB));
						} else if (map[nr][nc]==1 && now.wallB!=0 && !visited[nr][nc][now.wallB]) {
							visited[nr][nc][now.wallB] = true;
							queue.offer(new Move(nr, nc, 0));
						}
					}
				}
			}
			cnt++;
		}
		
		return false;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<M;
	}
}
