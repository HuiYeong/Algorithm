package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14940_쉬운최단거리 {
	static int R, C;
	static int[][] map, ans, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<move> queue;
	static class move {
		int r, c, cnt;

		public move(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return r+" "+c+" "+cnt;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		ans = new int[R][C];
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c]==2) {
					map[r][c] = 0;
					queue.offer(new move(r, c, 0));
				}
			}
		}
		// 입력완료
		
		bfs();
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]==1) ans[r][c]=-1;
				sb.append(ans[r][c]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i=0;i<size;i++) {
				move now = queue.poll();
				ans[now.r][now.c] = now.cnt;
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc) && map[nr][nc]==1) {
						map[nr][nc] = 0;
						queue.offer(new move(nr, nc, now.cnt+1));
					}
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
