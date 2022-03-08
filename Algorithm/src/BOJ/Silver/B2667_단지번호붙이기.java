package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B2667_단지번호붙이기 {
	private static int N, complex, cnt;
	private static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
	private static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i=0;i<N;i++) {
			String temp = br.readLine();
			for (int j=0;j<N;j++) {
				map[i][j] = temp.charAt(j)-'0';
			}
		}
		// 입력완료
		
		complex = 1;
		List<Integer> totalCnt = new ArrayList<>(); 
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (map[i][j]==1) {
					cnt = 1;
					complex++;
					dfs(i, j);
					totalCnt.add(cnt);
				}
			}
		}
		Collections.sort(totalCnt);
		sb.append(totalCnt.size()+"\n");
		for (int i=0;i<totalCnt.size();i++) {
			sb.append(totalCnt.get(i)+"\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int r, int c) {
		map[r][c] = complex;
		for (int d=0;d<4;d++) {
			int nr = r+deltas[d][0];
			int nc = c+deltas[d][1];
			if (isIn(nr, nc) && map[nr][nc]==1) {
				cnt++;
				dfs(nr, nc);
			}
		}
		return;
	}
	
	private static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<N && nc>=0 && nc<N;
	}
}
