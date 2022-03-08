package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2578_빙고 {
	private static int[][] bingo;
	private static int ans;
	private static boolean flag, flag2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		bingo = new int[5][5];
		for (int i=0;i<5;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<5;j++) {
				bingo[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		ans = 0;
		flag = false;
		flag2 = false;
		// 사회자가 수 부름
		outer:for (int i=0;i<5;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<5;j++) {
				int num = Integer.parseInt(st.nextToken()); // 사회자가 부른 수
				// bingo배열에서 해당 값 찾기
				inner:for (int r=0;r<5;r++) {
					for (int c=0;c<5;c++) {
						if (bingo[r][c] == num) {
							bingo[r][c] = 0; // 찾으면 0으로 바꿔줌
							cnt++;
							if (cnt >= 5) ans = check(r, c); // cnt가 5이상 즉 빙고가 만들어질 최소 cnt값이라면 검사
							if (ans>=3) break outer;
							break inner;
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}
	
	private static int check(int r, int c) {
		for (int t=0;t<4;t++) {
			int cnt = 0;
			for (int i=0,j=4;i<5;i++) {
				if (t==0 && bingo[r][i] == 0) cnt++; // 행 확인
				else if (t==1 && bingo[i][c]==0) cnt++; // 열 확인
				else if (!flag2 && t==2 && bingo[i][i]==0) {
					cnt++;
					if (cnt==5) flag2 = true;
				}
				else if (!flag && t==3 && bingo[i][j--]==0) {
					cnt++;
					if (cnt==5) flag = true;
				}
			}
			if (cnt==5) ans++;
		}
		return ans;
	}
}
