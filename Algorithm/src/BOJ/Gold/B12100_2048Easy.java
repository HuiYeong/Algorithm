package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12100_2048Easy {
	static int N, ans, idx, max;
	static boolean flag[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		StringTokenizer st = null;
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0) max = Math.max(max, map[r][c]);
			}
		}
		recrusion(0, map);
		System.out.println(ans);
	}
	
	static void recrusion(int cnt, int map[][]) {
		if (cnt == 5) {
			ans = Math.max(max, ans);
			return;
		}
		
		for (int i=0;i<4;i++) {
			flag = new boolean[N][N];
			int[][] temp = new int[N][N];
			copy(map, temp);
			if (i==0) up(temp);
			else if (i==1) down(temp);
			else if (i==2) left(temp);
			else if (i==3) right(temp);
			recrusion(cnt+1, temp);
		}
	}
	
	static void copy(int[][] map, int[][] temp) {
		for (int r=0;r<N;r++) {
			for (int c=0;c<N;c++) {
				temp[r][c] = map[r][c];
			}
		}
	}
	
	static void up(int[][] temp) {
		for (int c=0;c<N;c++) {
			idx = 0;
			for (int r=0;r<N;r++) {
				if (temp[r][c] != 0) {
					if (r == 0) {
						idx++;
						continue;
					}
					if (idx == 0) temp[idx++][c] = temp[r][c];
					else if ((temp[idx-1][c] != temp[r][c]) || (temp[idx-1][c] == temp[r][c] && flag[idx-1][c])) {
						temp[idx++][c] = temp[r][c];
						if (r == idx-1) continue;
					}
					else if (temp[idx-1][c] == temp[r][c] && !flag[idx-1][c]) {
						temp[idx-1][c] *= 2;
						max = Math.max(max, temp[idx-1][c]);
						flag[idx-1][c] = true;
					}
					temp[r][c] = 0;
				}
			}
		}
	}
	
	static void down(int[][] temp) {
		for (int c=0;c<N;c++) {
			idx = N-1;
			for (int r=N-1;r>=0;r--) {
				if (temp[r][c] != 0) {
					if (r == N-1) {
						idx--;
						continue;
					}
					if (idx == N-1) temp[idx--][c] = temp[r][c];
					else if ((temp[idx+1][c] != temp[r][c]) || (temp[idx+1][c] == temp[r][c] && flag[idx+1][c])) {
						temp[idx--][c] = temp[r][c];
						if (r == idx+1) continue;
					}
					else if (temp[idx+1][c] == temp[r][c] && !flag[idx+1][c]) {
						temp[idx+1][c] *= 2;
						max = Math.max(max, temp[idx+1][c]);
						flag[idx+1][c] = true;
					}
					temp[r][c] = 0;
				}
			}
		}
	}
	
	static void left(int[][] temp) {
		for (int r=0;r<N;r++) {
			idx = 0;
			for (int c=0;c<N;c++) {
				if (temp[r][c] != 0) {
					if (c == 0) {
						idx++;
						continue;
					}
					if (idx == 0) temp[r][idx++] = temp[r][c];
					else if ((temp[r][idx-1] != temp[r][c]) || (temp[r][idx-1] == temp[r][c] && flag[r][idx-1])) {
						temp[r][idx++] = temp[r][c];
						if (c == idx-1) continue;
					}
					else if (temp[r][idx-1] == temp[r][c] && !flag[r][idx-1]) {
						temp[r][idx-1] *= 2;
						max = Math.max(max, temp[r][idx-1]);
						flag[r][idx-1] = true;
					}
					temp[r][c] = 0;
				}
			}
		}
	}
	
	static void right(int[][] temp) {
		for (int r=0;r<N;r++) {
			idx = N-1;
			for (int c=N-1;c>=0;c--) {
				if (temp[r][c] != 0) {
					if (c == N-1) {
						idx--;
						continue;
					}
					if (idx == N-1) temp[r][idx--] = temp[r][c];
					else if ((temp[r][idx+1] != temp[r][c]) || (temp[r][idx+1] == temp[r][c] && flag[r][idx+1])) {
						temp[r][idx--] = temp[r][c];
						if (c == idx+1) continue;
					}
					else if (temp[r][idx+1] == temp[r][c] && !flag[r][idx+1]) {
						temp[r][idx+1] *= 2;
						max = Math.max(max, temp[r][idx+1]);
						flag[r][idx+1] = true;
					}
					temp[r][c] = 0;
				}
			}
		}
	}
}
