package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 시뮬레이션 문제
 * 
 * 벽의 방향이 상, 우로 2개만 나와있기 때문에 하, 좌로도 표현할 수 있도록 해야한다.
 * 바람이 나오는 과정에서 벽을 체크하는 게 제일 복잡했다.
 * 나머지는 구현하라고 한대로만 구현하면 끝
 */
public class B23289_온풍기안녕 {
	static int R, C, K, W, ans, nr, nc, d, temp[][], blowing[][], controlArr[][], arr[][], dir[][] = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static boolean visit[][];
	static List<int[]> heaterList = new ArrayList<>(), checkList = new ArrayList<>();
	static Queue<int[]> queue = new LinkedList<>();
	static Set<Wall> wallSet = new HashSet<>();
	static class Wall {
		int r, c, t;

		public Wall(int r, int c, int t) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Wall [r=" + r + ", c=" + c + ", t=" + t + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			result = prime * result + t;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Wall other = (Wall) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			if (t != other.t)
				return false;
			return true;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		temp = new int[R][C];
		blowing = new int[R][C];
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				if (arr[r][c] != 0 && arr[r][c] != 5) heaterList.add(new int[] {r, c});
				else if (arr[r][c] == 5) checkList.add(new int[] {r, c});
			}
		}
		
		W = Integer.parseInt(br.readLine());
		for (int i=0;i<W;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken());
			
			if (t==0 && isIn(r-1, c)) wallSet.add(new Wall(r-1, c, 4)); // 오른쪽에 벽이 있다면 그 오른칸으로 가서 왼쪽으로도 벽 표시
			if (t==1 && isIn(r, c+1)) wallSet.add(new Wall(r, c+1, 2)); // 위에 벽이 있다면 그 윗칸으로 가서 아랫쪽으로도 벽 표시
			
			wallSet.add(new Wall(r, c, t==0?3:t)); // 벽 set에 추가
		}
		// 입력 완료
		init(); // 초기 더해지는 바람 값은 항상 똑같으므로 한 번만 구함
		while(true) {
			windBlowing(); // 바람 불기
			tempControl(); // 온도 제어
			tempDecrease(); // 온도 감소
			if (++ans > 100) break; // 100번이 넘어가면 중지
			if (tempCheck()) break; // 온도 체크
		}
		
		System.out.println(ans>100?101:ans);
	}
	
	static void init() {
		for (int i=0;i<heaterList.size();i++) { // 온풍기 리스트에서 하나씩 뽑아서 바람 불기
			visit = new boolean[R][C];
			int temperature = 5; // 초기 더해주는 온도는 5
			int[] nowHeater = heaterList.get(i);
			int heaterNum = arr[nowHeater[0]][nowHeater[1]];
			
			nr = nowHeater[0] + dir[heaterNum][0];
			nc = nowHeater[1] + dir[heaterNum][1];
			blowing[nr][nc] += temperature--;
			queue.offer(new int[] {nr, nc}); 
			
			stop:while(!queue.isEmpty()) {
				int size = queue.size();
				while(size-->0) {
					int[] now = queue.poll();
					// (x-1, y+1), (x+1, y+1) 검사
					for (int j=0;j<2;j++) {
						if (j==0) d = heaterNum<3?3:2;
						else d = heaterNum<3?4:1;
						nr = now[0] + dir[d][0];
						nc = now[1] + dir[d][1];
						if (isIn(nr, nc) && isAble(now[0], now[1], d) && isAble(nr, nc, heaterNum)) {
							nr += dir[heaterNum][0];
							nc += dir[heaterNum][1];
							
							if (isIn(nr, nc) && !visit[nr][nc]) { // 중복체크
								visit[nr][nc] = true;
								blowing[nr][nc] += temperature;
								queue.offer(new int[] {nr, nc});
							}
						}
					}
					
					// (x, y+1) 검사
					nr = now[0] + dir[heaterNum][0];
					nc = now[1] + dir[heaterNum][1];
					if (isIn(nr, nc) && isAble(now[0], now[1], heaterNum) && !visit[nr][nc]) {
						visit[nr][nc] = true;
						blowing[nr][nc] += temperature;
						queue.offer(new int[] {nr, nc});
					}
				}
				if (--temperature == 0) break stop; // 1까지 바람 불면 종료
			}
			queue.clear();
		}
	}
	
	static void windBlowing() {
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				temp[r][c] += blowing[r][c]; // 이미 구해놓은 바람 값 더해주기
			}
		}
	}
	
	static void tempControl() {
		int[] dArr = {1, 4};
		controlArr = new int[R][C]; // 모든 인접한 칸에 대한 온도 제어는 동시에 일어나므로 저장해 줄 배열
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				for (int d=0;d<2;d++) { // 오른쪽과 아랫쪽만 탐색 (어차피 모든 값 탐색해야 하기 때문에 상, 하, 좌, 우 모두 탐색할 필요 없음)
					int nr = r + dir[dArr[d]][0];
					int nc = c + dir[dArr[d]][1];
					if (!isIn(nr, nc) || !isAble(r, c, dArr[d])) continue;
					
					int diff = (Math.abs(temp[r][c] - temp[nr][nc]))/4;
					if (diff == 0) continue;
					// 더 큰 값은 빼주고, 더 작은 값은 더해줌
					if (temp[r][c] > temp[nr][nc]) { 
						controlArr[r][c] -= diff;
						controlArr[nr][nc] += diff;
					} else {
						controlArr[r][c] += diff;
						controlArr[nr][nc] -= diff;
					}
				}
			}
		}
		
		// 모든 작업이 끝난 후 값 변경
		for (int r=0;r<R;r++) {
			for (int c=0;c<C;c++) {
				temp[r][c] += controlArr[r][c];
			}
		}
	}
	
	static void tempDecrease() {
		// 테두리 칸 -1
		for (int r=0;r<R;r++) {
			temp[r][0] = temp[r][0]==0?0:temp[r][0]-1;
			temp[r][C-1] = temp[r][C-1]==0?0:temp[r][C-1]-1;
		}
		
		for (int c=1;c<C-1;c++) {
			temp[0][c] = temp[0][c]==0?0:temp[0][c]-1;
			temp[R-1][c] = temp[R-1][c]==0?0:temp[R-1][c]-1;
		}
	}
	
	static boolean tempCheck() {
		// 온도 체크할 곳이 K보다 하나라도 작을 시 return false;
		for (int i=0;i<checkList.size();i++) {
			int[] now = checkList.get(i);
			if (temp[now[0]][now[1]] < K) return false;
		}
		return true;
	}
	
	static boolean isIn(int r, int c) { // 범위 확인
		return r>=0 && r<R && c>=0 && c<C;
	}
	
	static boolean isAble(int r, int c, int type) { // 벽 확인
		return !wallSet.contains(new Wall(r, c, type));
	}
}
