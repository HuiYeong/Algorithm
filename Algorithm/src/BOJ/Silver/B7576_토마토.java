package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7576_토마토 {
	private static int R, C, cnt;
	private static boolean flag;
	private static int[][] field, deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static Queue<tomato> bfs;
	public static class tomato {
		int r, c;

		public tomato(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		field = new int[R][C];
		bfs = new LinkedList<>();
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				field[r][c] = Integer.parseInt(st.nextToken());
				if (field[r][c] == 1) bfs.offer(new tomato(r, c)); // 익은 토마토 좌표 저장
			}
		}
		bfsL(); 
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (field[r][c] == 0) flag = true;
			}
		}
		if (flag) System.out.println(-1);
		else System.out.println(cnt-1);
	}
	
	private static void bfsL() {
		while(!bfs.isEmpty()) {
			int size = bfs.size(); 
			for (int i=0;i<size;i++) { 
				tomato now = bfs.poll();
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc) && field[nr][nc]==0) {
						field[nr][nc] = 1;
						bfs.offer(new tomato(nr, nc));
					}
				}
			}
			cnt++; // 레벨 끝나면 ++
		}
		
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}