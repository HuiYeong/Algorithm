package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10163_색종이 {
	private static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		arr = new int[101][101];
		int cnt = 1; // 들어오는 순서에 숫자 부여, 처음 들어온 위치는 1로, 그 다음 위치는 2로
		for (int n=0;n<N;n++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int startI = Integer.parseInt(st.nextToken()); // 시작 위치
			int startJ = Integer.parseInt(st.nextToken()); // 시작 위치
			int addI = Integer.parseInt(st.nextToken()); // 너비 길이
			int addJ = Integer.parseInt(st.nextToken());; // 높이 길이
			for (int i=startI;i<startI+addI;i++) {
				for (int j=startJ;j<startJ+addJ;j++) {
					arr[i][j] = cnt; // 순서에 따라 숫자 넣어주기
				}
			}
			cnt++;
		}
		
		// 1인 값 세기
		cnt = 1;
		for (int n=0;n<N;n++) {
			int ans = 0;
			for (int i=0;i<arr.length;i++) {
				for (int j=0;j<arr[0].length;j++) {
					if (arr[i][j]==cnt) ans++;
				}
			}
			sb.append(ans+"\n");
			cnt++;
		}
		System.out.println(sb);
	}
}
