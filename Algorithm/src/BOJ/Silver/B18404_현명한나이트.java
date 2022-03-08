package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B18404_현명한나이트 {
	static int N, M, nightR, nightC, cnt, ansCnt;
	static int[] ans;
	static int[][] map, deltas = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
	static boolean[][] visit;
	static Queue<Move> bfs;
	static class Move {
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
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visit = new boolean[N][N];
		ans = new int[M+1]; // 상대편 말들의 최소 이동 거리를 저장하기 위한 배열
		bfs = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		nightR = Integer.parseInt(st.nextToken()) - 1;
		nightC = Integer.parseInt(st.nextToken()) - 1;
		bfs.offer(new Move(nightR, nightC));
		visit[nightR][nightC] = true;
		for (int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = i; // 맵에 상대편 말 번호 저장
		}
		bfsMove();
		
		for (int i=1;i<=M;i++) {
			System.out.print(ans[i]+" ");
		}
	}
	
	static void bfsMove() {
		while(!bfs.isEmpty()) {
			if (ansCnt==M) return; // 상대편 말을 다 잡으면 끝!
			int size = bfs.size();
			for (int s=0;s<size;s++) {
				Move now = bfs.poll();
				if (map[now.r][now.c]!=0) { // 상대편 말을 잡으면
					ans[map[now.r][now.c]] = cnt; // ans배열에 최소 이동 거리 저장
					map[now.r][now.c] = 0; // 말 지워주기
					ansCnt++;
				}
				for (int d=0;d<8;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if(isIn(nr, nc) && !visit[nr][nc]) {
						bfs.offer(new Move(nr, nc));
						visit[nr][nc] = true;	
					}
				}
			}
			cnt++;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}