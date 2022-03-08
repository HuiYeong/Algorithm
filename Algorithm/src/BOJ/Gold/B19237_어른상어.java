package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B19237_어른상어 {
	static int N, M, K, ans;
	static boolean flag;
	static int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static MapShark[][] mapShark;
	static List<Shark> sharkList;
	static class Shark {
		int num, r, c, nr, nc, dir;
		boolean alive;
		int[][] priority;

		public Shark(int num, int r, int c, int nr, int nc, int dir, boolean alive, int[][] priority) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
			this.nr = nr;
			this.nc = nc;
			this.dir = dir;
			this.alive = alive;
			this.priority = priority;
		}

		@Override
		public String toString() {
			return "Shark [num=" + num + ", r=" + r + ", c=" + c + ", nr=" + nr + ", nc=" + nc + ", dir=" + dir
					+ ", alive=" + alive + ", priority=" + Arrays.toString(priority) + "]";
		}
		
	}
	
	static class MapShark {
		int num, time;

		public MapShark(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}

		@Override
		public String toString() {
			return "MapShark [num=" + num + ", time=" + time + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		mapShark = new MapShark[N][N];
		sharkList = new ArrayList<>();
		for (int i=0;i<=M;i++) {
			sharkList.add(new Shark(i, 0, 0, 0, 0, 0, true, null));
		}
		 
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				int now = Integer.parseInt(st.nextToken());
				if (now != 0) {
					mapShark[r][c] = new MapShark(now, K);
					Shark nowShark = sharkList.get(now);
					nowShark.r = r; nowShark.c = c;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=1;i<=M;i++) {
			Shark now = sharkList.get(i);
			now.dir = Integer.parseInt(st.nextToken());
		}
		
		for (int m=1;m<=M;m++) {
			Shark now = sharkList.get(m);
			int[][] nowDir = new int[5][4];
			for (int i=1;i<=4;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<4;j++) {
					nowDir[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			now.priority = nowDir;
		}
		// 입력 끝
		while(true) {
			flag = true;
			sharkMove(); // 상어 이동할 곳 체크
			timeCheck(); // 시간 체크
			moveStart(); // 상어 이동
			if (ans > 1000 || flag) break;
			ans++;
			
		}
		if (ans > 1000) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static void sharkMove() {
		for (int i=1;i<sharkList.size();i++) {
			Shark now = sharkList.get(i); // 이동할 상어
			if (!now.alive) continue; // 이미 죽었다면..... 보지도 말어라..
			
			int[] priority = now.priority[now.dir]; // 상어가 가진 방향 우선순위
			int possibleDir = -1; // 빈 칸이 없을 경우, 자신의 냄새가 있는 칸 정하기
			
			for (int j=0;j<4;j++) { // 내 우선순위 따라서~
				int nr = now.r + dir[priority[j]][0]; // 이동할 r 방향
				int nc = now.c + dir[priority[j]][1]; // 이동할 c 방향
				if (!isIn(nr, nc)) continue;
				
				if (mapShark[nr][nc] == null) { // 내가 가려는 방향에 상어가 없다면~
					now.nr = nr; now.nc = nc; now.dir = priority[j];
					possibleDir = 0;
					break;
				} else if (mapShark[nr][nc] != null && mapShark[nr][nc].num == now.num && possibleDir == -1){
					possibleDir = priority[j];
				}
			}
			
			if (possibleDir > 0) { // 빈 칸은 없음.. 
				now.nr = now.r + dir[possibleDir][0]; // 이동할 r 방향
				now.nc = now.c + dir[possibleDir][1]; // 이동할 c 방향
				now.dir = possibleDir;
			}
		}
	}
	
	static void moveStart() {
		for (int i=1;i<sharkList.size();i++) {
			Shark now = sharkList.get(i);
			
			if (!now.alive) continue; // 이미 죽었다면.. 보지도 말어라.. 
			else if (now.alive && now.num > 1) flag = false; 
			
			if (mapShark[now.nr][now.nc] != null) { // 내가 갈 방향에 ..이미 상어가 있다면..?!?!?
				Shark other = sharkList.get(mapShark[now.nr][now.nc].num);
				if (other.num < now.num) { // 거기 있는 상어가 나보다 크기가 작거나 이미 죽은 상어라면?
					now.alive = false; // 나 죽어...
				} else if (other.num >= now.num || !other.alive){ // 거기 있는 상어가 나보다 크기가 크다!!!
					if (now.num!=other.num) other.alive = false; // 걔는 죽어유.. ㅎ
					mapShark[now.nr][now.nc] = new MapShark(now.num, K); // 나 들어가유~
					now.r = now.nr; now.c = now.nc;
				}
			} else { // 내가 갈 방향에 아무도 없다면?!
				mapShark[now.nr][now.nc] = new MapShark(now.num, K); // 나 들어가유~
				now.r = now.nr; now.c = now.nc;
			}
		}
	}
	
	static void timeCheck() {
		for (int r=0;r<N;r++) {
			for (int c=0;c<N;c++) {
				if (mapShark[r][c] != null) { // 상어의 향기가 남아있을 경우
					if (mapShark[r][c].time-1 == 0) mapShark[r][c] = null;
					else mapShark[r][c] = new MapShark(mapShark[r][c].num, mapShark[r][c].time-1);
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
