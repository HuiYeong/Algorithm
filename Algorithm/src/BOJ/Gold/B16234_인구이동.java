package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16234_인구이동 {
	static int N, L, R, cnt, sum;
	static boolean flag;
	static int[][] country, deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visit;
	static Queue<Move> bfs, border;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		country = new int[N][N];
		bfs = new LinkedList<>();
		border = new LinkedList<>();
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				country[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료
		
		while(true) {
			flag = false;
			print();
			visit = new boolean[N][N];
			for (int r=0;r<N;r++) {
				for (int c=0;c<N;c++) {
					if (!visit[r][c]) {
						sum = country[r][c];
						bfs.offer(new Move(r, c));
						border.offer(new Move(r, c));
						visit[r][c] = true;
						borderLineCheck();
						populationMove();
					}
				}
			}
			if (!flag) break;
			cnt++;
		}
		System.out.println(cnt);
	}
	
	static void print() {
		for(int r=0;r<N;r++) {
			for (int c=0;c<N;c++) {
				System.out.print(country[r][c]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void populationMove() {
		int size = border.size();
		while(!border.isEmpty()) {
			Move now = border.poll();
			if (sum>0) {
				country[now.r][now.c] = (int)Math.floor(sum/size); 
			}
		}
	}
	
	static void borderLineCheck() {
		while(!bfs.isEmpty()) {
			Move now = bfs.poll();
			
			for (int d=0;d<4;d++) {
				int nr = now.r+deltas[d][0];
				int nc = now.c+deltas[d][1];
				
				if (isIn(nr, nc) && !visit[nr][nc]) {
					int diff = Math.abs(country[now.r][now.c]-country[nr][nc]);
					if (diff>=L && diff<=R) {
						visit[nr][nc] = true;
						sum+=country[nr][nc];
						bfs.offer(new Move(nr, nc));
						border.offer(new Move(nr, nc));
						flag = true;
					}
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
