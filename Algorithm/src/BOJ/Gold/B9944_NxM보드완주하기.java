package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B9944_NxM보드완주하기 {
	static int R, C, MIN, TC;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static boolean[][] visited, newVisit;
	static Queue<Move> bfs;
	static class Move {
		int r, c, cnt;
		boolean[][] visit;

		public Move(int r, int c, int cnt, boolean[][] visit) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.visit = visit;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		StringTokenizer st = null;
		TC = 1;
		while((input = br.readLine())!=null) {
			st = new StringTokenizer(input);
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			newVisit = new boolean[R][C];
			bfs = new LinkedList<>();
			MIN = Integer.MAX_VALUE;
			for (int r=0;r<R;r++) {
				String inputMap = br.readLine();
				map[r] = inputMap.toCharArray();
			}
			
			// 하나씩 검사
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (map[r][c]=='.') {
						visited = new boolean[R][C];
						visited[r][c] = true;
						bfs.offer(new Move(r, c, 0, visited));
						moveOnBoard();
					}
				}
			}
			
			if (MIN==Integer.MAX_VALUE) System.out.println("Case "+(TC++)+": -1");
			else System.out.println("Case "+(TC++)+": "+MIN);
		}
	}
	
	static void moveOnBoard() {
		while(!bfs.isEmpty()) {
			int size = bfs.size();
			for (int s=0;s<size;s++) {
				Move now = bfs.poll();
				if (now.cnt<MIN && now.cnt<1000000) {
					for (int d=0;d<4;d++) {
						int nr = now.r+deltas[d][0];
						int nc = now.c+deltas[d][1];
						if (isIn(nr, nc) && !now.visit[nr][nc] && map[nr][nc]!='*') moveNow(nr, nc, d, now);
					}
					if(checkMove(now)) MIN = Math.min(MIN, now.cnt);
				}
			}
		}
	}
	
	static void moveNow(int r, int c, int d, Move now) {
		cloneVisit(now);
		newVisit[r][c] = true;
		while(true) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (!isIn(nr, nc) || map[nr][nc]=='*' || newVisit[nr][nc]) {
				bfs.offer(new Move(r, c, now.cnt+1, newVisit));
				return;
			}
			newVisit[nr][nc] = true;
			r = nr; c = nc;
		}
	}
	
	static void cloneVisit(Move now) {
		newVisit = new boolean[R][C];
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				newVisit[r][c] = now.visit[r][c];
			}
		}
	}
	
	static boolean checkMove(Move now) {
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]=='.' && !now.visit[r][c]) return false;
			}
		}
		return true;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
