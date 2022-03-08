package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2933_미네랄 {
	static int R, C, N, num, idx, downMAX[], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean visit[][], flag;
	static Queue<Move> queue;
	static PriorityQueue<Move> pq;
	static char map[][];
	static class Move implements Comparable<Move>{
		int r, c;

		public Move(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Move [r=" + r + ", c=" + c + "]";
		}

		@Override
		public int compareTo(Move o) {
			if (this.r == o.r) Integer.compare(o.c, this.c);
			return Integer.compare(o.r, this.r);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		queue = new LinkedList<>();
		pq = new PriorityQueue<>();
		for (int r=0;r<R;r++) {
			map[r] = br.readLine().toCharArray();
		}
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) { // i : 짝수 (왼 -> 오), 홀수 (오 -> 왼)
			flag = false;
			num = Integer.parseInt(st.nextToken());
			check(i%2==0?0:1); // 없앨 미네랄 확인
			clusterCheck();
			if (flag) downCheck();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				sb.append(map[r][c]);
			}
		    sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static void check(int type) {
		num = R-num;
		if (type==0) {
			for (int c=0;c<C;c++) {
				if (map[num][c]=='x') {
					map[num][c] = '.';
					return;
				}
			}
		} else {
			for (int c=C-1;c>=0;c--) {
				if (map[num][c]=='x') {
					map[num][c] = '.';
					return;
				}
			}
		}
	}
	
	static void clusterCheck() {
		visit = new boolean[R][C];
		for (int r=R-1;r>=0;r--) {
			for (int c=0;c<C;c++) {
				if (map[r][c] == 'x' && !visit[r][c]) {
					queue.offer(new Move(r, c));
					visit[r][c] = true;
					if (r!=R-1) {
						downMAX = new int[C];
						Arrays.fill(downMAX, -1);
						downMAX[c] = Math.max(downMAX[c], r);
						pq.offer(new Move(r, c));
						flag = true;
					}
					bfs();
				}
			}
		}
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			Move now = queue.poll();
			for (int d=0;d<4;d++) {
				int nr = now.r + dir[d][0];
				int nc = now.c + dir[d][1];
				if (!isIn(nr, nc) || visit[nr][nc] || map[nr][nc] == '.') continue;
				visit[nr][nc] = true;
				Move newM = new Move(nr, nc);
				queue.offer(newM);
				if (flag) {
					pq.offer(newM);
					downMAX[nc] = Math.max(downMAX[nc], nr);
				}
			}
		}
	}
	
	static void downCheck() {
		idx = R;
		for (int c=0;c<C;c++) {
			if (downMAX[c] == -1) continue;
			for (int r=downMAX[c]+1;r<R;r++) {
				if (map[r][c] == 'x') {
					idx = Math.min(idx, r-downMAX[c]-1);
					break;
				}
			}
			idx = Math.min(idx, R-downMAX[c]-1);
		}
		down();
	}
	
	static void down() {
		while(!pq.isEmpty()) {
			Move now = pq.poll();
			map[now.r][now.c] = '.';
			map[now.r+idx][now.c] = 'x';
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
