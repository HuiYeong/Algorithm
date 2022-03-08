package BOJ.Gold;

import java.io.*;
import java.util.*;

public class B6087_레이저통신 {
	static int R, C;
	static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static char[][] map;
	static int[][] ans;
	static Queue<Laser> queue;
	static List<Laser> lasers;
	public static class Laser {
		int r, c, cnt, d;

		public Laser(int r, int c, int cnt, int d) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		ans = new int[R][C];
		lasers = new ArrayList<>();
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c]=='C') lasers.add(new Laser(r, c, 0, -1));
			}
		}
		
		for (int r=0;r<R;r++) Arrays.fill(ans[r], 987654321);
		
		bfs();
		System.out.println(ans[lasers.get(1).r][lasers.get(1).c]);
	}
	
	static void bfs() {
		Laser start = lasers.get(0);
		queue.offer(start);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i=0;i<size;i++) {
				Laser now = queue.poll();
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc) && map[nr][nc]!='*') {
						int nextCnt = (now.d!=d && now.d!=-1)?now.cnt+1:now.cnt;
						if (ans[nr][nc] >= nextCnt) {
							ans[nr][nc] = nextCnt;
							queue.offer(new Laser(nr, nc, nextCnt, d));
						}
					}
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
