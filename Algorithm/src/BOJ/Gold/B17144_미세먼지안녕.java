package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17144_미세먼지안녕 {
	static int R, C, T, cnt, dirCnt, next;
	static int[][] map, deltas = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}, deltasD = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int[][][] temp;
	static Queue<Dust> queue;
	static Dust airClean;
	static class Dust {
		int r, c;

		public Dust(int r, int c) {
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
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c]==-1) airClean = new Dust(r, c);
			}
		}
		for (int i=0;i<T;i++) {
			temp = new int[R][C][2];
			getDust(); // 먼지 위치 파악
			spreadCnt(); // 미세먼지 뿌리는 값 확인
			spread(); // 미세먼지 뿌리기
			purify((airClean.r-1)+deltas[0][0], airClean.c+deltas[0][1], deltas); // 공기청정기 위쪽 작동
			purify(airClean.r+deltas[0][0], airClean.c+deltas[0][1], deltasD); // 공기청정기 아래쪽 작동
		}
		System.out.println(cntCheck());
		
	}
	
	static void getDust() {
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]>0) queue.offer(new Dust(r, c));
			}
		}
	}
	
	static void spreadCnt() {
		while(!queue.isEmpty()) {
			Dust now = queue.poll();
			int amount = map[now.r][now.c];
			dirCnt = 0;
			for (int d=0;d<4;d++) {
				int nr = now.r+deltas[d][0];
				int nc = now.c+deltas[d][1];
				if (isIn(nr, nc) && map[nr][nc]!=-1) {
					temp[nr][nc][0] += amount/5;
					dirCnt++;
				}
			}
			temp[now.r][now.c][1] = amount/5 * dirCnt;
		}
	}
	
	static void spread() {
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				map[r][c] += temp[r][c][0];
				map[r][c] -= temp[r][c][1];
			}
		}
	}
	
	static void purify(int r, int c, int[][] dir) {
		int value = map[r][c];
		map[r][c] = 0;
		int d = 0;
		
		while(true) {
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if (!isIn(nr, nc)) {
				d++;
				continue;
			}
			if (((dir[1][0]==-1 && airClean.r-1==nr) || dir[1][0]==1 && airClean.r==nr) && airClean.c==nc) return;
			next = map[nr][nc];
			map[nr][nc] = value;
			r = nr; c = nc; value = next;
		}
	}
	
	static int cntCheck() {
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]>0) cnt+=map[r][c];
			}
		}
		return cnt;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
