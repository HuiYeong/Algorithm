package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B18428_감시피하기 {
	static int N;
	static boolean flag;
	static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[] isSelect;
	static char[][] map, temp;
	static List<Point> list, teacher;
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return r+" "+c;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		teacher = new ArrayList<>();
		list = new ArrayList<>();
		map = new char[N][N];
		StringTokenizer st = null;
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = st.nextToken().charAt(0);
				if (map[r][c]=='T') teacher.add(new Point(r, c));
				else if (map[r][c]=='X') list.add(new Point(r, c));
			}
		}
		
		isSelect = new int[3];
		combination(0, 0);
		System.out.println("NO");
	}
	
	static void combination(int cnt, int start) {
		if (cnt == 3) {
			temp = new char[N][N];
			for (int r=0;r<N;r++) {
				for (int c=0;c<N;c++) {
					temp[r][c] = map[r][c];
				}
			}
			surve();
			return;
		}
		
		for (int i=start;i<list.size();i++) {
			isSelect[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
	
	static void surve() {
		for (int i=0;i<3;i++) {
			int r = list.get(isSelect[i]).r;
			int c = list.get(isSelect[i]).c;
			temp[r][c] = 'W';
		}
		
		for (int i=0;i<teacher.size();i++) {
			Point now = teacher.get(i);
			for (int d=0;d<4;d++) {
				flag = check(now.r, now.c, d);
				if (!flag) return;
			}
		}
		
		System.out.println("YES");
		System.exit(0);
	}
	
	static boolean check(int r, int c, int d) {
		while(true) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (!isIn(nr, nc) || temp[nr][nc] == 'T' || temp[nr][nc]=='W') return true;
			else if (temp[nr][nc]=='S') return false;
			r = nr; c = nc;
		}
		
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
