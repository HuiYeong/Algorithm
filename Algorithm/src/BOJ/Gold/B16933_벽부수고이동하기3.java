package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16933_벽부수고이동하기3 {
	static int R, C, K, cnt, size;
	static int[][] map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][][] visited;
	static boolean flag;
	static Queue<Move> queue;
	static class Move {
		int r, c, wallB;
		boolean morning;

		public Move(int r, int c, int wallB, boolean morning) {
			super();
			this.r = r;
			this.c = c;
			this.wallB = wallB;
			this.morning = morning;
		}

		@Override
		public String toString() {
			return r+" "+c+" "+wallB+" "+morning;
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
		// 입력 완료
		queue = new LinkedList<>();
		queue.add(new Move(0, 0, 0, true));
		visited[0][0][0] = true;
		System.out.println(bfs());
	}
	
	static int bfs() {
		while (!queue.isEmpty()) {
			cnt++;
			size = queue.size();
			for (int i=0;i<size;i++) {
				Move now  = queue.poll();
				
				if (now.r==R-1 && now.c==C-1) return cnt;
				
				if (!now.morning) {
					queue.offer(new Move(now.r, now.c, now.wallB, !now.morning));
					visited[now.r][now.c][now.wallB] = true;
				}
				loop(now);
			}	
		}
		
		return -1;
	}
	
	static void loop(Move now) {
		for (int d=0;d<4;d++) {
			int nr = now.r+deltas[d][0];
			int nc = now.c+deltas[d][1];
			if(isIn(nr, nc)) {
				if (map[nr][nc]==1 && now.morning && now.wallB<K && !visited[nr][nc][now.wallB+1]) {
					visited[nr][nc][now.wallB+1] = true;
					queue.offer(new Move(nr, nc, now.wallB+1, !now.morning));
				} else if (map[nr][nc]==0 && !visited[nr][nc][now.wallB]) {
					visited[nr][nc][now.wallB] = true;
					queue.offer(new Move(nr, nc, now.wallB, !now.morning));
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
