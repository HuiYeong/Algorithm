package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17836_공주님을구해라 {
	static int R, C, T, cnt;
	static int[][] map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][][] visited;
	static Queue<Move> queue;
	static class Move {
		int r, c, gram;

		public Move(int r, int c, int gram) {
			super();
			this.r = r;
			this.c = c;
			this.gram = gram;
		}

		@Override
		public String toString() {
			return r+" "+c+" "+gram;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C][2];
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력완료
		queue = new LinkedList<>();
		queue.offer(new Move(0, 0, 0));
		visited[0][0][0] = true; // 방문체크
		boolean flag = bfs();
		if (flag && cnt<=T) System.out.println(cnt);
		else System.out.println("Fail");
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
					if (isIn(nr, nc) && !visited[nr][nc][now.gram]) {
						if (now.gram==1) { // 벽 부실 수 있음
							visited[nr][nc][now.gram] = true;
							queue.offer(new Move(nr, nc, now.gram));
						} else if (now.gram==0 && map[nr][nc]!=1){
							if (map[nr][nc]==2) {
								queue.offer(new Move(nr, nc, 1));
							} else queue.offer(new Move(nr, nc, now.gram));
							visited[nr][nc][now.gram] = true; 
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
