package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B21736_헌내기는친구가필요해 {
	static int R, C, cnt;
	static char[][] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visit;
	static Queue<int[]> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c]=='I') {
					queue.offer(new int[] {r, c});
					visit[r][c] = true;
				}
			}
		}
		bfs();
		if (cnt==0) System.out.println("TT");
		else System.out.println(cnt);
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int d=0;d<4;d++) {
				int nr = now[0]+dir[d][0];
				int nc = now[1]+dir[d][1];
				if (isIn(nr, nc) && !visit[nr][nc] && map[nr][nc]!='X') {
					if (map[nr][nc]=='P') {
						cnt++;
						map[nr][nc] = 'O';
					}
					queue.offer(new int[] {nr, nc});
					visit[nr][nc] = true;
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
