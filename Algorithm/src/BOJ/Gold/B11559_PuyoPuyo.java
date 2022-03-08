package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B11559_PuyoPuyo {
	static char[][] map;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visit;
	static boolean flag;
	static Queue<Move> bfs, bomb;
	static class Move {
		int r, c;

		public Move(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Move [r=" + r + ", c=" + c + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		bfs = new LinkedList<>();
		bomb = new LinkedList<>();
		for (int r=0;r<12;r++) {
			String input = br.readLine();
			map[r] = input.toCharArray();
		}
		int cnt = 0;
		while(true) {
			visit = new boolean[12][6];
//			print();
			flag = false;
			for (int r=0;r<12;r++) {
				for (int c=0;c<6;c++) {
					if (map[r][c]!='.' && !visit[r][c]) {
						bfs.offer(new Move(r, c));
						bomb.offer(new Move(r, c));
						visit[r][c] = true;
						bombCheck();
					}
				}
			}
			if (!flag) break;
			mapRemake();
			cnt++;
		}
		System.out.println(cnt);
	}
	
	static void print() {
		for (int r=0;r<12;r++) {
			for (int c=0;c<6;c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void bombCheck() {
		while(!bfs.isEmpty()) {
			Move now = bfs.poll();
			for (int d=0;d<4;d++) {
				int nr = now.r+deltas[d][0];
				int nc = now.c+deltas[d][1];
				if (isIn(nr, nc) && !visit[nr][nc] && map[nr][nc]==map[now.r][now.c]) {
					bfs.offer(new Move(nr, nc));
					bomb.offer(new Move(nr, nc));
					visit[nr][nc] = true;
				}
			}
		}
		
		if (bomb.size()>=4) {
			flag = true;
			bombNow();
		}
		else bomb = new LinkedList<>();
	}
	
	static void bombNow() {
		while(!bomb.isEmpty()) {
			Move now = bomb.poll();
			map[now.r][now.c] = '.'; 
		}
	}
	
	static void mapRemake() {
		for (int c=0;c<6;c++) {
			String input = "";
			int len = 0;
			for (int r=0;r<12;r++) {
				if (map[r][c]!='.') input+=map[r][c];
			}
//			System.out.println("mapRemake !!!! c : "+c+", input : "+input);
			for (int r=0;r<12;r++) {
				if (12-input.length()>r) map[r][c]='.';
				else map[r][c] = input.charAt(len++);
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<12 && nc>=0 && nc<6;
	}
}
