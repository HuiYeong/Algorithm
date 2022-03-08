package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2146_다리만들기 {
	static int N, len, MIN;
	static boolean[][] visited;
	static int[][] map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Move> queue;
	static class Move{
		int r, c;

		public Move(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		//입력완료
		
		// 섬 번호 새겨주기
		int island = 2;
		for (int r=0;r<N;r++) {
			for (int c=0;c<N;c++) {
				if (map[r][c]==1) {
					map[r][c] = island;
					dfs(r, c, island);
					island++;
				}
				
			}
		}
		MIN = Integer.MAX_VALUE;
		// 섬 사이의 다리 길이 구하기
		for (int r=0;r<N;r++) {
			for (int c=0;c<N;c++) {
				if (map[r][c]!=0) {
					queue = new LinkedList<>();
					queue.offer(new Move(r, c));
					visited = new boolean[N][N];
					visited[r][c] = true;
					len = 0;
					len = bfsL(map[r][c]);
					MIN = Math.min(MIN, len);
				}
			}
		}
		System.out.println(MIN);
	}
	
	static int bfsL(int island) {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i=0;i<size;i++) {
				Move now = queue.poll();
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc) && map[nr][nc]!=island && !visited[nr][nc]) {
						if (map[nr][nc]!=0) return len;
						else if (map[nr][nc]==0) {
							visited[nr][nc] = true;
							queue.offer(new Move(nr, nc));
						}
					}
				}
			}
			len++;
		}
		return Integer.MAX_VALUE;
	}
	
	static void dfs(int r, int c, int island) {
		for (int d=0;d<4;d++) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc) && map[nr][nc]==1) {
				map[nr][nc] = island;
				dfs(nr, nc, island);
			}
		}
		return;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}	
