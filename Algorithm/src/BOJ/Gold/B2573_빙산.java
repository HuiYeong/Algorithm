package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2573_빙산 {
	static int R, C;
	static int[][] map, check, deltas= {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력완료
		
		int ans = 0;
		stop:while(true) {
			check = new int[R][C];
			ans++;
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (map[r][c]>0) melt(r, c, 0);
				}
			}
			
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (check[r][c]>0) {
						map[r][c]-=check[r][c];
						if (map[r][c]<0) map[r][c]=0;
					}
				}
			}
			
			visit = new boolean[R][C];
			boolean flag = false;
			int cnt = 0;
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (map[r][c]>0 && !visit[r][c]) {
						flag = true; cnt++;
						visit[r][c] = true;
						melt(r, c, 1);
					}
				}
			}
			if (!flag || cnt>1) {
				if (!flag) ans = 0;
				break stop;
			}
		}
		System.out.println(ans);
	}
	
	static void melt(int r, int c, int menu) {
		for (int d=0;d<4;d++) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc)) {
				if (menu==0 && map[nr][nc]==0) check[r][c]++;
				else if (menu == 1 && map[nr][nc]>0 && !visit[nr][nc])  {
					visit[nr][nc] = true;
					melt(nr, nc, 1);
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
