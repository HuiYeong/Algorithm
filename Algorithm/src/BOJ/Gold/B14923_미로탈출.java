package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14923_미로탈출 {
	static int R, C, sR, sC, eR, eC, cnt;
	static int[][] map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][][] visited;
	static Queue<Move> queue;
	static class Move {
		int r, c, magic;

		public Move(int r, int c, int magic) {
			super();
			this.r = r;
			this.c = c;
			this.magic = magic;
		}

		@Override
		public String toString() {
			return r+" "+c+" "+magic;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C][2];
		queue = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		sR = Integer.parseInt(st.nextToken())-1;
		sC = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		eR = Integer.parseInt(st.nextToken())-1;
		eC = Integer.parseInt(st.nextToken())-1;
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		queue.offer(new Move(sR, sC, 0));
		visited[sR][sC][0] = true;
		boolean flag = bfs();
		if (flag) System.out.println(cnt);
		else System.out.println(-1);
	}
	
	static boolean bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i=0;i<size;i++) {
				Move now = queue.poll();
				if (now.r==eR && now.c==eC) return true;
				
				go(now, false);
				if (now.magic==0) go(now, true);
			}
			cnt++;
		}
		return false;
	}
	
	static void go (Move now, boolean isMagic) {
		for (int d=0;d<4;d++) {
			int nr = now.r+deltas[d][0];
			int nc = now.c+deltas[d][1];
			if (isIn(nr, nc)) {
				if (!isMagic && map[nr][nc]==0 && !visited[nr][nc][now.magic]) {
					visited[nr][nc][now.magic] = true;
					queue.offer(new Move(nr, nc, now.magic));
				}
				
				else if (isMagic && !visited[nr][nc][1]) {
					visited[nr][nc][1] = true;
					queue.offer(new Move(nr, nc, 1));
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
