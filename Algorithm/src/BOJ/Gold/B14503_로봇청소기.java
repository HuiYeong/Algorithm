package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14503_로봇청소기 {
	static int R, C, r, c, dir, cnt, dirCnt;
	static int[][] map, deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서
	static boolean flagEnd;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		for (int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		// 입력완료
		cnt = 1;
		while(true) {
			if(map[r][c]!=1) map[r][c] = 2;
			clean((dir+3)%4);
			if (flagEnd) break;
		}
		
		System.out.println(cnt);
	}
	
	static void clean(int d) {
		int nr = r+deltas[d][0];
		int nc = c+deltas[d][1];
		if (isIn(nr, nc) && map[nr][nc]==0) {
			dir = d;
			r = nr; c = nc;
			cnt++;
			dirCnt = 0;
		} else if (isIn(nr, nc) && map[nr][nc]!=0) {
			dir = d;
			dirCnt++;
		}
		
		if (dirCnt==4) {
			dirCnt = 0;
			nr = r-deltas[dir][0];
			nc = c-deltas[dir][1];
			if (isIn(nr, nc) && (map[nr][nc]==0||map[nr][nc]==2)) {
				r = nr; c = nc;
			} else {
				flagEnd = true;
			}
 		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
