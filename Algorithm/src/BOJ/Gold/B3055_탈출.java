package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B3055_탈출 {
	static char[][] map;
	static int R, C, cnt;
	static boolean flag;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<move> water, dochi;
	static class move {
		int r, c;

		public move(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		water = new LinkedList<>();
		dochi = new LinkedList<>();
		for (int r=0;r<R;r++) {
			String input = br.readLine();
			for (int c=0;c<C;c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c]=='S') dochi.add(new move(r, c)); // 고슴도치
				else if (map[r][c]=='*') water.add(new move(r, c)); // 물
			}
		}
		// 입력완료
		cnt = 0;
		bfsL();
		if (flag) System.out.println(cnt);
		else System.out.println("KAKTUS");
	}
	
	static void bfsL() {
		stop:while(!dochi.isEmpty()) {
			cnt++;
			int sizeW = water.size(); // 현재 시간에 있는 물들을 이동해줘야하니 물의 사이즈만큼 반복문을 돌려 현재 시간의 물을 다 퍼트려준다			
			for (int i=0;i<sizeW;i++) {
				move waterN = water.poll();
				loop(waterN, 'W');
			}
			int sizeD = dochi.size(); // 도치도 마찬가지
			for (int i=0;i<sizeD;i++) { 
				move dochiN = dochi.poll();
				loop(dochiN, 'D');
				if (flag) break stop;
			}
		}
	}
	
	static void loop(move now, char wd) {
		for (int d=0;d<4;d++) {
			int nr = now.r+deltas[d][0];
			int nc = now.c+deltas[d][1];
			if (isIn(nr, nc)) {
				if (wd == 'W' && (map[nr][nc]=='.' || map[nr][nc]=='S')) {
					map[nr][nc] = '*';
					water.add(new move(nr, nc));
				} else if (wd == 'D' && (map[nr][nc]=='.' || map[nr][nc]=='D')) {
					if (map[nr][nc]=='D') flag = true;
					map[nr][nc] = 'S';
					dochi.add(new move(nr, nc));
				}
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
