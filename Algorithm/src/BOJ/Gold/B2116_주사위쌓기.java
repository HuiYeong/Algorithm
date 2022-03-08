package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2116_주사위쌓기 {
	private static int[][] dice;
	private static int N, ans, MAX, temp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<6;j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		MAX = Integer.MIN_VALUE;
		for (int j=0;j<6;j++) { // 맨 밑에 있는 주사위가 가질 수 있는 경우의 수 6가지
			ans = 0;
			game(j);
		}
		System.out.println(MAX);
	}
	
	private static int select(int j) {
		if (j==0) return 5; // 밑에가 0이라면 위에는 5 (A->F)
		else if (j==1) return 3; // 밑에가 1이라면 위에는 3 (B->D)
		else if (j==2) return 4; // 밑에가 2라면 위에는 4 (C->E)
		else if (j==3) return 1;
		else if (j==4) return 2;
		else if (j==5) return 0;
		return 0;
	}
	
	private static void game(int downT) {
		for (int i=0;i<N;i++) { 
			int max = Integer.MIN_VALUE;
			int upT = select(downT);
			int up = dice[i][upT]; // 주사위 윗쪽 수
			for (int j=0;j<6;j++) {
				if (downT!=j && upT!=j) max = Math.max(max, dice[i][j]); // 주사위 위, 아래 제외한 나머지 중 제일 큰 값
				if (i+1<N && dice[i+1][j]==up) temp = j; // 다음 주사위의 아래는 현재 주사위의 위와 같음
			}
			ans+=max;
			downT = temp;
		}
		MAX = Math.max(MAX, ans);
	}
}
