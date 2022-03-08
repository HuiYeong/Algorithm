package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class B16946_벽부수고이동하기4 {
	static int R, C, cnt, ans, levelCnt;
	static boolean[][] visited;
	static TreeSet<Integer> tree;
	static int[][] map, level, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<int[]> queue;
	static Queue<int[]> list;
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		level = new int[R][C];
		map = new int[R][C];
		visited = new boolean[R][C];
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c)-'0';
				if (map[r][c]==1) map[r][c] = -1;
			}
		}
		// 입력완료
		levelCnt = 1;
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]==0 && !visited[r][c]) {
					cnt = 1;
					visited[r][c] = true;
					queue.offer(new int[] {r, c});
					dfs(r, c);
					check();
				} 
			}
		}
		
		tree = new TreeSet<>();
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]==-1) {
					sb.append(ansCheck(r, c));
				} else sb.append(0);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void check() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			map[now[0]][now[1]] = cnt%10;
			level[now[0]][now[1]] = levelCnt;
		}
		levelCnt++;
	}
	
	static void dfs(int r, int c) {
		for (int d=0;d<4;d++) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc) && map[nr][nc]==0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
				cnt++;
				dfs(nr, nc);
			}
		}
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int d=0;d<4;d++) {
				int nr = now[0]+deltas[d][0];
				int nc = now[1]+deltas[d][1];
				if (isIn(nr, nc) && map[nr][nc]==0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					cnt++;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
	}
	
	static int ansCheck(int r, int c) {
		ans = 1;
		tree.clear();
		for (int d=0;d<4;d++) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc) && map[nr][nc]!=-1 && !tree.contains(level[nr][nc])) {
				tree.add(level[nr][nc]);
				ans+=map[nr][nc];
			}
		}
		return ans%10;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}