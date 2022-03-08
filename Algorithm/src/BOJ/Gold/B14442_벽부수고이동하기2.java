package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14442_벽부수고이동하기2 {
	static int R, C, K, cnt;
	static boolean[][][] visited;
	static int[][] map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Move> queue;
	static class Move {
		int r, c, wallB;

		public Move(int r, int c, int wallB) {
			super();
			this.r = r;
			this.c = c;
			this.wallB = wallB;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C][K+1];
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c)-'0';
			}
		}
		// 입력완료
		queue = new LinkedList<>();
		queue.offer(new Move(0, 0, 0));
		visited[0][0][0] = true;
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
				if (now.r==R-1 && now.c==C-1) return true;
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc)) {
						if (map[nr][nc]==0 && !visited[nr][nc][now.wallB]) {
							visited[nr][nc][now.wallB] = true;
							queue.offer(new Move(nr, nc, now.wallB));
						} else if (map[nr][nc]==1 && now.wallB<K && !visited[nr][nc][now.wallB+1]) {
							visited[nr][nc][now.wallB+1] = true;
							queue.offer(new Move(nr, nc, now.wallB+1));
						}
					}
				}
			}
			cnt++;
		}
		
		return false;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
