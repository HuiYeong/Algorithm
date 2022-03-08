package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7562_나이트의이동 {
	static int I, goalR, goalC, cnt;
	static Queue<move> chess;
	static int[][] map;
	static int[][] deltas = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
	static class move {
		int r, c;

		public move(int r, int c) {
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
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			chess = new LinkedList<>();
			I = Integer.parseInt(br.readLine());
			map = new int[I][I];
			StringTokenizer st = new StringTokenizer(br.readLine());
			chess.add(new move(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			st = new StringTokenizer(br.readLine());
			goalR = Integer.parseInt(st.nextToken());
			goalC = Integer.parseInt(st.nextToken());
			cnt = 0;
			map[chess.peek().r][chess.peek().c] = 1;
			bfs();
			sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}
	
	static void bfs() {
		stop:while(!chess.isEmpty()) {
			int size = chess.size();
			for (int i=0;i<size;i++) {
				move now = chess.poll();
				if (now.r == goalR && now.c == goalC) break stop;
				for (int d=0;d<deltas.length;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc) && map[nr][nc]==0) {
						map[nr][nc] = 1;
						chess.add(new move(nr, nc));
					}
				}
			}
			cnt++;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<I && nc>=0 && nc<I;
	}
}
