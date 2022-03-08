package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4963_섬의개수 {
	private static int[][] island;
	private static int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
	private static int W, H;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); // 높이
			W = Integer.parseInt(st.nextToken()); // 너비
			island = new int[W][H];
			if (W==0 && H==0) break;
			for (int w=0;w<W;w++) {
				st = new StringTokenizer(br.readLine());
				for (int h=0;h<H;h++) {
					island[w][h] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력완료
			int ans = 0;
			for (int w=0;w<W;w++) {
				for (int h=0;h<H;h++) {
					if (island[w][h]==1) { // 섬이면
						dfs(w, h); // dfs
						ans++;
					}
				}
			}
			sb.append(ans+"\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int w, int h) {
		island[w][h] = 0; // 방문체크
		for (int d=0;d<deltas.length;d++) {
			int nw = w+deltas[d][0];
			int nh = h+deltas[d][1];
			if (isIn(nw, nh) && island[nw][nh]==1) { 
				dfs(nw, nh);
			}
		}
	}
	
	private static boolean isIn(int nw, int nh) {
		return nw>=0 && nw<W && nh>=0 && nh<H;
	}
}
