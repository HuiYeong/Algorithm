package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B17472_다리만들기2 {
	static int R, C, len;
	static int[] roots;
	static int[][] map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static PriorityQueue<Bridge> queue;
	static class Bridge implements Comparable<Bridge>{
		int from, to, weight;

		public Bridge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		queue = new PriorityQueue<>();
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		//입력완료
		
		// 섬 번호 새겨주기
		int island = 2;
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]==1) {
					map[r][c] = island;
					dfs(r, c, island);
					island++;
				}
				
			}
		}
		
		// 섬 사이의 다리 길이 구하기
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				if (map[r][c]!=0) {
					for (int d=0;d<4;d++) {
						int nr = r+deltas[d][0];
						int nc = c+deltas[d][1];
						if (isIn(nr, nc) && map[nr][nc]==0) {
							len = 1;
							lengthCheck(nr, nc, d, map[r][c]);
						}
					}
				}
			}
		}
		
		// 다리 결합!!!
		roots = new int[island-2];
		makeSet();
		int cnt = 0;
		int ans = 0;
		boolean flag = true;
		while(!queue.isEmpty()) {
			Bridge now = queue.poll();
			if (union(now.from, now.to)) {
				ans+=now.weight;
				if (++cnt==roots.length-1) {
					flag = false;
					break;
				}
			}
		}
		
		if (flag) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static void makeSet() {
		for (int i=0;i<roots.length;i++) {
			roots[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (roots[x]<0) return x;
		return roots[x] = findSet(roots[x]);
	}
	
	static boolean union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x==y) return false;
		roots[y] = x;
		return true;
	}
	
	static void lengthCheck(int r, int c, int d, int island) {
		int dest = 0;
		boolean flag = false;
		while(true) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc) && map[nr][nc]!=0) {
				dest = map[nr][nc];
				break;
			} else if (!isIn(nr, nc)){
				flag = true;
				break;
			}
			r = nr; c = nc;
			len++;
		}
		
		if (!flag && len!=1) queue.offer(new Bridge(island-2, dest-2, len));
	}
	
	static void dfs(int r, int c, int island) {
		for (int d=0;d<4;d++) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc) && map[nr][nc]==1) {
				map[nr][nc] = island;
				dfs(nr, nc, island);
			}
		}
		return;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
