package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1194_달이차오른다가자 {
	static int R, C, cnt;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static char[][] map;
	static boolean[][][] visited;
	static Queue<Move> queue;

	static class Move {
		int r, c, key;

		public Move(int r, int c, int key) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
		}

		@Override
		public String toString() {
			return "Move [r=" + r + ", c=" + c + ", key=" + key + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C][1<<6];
		queue = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			String input = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c] == '0') {
					queue.offer(new Move(r, c, 0));
					visited[r][c][0] = true;
				}
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Move now = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = now.r + deltas[d][0];
					int nc = now.c + deltas[d][1];
					int key = now.key;

					if (isIn(nr, nc) && map[nr][nc] != '#' & !visited[nr][nc][key]) {
						if (map[nr][nc]=='1') return cnt+1;
						
						if ('a'<=map[nr][nc] && map[nr][nc]<='f') {
							key |= (1 << map[nr][nc] - 'a');
						}
						
						if ('A'<=map[nr][nc] && map[nr][nc]<='F') {
							if ((key & (1 << (map[nr][nc]-'A')))==0) {
								continue;
							}
						}
						
						visited[nr][nc][key] = true;
						queue.offer(new Move(nr, nc, key));
						
					}
				}
			}
			cnt++;
		}

		return -1;
	}

	static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}