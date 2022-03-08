package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16441_아기돼지와늑대 {
	static int R, C;
	static int[][] deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static boolean[][] visited;
	static Queue<Move> wolf;
	static class Move {
		int r, c;

		public Move(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return r+" "+c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[R][C];
		map = new char[R][C];
		wolf = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c]=='W') {
					visited[r][c] = true;
					wolf.offer(new Move(r, c));
				}
			}
		}
		// 입력완료
		bfs();
		
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]=='.' && !visited[r][c]) map[r][c] = 'P';
				sb.append(map[r][c]+"");
			} sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void bfs() {
		while(!wolf.isEmpty()) {
			int size = wolf.size();
			for (int i=0;i<size;i++) {
				Move now = wolf.poll();
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc) && map[nr][nc]!='#' && !visited[nr][nc]) {
						if (map[nr][nc]=='+') ice(nr, nc, d);
						else {
							visited[nr][nc] = true;
							wolf.offer(new Move(nr, nc));
						}
					}
				}
			}
		}
	}
	
	static void ice(int r, int c, int d) {
		while(true) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (visited[nr][nc]) return;
			
			if (map[nr][nc]=='#') { // 벽에 부딪칠 때
				visited[r][c] = true;
				wolf.offer(new Move(r, c));
				return;
			} else if (map[nr][nc]=='.') { // 초원으로 나왔을 때
				visited[nr][nc] = true;
				wolf.offer(new Move(nr, nc));
				return;
			} 
			r = nr; c = nc;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
