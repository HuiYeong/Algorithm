package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4179_불 {
	static int R, C, cnt;
	static boolean flag;
	static int[][] deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static boolean[][] visitedJ,  visitedF;
	static Queue<Move> jihoon, fire;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visitedJ = new boolean[R][C];
		visitedF = new boolean[R][C];
		jihoon = new LinkedList<>();
		fire = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c]=='J') {
					jihoon.offer(new Move(r, c));
					visitedJ[r][c] = true;
				} else if (map[r][c]=='F') {
					fire.offer(new Move(r, c));
					visitedF[r][c] = true;
				}
			}
		}
		
		bfs();
		System.out.println("IMPOSSIBLE");
	}
	
	static void bfs() {
		while(!jihoon.isEmpty()) {
			// 지훈이 먼저 이동
			int sizeJ = jihoon.size();
			for (int i=0;i<sizeJ;i++) {
				Move now = jihoon.poll();
				if (!visitedF[now.r][now.c]) move(now, 'J');
			}
			
			// 불 이동
			int sizeF = fire.size();
			for (int i=0;i<sizeF;i++) {
				Move now = fire.poll();
				move(now, 'F');
			}
			
			cnt++;
		}
	}
	
	static void move(Move now, char mode) {
		flag = false;
		for (int d=0;d<4;d++) {
			int nr = now.r+deltas[d][0];
			int nc = now.c+deltas[d][1];
			if (isIn(nr, nc)) {
				if (mode=='J' && map[nr][nc]!='#' && !visitedJ[nr][nc] && !visitedF[nr][nc]) {
					visitedJ[nr][nc] = true;
					jihoon.offer(new Move(nr, nc));
				} else if (mode=='F' && map[nr][nc]!='#' && !visitedF[nr][nc]) {
					visitedF[nr][nc] = true;
					fire.offer(new Move(nr, nc));
				}
			} else flag = true;
		}
		
		if (mode=='J' && flag) {
			System.out.println(cnt+1);
			System.exit(0);
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
