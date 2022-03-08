package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B15730_수영장사장님 {
	static int R, C, cnt, MAX, map[][], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<int[]> queue;
	static boolean[][] nowMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		queue = new LinkedList<>();
		MAX = Integer.MIN_VALUE;
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (MAX<map[r][c]) MAX = map[r][c];
			}
		}
		bruteforce();
		System.out.println(cnt);
	}
	
	static void bruteforce() {
		for (int h=1;h<=MAX;h++) {
			nowMap = new boolean[R+2][C+2];
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (map[r][c]>=h) nowMap[r+1][c+1] = true;
				}
			}
			queue.offer(new int[] {0, 0});
			nowMap[0][0] = true;
			bfs();
		}
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int d=0;d<4;d++) {
				int nr = now[0]+dir[d][0];
				int nc = now[1]+dir[d][1];
				if (!isIn(nr, nc) || nowMap[nr][nc]) continue;
				nowMap[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}
		}
		
		for (int r=0;r<=R+1;r++) {
			for (int c=0;c<=C+1;c++) {
				if (!nowMap[r][c]) cnt++;
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R+2 && nc>=0 && nc<C+2;
	}
}
