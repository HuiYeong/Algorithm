package BOJ.Gold;

import java.io.*;
import java.util.*;

public class B1175_배달 {
	static int R, C, time;
	static char[][] map;
	static int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][][][] visit;
	static Queue<Move> queue;
	static class Move {
		int r, c, cnt, giftNum, d;

		public Move(int r, int c, int cnt, int giftNum, int d) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.giftNum = giftNum;
			this.d = d;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C][5][3];
		queue = new LinkedList<>();
		int num = 1;
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c] == 'S') queue.add(new Move(r, c, 0, 0, 0));
				else if (map[r][c] == 'C') {
					if (num++ == 1) map[r][c] = '1';
					else map[r][c] = '2';
				}
			}
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			time++;
			for (int s=0;s<size;s++) {
				Move now = queue.poll();
				for (int d=1;d<=4;d++) {
					if (now.d == d) continue;
					int nr = now.r + dir[d][0];
					int nc = now.c + dir[d][1];
					if (!isIn(nr, nc) || map[nr][nc] == '#' || visit[nr][nc][d][now.giftNum]) continue;
					
					if ((map[nr][nc] == '1' && now.giftNum != 1)||( map[nr][nc] == '2' && now.giftNum != 2)) {
						queue.offer(new Move(nr, nc, now.cnt+1, map[nr][nc]=='1'?1:2, d));
						if (now.cnt+1 == 2) return time;
					} else queue.offer(new Move(nr, nc, now.cnt, now.giftNum, d));
					
					visit[nr][nc][d][now.giftNum] = true;
				}
			}
		}
		
		return -1;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
