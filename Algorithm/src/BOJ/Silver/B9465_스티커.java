package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9465_스티커 {
	static int T, n, arr[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(T-->0) {
			n = Integer.parseInt(br.readLine());
			arr = new int[2][n];
			for (int i=0;i<2;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<n;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=1;i<n;i++) {
				// 현재 나의 점수(현재 나의 점수 + 나와 대각선 방향에 있는 점수) vs 이전 점수 (내 바로 옆에 있는 점수) 중 더 큰 값 저장
				arr[0][i] = Math.max(arr[0][i-1], arr[1][i-1]+arr[0][i]); 
				arr[1][i] = Math.max(arr[1][i-1], arr[0][i-1]+arr[1][i]);
			}
			
			sb.append(Math.max(arr[0][n-1], arr[1][n-1])+"\n");
		}
		System.out.println(sb);
	}
}
