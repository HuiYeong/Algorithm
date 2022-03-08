package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5427_불 {
	static int R, C, cnt;
	static char[][] map;
	static Queue<move> fire, person;
	static boolean flag;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
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
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			fire = new LinkedList<>();
			person = new LinkedList<>();
			for (int r=0;r<R;r++) {
				String input = br.readLine();
				for (int c=0;c<C;c++) {
					map[r][c] = input.charAt(c);
					if (map[r][c]=='@') person.add(new move(r, c));
					else if (map[r][c]=='*') fire.add(new move(r, c));
				}
			}
			
			cnt = 0;
			flag = false;
			bfsL();
			if (flag) sb.append(cnt+"\n");
			else sb.append("IMPOSSIBLE\n");
		}
		System.out.println(sb);
	}
	
	static void bfsL() {
		stop:while(!person.isEmpty()) {
			cnt++;
			int sizeF = fire.size();
			for (int i=0;i<sizeF;i++) { 
				move nowF = fire.poll();
				loop(nowF, 'F');
			}
			
			int sizeP = person.size();
			for (int i=0;i<sizeP;i++) {
				move nowP = person.poll();
				loop(nowP, 'P');
				if (flag) break stop;
			}
		}
	}
	
	static void loop(move now, char fp) {
		for (int d=0;d<4;d++) {
			int nr = now.r+deltas[d][0];
			int nc = now.c+deltas[d][1];
			if (isIn(nr, nc) && map[nr][nc]=='.') {
				if (fp == 'F') {
					map[nr][nc]='*';
					fire.add(new move(nr, nc));
				} else if (fp == 'P') {
					map[nr][nc]='@';
					person.add(new move(nr, nc));
				}	
			} else if (fp == 'P' && !isIn(nr, nc)) flag = true; // 범위 밖을 벗어나면 탈출!
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<R && nc>=0 && nc<C;
	}
}
