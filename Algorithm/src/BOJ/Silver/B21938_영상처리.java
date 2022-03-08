package BOJ.Silver;

import java.io.*;
import java.util.*;

public class B21938_영상처리 {
	static int N, M, T, cnt, temp;
	static int[][] map, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visit;
	static Queue<int[]> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		queue = new LinkedList<>();
		
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<M;c++) {
				for (int k=0;k<3;k++) {
					map[r][c] += Integer.parseInt(st.nextToken());
				}
			}
		}
		T = Integer.parseInt(br.readLine());
		// 입력 완료
		
		for (int r=0;r<N;r++) {
			for (int c=0;c<M;c++) {
				int avg = (int)(map[r][c]/3);
				if (avg >= T) temp = 255;
				else temp = 0;
				map[r][c] = temp; 
			}
		}
		
		for (int r=0;r<N;r++) {
			for (int c=0;c<M;c++) {
				if (map[r][c] == 255 && !visit[r][c]) {
					cnt++;
					visit[r][c] = true;
					queue.offer(new int[] {r, c});
					bfs();
				}
			}
		}
		System.out.println(cnt);
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int d=0;d<4;d++) {
				int nr = now[0] + dir[d][0];
				int nc = now[1] + dir[d][1];
				if (!isIn(nr, nc) || map[nr][nc] == 0 || visit[nr][nc]) continue;
				visit[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<M;
	}
}
