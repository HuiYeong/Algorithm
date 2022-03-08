package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2636_치즈 {
	static int R, C, ans, tmp, cnt;
	static int[][] cheese, check, deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<int[]> bfs;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C= Integer.parseInt(st.nextToken());
		cheese = new int[R][C];
		for (int r=0;r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<C;c++) {
				cheese[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		bfs = new LinkedList<>();
		// 입력완료
		while(true) {
			flag = false;
			// 공기 탐색
			check = new int[R][C];
			bfs.offer(new int[] {0, 0});
			check[0][0] = 1;
			bfsL();
			
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					if (check[r][c]==0 && cheese[r][c]==0) cheese[r][c]=2; // 치즈 속 공기는 2
				}
			}
			
			// 사라질 치즈 탐색
			tmp = 0; // 치즈 개수 카운트
			check = new int[R][C];
			for (int r=1;r<R-1;r++) {
				for (int c=1;c<C-1;c++) {
					if (cheese[r][c]==1) {
						flag = true;
						tmp++;
						stop:for (int d=0;d<4;d++) {
							int nr = r+deltas[d][0];
							int nc = c+deltas[d][1];
							if (cheese[nr][nc]==0) {
								check[r][c]=1;
								break stop;
							}
						}
					}
				}
			}
			
			if (!flag) break;
			
			// 치즈 제거
			for (int r=1;r<R-1;r++) {
				for (int c=1;c<C-1;c++) {
					if (check[r][c]==1 || cheese[r][c]==2) cheese[r][c] = 0;
				}
			}
			
			cnt = tmp;
			ans++;
		}
		
		System.out.println(ans+"\n"+cnt);
	}
	
	static void bfsL() {
		while(!bfs.isEmpty()) {
			int size = bfs.size();
			for (int i=0;i<size;i++) {
				int[] now = bfs.poll();
				for (int d=0;d<4;d++) {
					int nr = now[0]+deltas[d][0];
					int nc = now[1]+deltas[d][1];
					if (isIn(nr, nc) && cheese[nr][nc]==0 && check[nr][nc]==0) {
						check[nr][nc] = 1;
						bfs.offer(new int[] {nr, nc});
					}
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
