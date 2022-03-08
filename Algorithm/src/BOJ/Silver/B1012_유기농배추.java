package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1012_유기농배추 {
	private static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
	private static int[][] arr;
	private static Queue<int[]> bfs;
	private static int N, M;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t=0;t<tc;t++) { // 테스트케이스
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			M = Integer.parseInt(st.nextToken()); // 밭 가로길이
			N = Integer.parseInt(st.nextToken()); // 밭 세로길이
			int K = Integer.parseInt(st.nextToken()); // 배추 개수
			int Answer = 0;
			bfs = new ArrayDeque<>();
			arr = new int[N][M]; // 
			for (int k=0;k<K;k++) {
				st = new StringTokenizer(br.readLine()," ");
				int y = Integer.parseInt(st.nextToken()); 
				int x = Integer.parseInt(st.nextToken());
				arr[x][y] = 1; // 배추 있으면 1
			}
			
			for (int i=0;i<N;i++) {
				for (int j=0;j<M;j++) {
					if (arr[i][j]==1) { // 배추가 있다
						bfs(i, j);
						Answer++;
					}
				}
			}
			sb.append(Answer+"\n");
		}
		System.out.println(sb);
	}
	
	private static boolean bfs(int x, int y) {
		bfs.offer(new int[] {x, y});
		
		while(!bfs.isEmpty()) {
			int[] now = bfs.poll();
			
			for (int d=0;d<deltas.length;d++) { 
				int nx = now[0]+deltas[d][0];
				int ny = now[1]+deltas[d][1];
				if (isIn(nx, ny) && arr[nx][ny]==1) { // 범위 벗어나지 않고 배추가 심어져있다
					bfs.offer(new int[] {nx, ny});
					arr[nx][ny] = 0;
				}
			}
		}
		
		return true;
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx>=0 && nx<N && ny>=0 && ny<M;
	}
}
