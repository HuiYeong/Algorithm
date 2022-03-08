package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1726_로봇 {
	static int R, C, MIN;
	static Robot goal;
	static int[][] map, deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 우, 하, 좌, 상
	static boolean[][][] visit;
	static Queue<Robot> queue;
	static class Robot {
		int r, c, dir, cnt;

		public Robot(int r, int c, int dir, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Robot [r=" + r + ", c=" + c + ", dir=" + dir + ", cnt=" + cnt + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visit = new boolean[R][C][4];
		queue = new LinkedList<>();
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i=0;i<2;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			if (d==1) d = 2;
			else if (d==2) d = 1;
			goal = new Robot(r, c, d, 0);
			if (i==0) {
				queue.offer(goal);
				visit[goal.r][goal.c][goal.dir] = true; 
			}
		}
		MIN = Integer.MAX_VALUE;
		if (queue.peek().r==goal.r && queue.peek().c==goal.c) {
			if (queue.peek().dir==goal.dir) System.out.println(0);
			else {
				int addDir = Math.abs(queue.peek().dir-goal.dir)==3?1:Math.abs(queue.peek().dir-goal.dir);
				System.out.println(addDir);
			}
		}
		else System.out.println(bfs());
	}
	
	static int bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s=0;s<size;s++) {
				Robot now = queue.poll();
				visit[now.r][now.c][now.dir] = true;
				if (MIN>now.cnt) {
					for (int d=0;d<4;d++) {
						for (int k=1;k<=3;k++) { // 3칸.. 이동 가능...
							int nr = now.r+deltas[d][0]*k;
							int nc = now.c+deltas[d][1]*k;
							if (isIn(nr, nc) && map[nr][nc]!=1) {
								if (nr==goal.r && nc==goal.c) { // 다음 칸이... 목적지라면....
									int addDir = Math.abs(d-goal.dir)==3?1:Math.abs(d-goal.dir); // 이동 방향과... 목적지 방향과의 ... 차이 구하기...
									int addDir2 = Math.abs(now.dir-d)==3?1:Math.abs(now.dir-d); // 현재 방향과... 이동 방향과의 ... 차이 구하기...
									MIN = Math.min(MIN, now.cnt+addDir+addDir2+1); // 최솟값.. 구하기.....
								} else if (!visit[nr][nc][d]) { 
									if (d!=now.dir) { // 이동할 방향과... 현재 방향이.. 같지 않을 때..
										int addDir = Math.abs(now.dir-d)==3?1:Math.abs(now.dir-d); // 현재 방향과... 이동 방향과의 ... 차이 구하기...
										queue.offer(new Robot(nr, nc, d, now.cnt+addDir+1)); // 현재 이동 횟수 + 현재 방향, 이동 방향 차이 + 이동 횟수
									}
									else queue.offer(new Robot(nr, nc, d, now.cnt+1)); // 현재 방향과 이동방향이 같을 땐 이동 횟수만 +1
								} else break;
							} else break;
						}
					}
				}
			}
		}
		return MIN;
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
