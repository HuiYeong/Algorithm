package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백트래킹 문제로 대각선으로만 이동한다는 비숍 특성을 생각하여 (0,0) 칸에서의 대각선, (0,1) 칸에서의 대각선으로 나누어 생각한다.
 * 
 * 상단을 먼저 탐색한 후 하단을 탐색한다.
 * 그러므로 비숍이 놓일 수 있는지 확인할 때 하단을 볼 필요가 없기 때문에 상단만 체크한다.
 * 
 * 해당 위치에서 비숍이 놓인다면 방문 처리 후 다음 칸을 탐색한다.
 */

public class B1799_비숍 {
	static int N, blackMax, whiteMax, arr[][], dir[][] = {{-1, -1}, {-1, 1}};
	static boolean visit[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		StringTokenizer st = null;
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[N][N];
		search(0, 0, 0, true);
		visit = new boolean[N][N];
		search(0, 1, 0, false);
		System.out.println(blackMax+whiteMax);
	}
	
	static void search(int r, int c, int cnt, boolean type) {
		if (type) blackMax = Math.max(blackMax, cnt);
		else whiteMax = Math.max(whiteMax, cnt);
		
		if (c >= N) { // 범위 벗어나면 줄 바꿔주기
			r += 1; 
			if (type) c = (r%2==0)?0:1;
			else c = (r%2==0)?1:0;
		}
		
		if (r >= N) return; // 모든 줄 탐색이 끝나면 리턴
		
		if (isAble(r, c)) { 
			visit[r][c] = true; // 방문 처리
			search(r, c+2, cnt+1, type); // 다음 칸 탐색
			visit[r][c] = false;
		}
		
		search(r, c+2, cnt, type); // 비숍을 못 놓는 경우도 탐색
	}
	
	static boolean isAble(int initR, int initC) {
		if (arr[initR][initC] == 0) return false; // 비숍을 놓을 수 없는 곳이라면 return false;
		
		for (int i=0;i<2;i++) { 
			int r = initR, c = initC;
			while(true) {
				int nr = r + dir[i][0];
				int nc = c + dir[i][1];
				if (!isIn(nr, nc)) break; // 범위를 벗어난다면 다음 방향으로
				if (visit[nr][nc]) return false; // 이미 비숍이 놓여졌다면 return false;
				r = nr; c = nc;
			}
		}
		return true; // 비숍을 넣을 수 있는 곳이라면 return true;
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
