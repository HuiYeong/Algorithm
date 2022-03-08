package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7569_토마토 {
	private static int R, C, I, cnt;
	private static int[][][] field;
	private static int[][] deltas = {{1, 0, 0}, {-1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
	private static Queue<tomato> bfs;
	private static List<tomato> ripe; // 맨 초기 익은 토마토 위치 저장
	public static class tomato {
		int i, r, c;

		public tomato(int i, int r, int c) {
			super();
			this.i = i;
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		I = Integer.parseInt(st.nextToken());
		field = new int[I][R][C];
		bfs = new ArrayDeque<>();
		ripe = new ArrayList<>();
		for (int i=0;i<I;i++) {
			for (int r=0;r<R;r++) {
				st = new StringTokenizer(br.readLine());
				for (int c=0;c<C;c++) {
					field[i][r][c] = Integer.parseInt(st.nextToken());
					if(field[i][r][c]==1) ripe.add(new tomato(i, r, c));
				}
			}
		}
		cnt = 0;
		bfsL(); 
		boolean num = false;
		for (int i=0;i<I;i++) {
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (field[i][r][c] == 0) num = true;
				}
			}
		}
		
		if (num) sb.append(-1);
		else sb.append(cnt-1);
		System.out.println(sb);
	}
	
	private static void bfsL() {
		for (int i=0;i<ripe.size();i++) {
			bfs.add(new tomato(ripe.get(i).i, ripe.get(i).r, ripe.get(i).c)); // 익은 토마토 개수만큼 bfs에 add
		}
		while(!bfs.isEmpty()) {
			int size = bfs.size(); // bfs.add로 인해 사이즈가 계속 변하기 때문에 고정해
			for (int i=0;i<size;i++) { // 현재 레벨에 있는 만큼
				tomato now = bfs.poll();
				for (int d=0;d<deltas.length;d++) {
					int ni = now.i+deltas[d][0];
					int nr = now.r+deltas[d][1];
					int nc = now.c+deltas[d][2];
					if (isIn(ni, nr, nc) && field[ni][nr][nc]==0) {
						field[ni][nr][nc] = 1;
						bfs.add(new tomato(ni, nr, nc));
					}
				}
			}
			cnt++; // 레벨 끝나면 ++
		}
		
	}
	
	private static boolean isIn(int ni, int nr, int nc) {
		return ni>=0 && ni<I && nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
