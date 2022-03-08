package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class B1525_퍼즐 {
	static int cnt, R, C, arr[][] = new int[3][3], dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static StringBuilder sb = new StringBuilder();
	static String ans = "123456780", visit;
	static Set<String> set = new HashSet<>();
	static Queue<Puzzle> queue = new LinkedList<>();
	static class Puzzle {
		int r, c;
		String map;

		public Puzzle(int r, int c, String map) {
			super();
			this.r = r;
			this.c = c;
			this.map = map;
		}

		@Override
		public String toString() {
			return "Puzzle [r=" + r + ", c=" + c + ", map=" + map + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int r=0;r<3;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<3;c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				if (arr[r][c] == 0) {
					R = r; C = c;
				}
			}
		}
		queue.offer(new Puzzle(R, C, arrToString(arr)));
		System.out.println(bfs());
	}
	
	static int bfs() {
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int s=0;s<size;s++) {
				Puzzle now = queue.poll();
				if ((now.r == 2 && now.c == 2) && ans.equals(now.map)) return cnt;
				for (int d=0;d<4;d++) {
					 int nr = now.r + dir[d][0];
					 int nc = now.c + dir[d][1];
					 if (!isIn(nr, nc)) continue;
					 
					 stringToArr(now.map);
					 
					 arr[now.r][now.c] = arr[nr][nc];
					 arr[nr][nc] = 0;
					 
					 visit = arrToString(arr);
					 
					 if (set.contains(visit)) continue;
					 
					 set.add(visit);
					 queue.offer(new Puzzle(nr, nc, visit));
				}
			}
			cnt++;
		}
		
		return -1;
	}
	
	static String arrToString(int[][] arr) {
		sb.delete(0, sb.length());
		for (int r=0;r<3;r++) {
			for (int c=0;c<3;c++) {
				sb.append(arr[r][c]);
			}
		}
		return sb.toString();
	}
	
	static void stringToArr(String map) {
		R = 0; C = 0;
		for (int i=0;i<map.length();i++) {
			arr[R][C++] = map.charAt(i)-'0';
			if (C>2) {
				R++; C = 0;
			}
		}
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<3 && nc>=0 && nc<3;
	}
}
