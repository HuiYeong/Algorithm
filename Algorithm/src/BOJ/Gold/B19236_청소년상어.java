package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B19236_청소년상어 {
	static int MAX;	
	static fish[] fishes;
 	static int[][] arr, deltas = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}}; 
 	
	static class fish {
		int r, c, d;
		boolean alive;

		public fish(int r, int c, int d, boolean alive) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.alive = alive;
		}
	}
	
	static class par {
		fish shark;
		int ans, key;
		int[][] arr;
		fish[] fishes;
		public par(fish shark, int ans, int key, int[][] arr, fish[] fishes) {
			super();
			this.shark = shark; // 상어 객
			this.ans = ans; // 물고기 합 
			this.key = key; // 현재 먹은 물고기 번호
			this.arr = arr; // 맵
			this.fishes = fishes; // 물고기 배열
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		arr = new int[4][4];
		fishes = new fish[17];
		for (int r=0;r<4;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<4;c++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				arr[r][c] = num;
				fishes[num] = new fish(r, c, d, true);
			}
		}
		MAX = Integer.MIN_VALUE;
		dfs(new par(fishes[arr[0][0]], 0, arr[0][0], arr, fishes)); // 상어가 처음 먹는 물고기 : (0,0) 좌표에 있는 물고
		System.out.println(MAX);
	}
	
	static void dfs(par in) {
		int[][] temp = new int[4][4];
		for (int i=0;i<4;i++) { // 맵 복사
			for (int j=0;j<4;j++) {
				temp[i][j] = in.arr[i][j];
			}
		}
		
		fish[] f = new fish[17];
		for (int i=1;i<=16;i++) { // 물고기 배열 복사
			f[i] = in.fishes[i];
		}
		
		temp[in.shark.r][in.shark.c] = -1; // 해당 위치 물고기를 상어가 먹음
		f[in.key].alive = false; // 물고기 죽었다..
		
		// 물고기 이동
		fishMove(temp, f);
		
		// 상어 이동
		for (int i=1;i<=3;i++) { 
			int nr = in.shark.r + deltas[in.shark.d][0]*i; // 이동할 칸 수 정하기 (1칸, 2칸, 3칸까지 이동 가능)
			int nc = in.shark.c + deltas[in.shark.d][1]*i;
			
			if (!isIn(nr, nc)) { // 범위를 벗어나면 상어는 집으로 돌아감
				MAX = Math.max(MAX, in.ans+in.key); // 현재까지 먹은 물고기 합의 최댓값 구하기
				break;
			} else if (temp[nr][nc]==0) continue; // 물고기가 없다면 넘어감
			else {
				temp[in.shark.r][in.shark.c] = 0; // 상어가 이동하니 현재 있었던 위치 0으로 바꿔주기
				int next = temp[nr][nc]; // 다음 먹을 물고기 번호
				dfs(new par(new fish(nr, nc, f[next].d, true), in.ans+in.key, next, temp, f)); // 다음 물고기 먹으러 가자
				// 초기화!!!!!!!!!
				temp[nr][nc] = next; 
				temp[in.shark.r][in.shark.c] = -1; // 상어 다시 제자리로
				f[next].alive = true; // 먹혔던 물고기 다시 살려쥬..
			} 
		}
		return;
	}
	
	static void fishMove(int[][] temp, fish[] f) {
		for (int i=1;i<=16;i++) { // 작은 물고기부터 이동
			if (f[i].alive) { // 물고기가 살아있을때만 이동 가능
				fish now = f[i]; //현재 이동할 물고기 가져오기
				int d = f[i].d; // 물고기가 가진 방향 저장
				while (true) {
					int nr = now.r + deltas[d][0];
					int nc = now.c + deltas[d][1];
					if (!isIn(nr, nc) || temp[nr][nc]==-1) d = (d%8)+1; // 범위를 벗어나거나 갈 위치에 상어가 있다면 45도 회전
					else if (isIn(nr, nc)){ // 갈 수 있다면
						swap(now, nr, nc, i, d, temp, f); // 바꿔주기
						break;
					}
				}
			}
		}
	}
	
	static void swap(fish now, int nr, int nc, int i, int d, int[][] temp, fish[] f) {
		int tmp = temp[nr][nc]; // 바꿔줄 물고기 번호
		
		temp[nr][nc] = i; // 바꿔줄 좌표에 현재 물고기 번호 넣어줌
		temp[now.r][now.c] = tmp; // 현재 좌표에 바꿔줄 물고기 번호 넣어줌
		if (tmp!=0) f[tmp] = new fish(now.r, now.c, f[tmp].d, true); // 바꿔줄 좌표에 물고기가 없다면 
		f[i] = new fish(nr, nc, d, true); // 물고기 저장
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<4 && nc>=0 && nc<4;
	}
}