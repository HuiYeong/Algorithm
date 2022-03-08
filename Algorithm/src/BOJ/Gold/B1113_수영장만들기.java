package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1113_수영장만들기 {
	static int R, C, cnt, MAX, totalCnt;
	static boolean flag;
	static Queue<int[]> queue;
	static int[][] map, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][][] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visit = new boolean[R][C][10];
		MAX = Integer.MIN_VALUE;
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c)-'0';
				if (map[r][c]>MAX) MAX = map[r][c]; // 최대 높이 구하기
			}
		}
		System.out.println(bruteforce());
	}
	
	static int bruteforce() {
		for (int h=1;h<=MAX;h++) {
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (!visit[r][c][h] && map[r][c]<h) { // 방문하지 않고 물이 고일 수 있다면 bfs 돌리기
						cnt = 1;
						queue.offer(new int[] {r, c});
						visit[r][c][h] = true;
						totalCnt += bfs(h);
					}
				}
			}
		}
		return totalCnt;
	}
	
	static int bfs(int h) {
		flag = true;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int s=0;s<size;s++) {
				int[] now = queue.poll();
				for (int d=0;d<4;d++) {
					int nr = now[0]+dir[d][0];
					int nc = now[1]+dir[d][1];
					if (isIn(nr, nc) && !visit[nr][nc][h] && map[nr][nc]<h) {
						queue.offer(new int[] {nr, nc});
						visit[nr][nc][h] = true;
						cnt++;
					} else if (!isIn(nr, nc)) flag = false; // 범위를 벗어난다면 물이 고일 수 없음
				}
			}
		}
		if (flag) return cnt; // 물이 고일 때만 cnt 리턴
		else return 0;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}

