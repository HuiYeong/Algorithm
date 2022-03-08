package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5558_치즈 {
	static int R, C, N, time;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static boolean[][][] visit;
	static boolean[] hardCheck;
	static Queue<Cheese> cheeses;
	static class Cheese {
		int r, c, hard;

		public Cheese(int r, int c, int hard) {
			super();
			this.r = r;
			this.c = c;
			this.hard = hard;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C][N+2]; // r, c, 치즈 경도
		hardCheck = new boolean[N+1]; // 먹은 치즈 경도를 확인하기 위한 배열
		cheeses = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c]=='S') cheeses.offer(new Cheese(r, c, 1));
			}
		}
		System.out.println(bfs());
	}
	
	static int bfs() {
		while(!cheeses.isEmpty()) {
			int size = cheeses.size();
			for (int s=0;s<size;s++) {
				Cheese now = cheeses.poll();
				if (now.hard==N+1) return time; // 모든 치즈를 다 먹었다면 걸린 시간 리턴
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr , nc) && !visit[nr][nc][now.hard] && map[nr][nc]!='X') { // 이동할 수 있는 곳이라면
						if (Character.isDigit(map[nr][nc]) && map[nr][nc]-'0'<=now.hard && !hardCheck[map[nr][nc]-'0']) { // 이동하는 곳이 치즈가 있는 곳이라면
							hardCheck[map[nr][nc]-'0'] = true; // 먹은 치즈 경도 체크
							cheeses.offer(new Cheese(nr, nc, now.hard+1)); // 쥐 체력 +1
						} else cheeses.offer(new Cheese(nr, nc, now.hard)); // 빈 공간이나 쥐가 치즈를 먹을 수 없다면 다음 탐색을 위해 큐에 삽입
						visit[nr][nc][now.hard] = true; // 방문체크
					}
				}
			}
			time++;
		}
		return 0;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
