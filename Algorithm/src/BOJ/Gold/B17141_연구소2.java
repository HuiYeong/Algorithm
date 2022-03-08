package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17141_연구소2 {
	static int N, M, MIN, ans;
	static int[] numbers;
	static boolean[][] visited;
	static int[][] map, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<Move> virus;
	static List<Move> available;
	static class Move {
		int r, c;

		public Move(int r, int c) {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new LinkedList<>();
		available = new LinkedList<>();
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c]==2) available.add(new Move(r, c));
			}
		}
		numbers = new int[M];
		MIN = Integer.MAX_VALUE;
		combination(0, 0);
		System.out.println(MIN);
	}
	
	static void combination(int cnt, int start) {
		if (cnt==M) {
			ans = 0;
			visited = new boolean[N][N];
			for (int i=0;i<M;i++) {
				int r = available.get(numbers[i]).r;
				int c = available.get(numbers[i]).c;
				virus.offer(new Move(r, c));
				visited[r][c] = true;
			}
			bfs();
			for (int r=0;r<N;r++) {
				for (int c=0;c<N;c++) {
					if (map[r][c]!=1 && !visited[r][c]) ans = 0; // 퍼뜨릴 수 없슴..
				}
			}
			if (ans==0 && MIN==Integer.MAX_VALUE) MIN = -1;
			else if (MIN==-1 && ans!=0) MIN = ans-1;
			else if (MIN!=-1 && ans!=0) MIN = Math.min(MIN, ans-1);

			return;
		}
		
		for (int i=start;i<available.size();i++) {
			numbers[cnt] = i;
			combination(cnt+1,i+1);
		}
	}
	
	static void bfs() {
		while(!virus.isEmpty()) {
			int size = virus.size();
			for (int i=0;i<size;i++) {
				Move now = virus.poll();
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc) && map[nr][nc]!=1 && !visited[nr][nc]) {
						visited[nr][nc] = true;
						virus.offer(new Move(nr, nc));
					}
				}
			}
			ans++;
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
