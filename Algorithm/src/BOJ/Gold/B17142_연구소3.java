package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17142_연구소3 {
	static int N, M, MIN, cntT, emptyCnt, transCnt;
	static int[][] map, deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visit;
	static int[] select;
	static List<Virus> virus;
	static Queue<Virus> queue;
	static class Virus {
		int r, c;
		public Virus(int r, int c) {
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
		select = new int[M];
		virus = new ArrayList<>();
		MIN = Integer.MAX_VALUE;
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c]==2) virus.add(new Virus(r, c));
				else if (map[r][c]==0) emptyCnt++;
			}
		}
		if (emptyCnt==0) System.out.println(0);
		else {
			combination(0, 0);
			if(MIN == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(MIN);
		}
		
	}
	
	static void combination(int cnt, int start) {
		if(cnt==M) {
			queue = new LinkedList<>();
			visit = new boolean[N][N];
			cntT = 0; transCnt = 0;
			for (int i=0;i<M;i++) {
				int r = virus.get(select[i]).r;
				int c = virus.get(select[i]).c;
				queue.offer(new Virus(r, c)); // 활성 바이러스
				visit[r][c] = true;
			}
			
			if(bfs()) MIN = Math.min(MIN, cntT+1);
			return;
		}
		
		for (int i=start;i<virus.size();i++) {
			select[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
	
	static boolean bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int s=0;s<size;s++) {
				Virus now = queue.poll();
				for (int d=0;d<4;d++) {
					int nr = now.r+deltas[d][0];
					int nc = now.c+deltas[d][1];
					if (isIn(nr, nc) && !visit[nr][nc] && map[nr][nc]!=1) {
						if(map[nr][nc]==0) transCnt++;
						queue.offer(new Virus(nr, nc));
						visit[nr][nc] = true;
					}
				}
			}
			if (transCnt==emptyCnt) return true;
			if (cntT++>=MIN) return false;;
		}
		return false;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
