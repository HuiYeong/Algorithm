package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10157_자리배정 {
	private static int[][] play;
	private static int seat, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		play = new int[R][C];
		seat = Integer.parseInt(br.readLine());
		cnt = 1;
		if (seat > R * C)
			sb.append(0);
		else {
			int dir = 0;
			int r = 0, c = 0;
			while (cnt <= R * C) {
				if (cnt == seat) break;
				play[r][c] = cnt++;
				if (dir == 0) { // 우
	                 if (c + 1 < C && play[r][c + 1] == 0) c++;
	                 else {
	                    dir = 1;
	                    r++;
	                 }

	              } else if (dir == 1) { // 하
	                 if (r + 1 < R && play[r + 1][c] == 0) r++;
	                 else {
	                    dir = 2;
	                    c--;
	                 }
	              } else if (dir == 2) { // 좌
	                 if (c - 1 >= 0 && play[r][c - 1] == 0) c--;
	                 else {
	                    dir = 3;
	                    r--;
	                 }
	              } else if (dir == 3) { // 상
	                 if (r - 1 < R && play[r-1][c] == 0) r--;
	                 else {
	                    dir = 0;
	                    c++;
	                 }
	              }
			}
			sb.append((r + 1) + " " + (c + 1));
		}
		System.out.println(sb);
	}
}
