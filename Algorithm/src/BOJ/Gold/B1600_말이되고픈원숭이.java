package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1600_말이되고픈원숭이 {
	static int R, C, K;
	static int[][] map;
	static int[][] deltaM = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 원숭이 이동
	static int[][] deltaH = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } }; // 말이동
	static boolean[][][] visited;
	static Queue<Monkey> queue;
	static class Monkey {
		int r, c, horse;

		public Monkey(int r, int c, int horse) {
			super();
			this.r = r;
			this.c = c;
			this.horse = horse;
		}

		@Override
		public String toString() {
			return r+" "+c+" "+horse;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());	
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		queue = new LinkedList<>();
		visited = new boolean[R][C][K+1];
		queue.offer(new Monkey(0, 0, 0));
		visited[0][0][0] = true;
		System.out.println(bfs());
	}
	
	static int bfs() {
		int cnt = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i=0;i<size;i++) {
				Monkey now = queue.poll();
				if(now.r == R-1 && now.c == C-1) return cnt;
				
				move(deltaM, now, true);
				if(now.horse < K) move(deltaH, now, false);
			}
			cnt++;
		}
		return -1;
	}
	
	static void move(int[][] deltas, Monkey now, boolean isMonkey) {
		// 자식 탐색
		for (int d = 0; d < deltas.length; d++) {
			int nr = now.r + deltas[d][0];
			int nc = now.c + deltas[d][1];

			if (isIn(nr, nc) && map[nr][nc] == 0) {
				int horseMove = isMonkey ? now.horse : now.horse + 1;
				if (!visited[nr][nc][horseMove]) {
					Monkey newMonkey = new Monkey(nr, nc, horseMove);
					queue.add(newMonkey);
					visited[nr][nc][horseMove] = true;
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
