package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9252_LCS2 {
	static int r, c, nr, nc;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		int[][] LCS = new int[A.length()+1][B.length()+1];
		for (int i=0;i<=A.length();i++) {
			for (int j=0;j<=B.length();j++) {
				// 마진 설정
				if (i==0 || j==0) LCS[i][j] = 0;
				// 문자가 같다면 이전 문자에 저장된 값 + 1
				else if (A.charAt(i-1) == B.charAt(j-1)) LCS[i][j] = LCS[i-1][j-1] + 1; 
				// 문자가 다르다면 현재까지 탐색한 문자에서 더 큰 값 저장
				else LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
			}
		}
		StringBuilder sb = new StringBuilder();
		int[][] dir = {{-1, 0}, {0, -1}};
		r = A.length(); c = B.length();
		if (LCS[A.length()][B.length()] == 0) { // 부분 수열이 아예 없으면
			System.out.println(0); // 0 출력해주고
			System.exit(0); // 종료
		}
		while(true) {
			boolean flag = false;
			for (int d=0;d<2;d++) { // 왼쪽, 위쪽 검사 (현재까지 탐색한 문자 검사)
				nr = r + dir[d][0];
				nc = c + dir[d][1];
				if (LCS[r][c] != LCS[nr][nc]) continue;
				flag = true; 
				break;
			}
			if (flag) { // 현재 위치와 왼쪽 또는 위쪽과 값이 같다면
				r = nr; c = nc; // 해당 위치로 이동
			} else { // 같지 않다면 부분 수열
				sb.append(B.charAt(c-1)); // 값 저장 
				r = r-1; c = c-1; // 현재 문자의 앞 문자로 이동
				if (LCS[r][c] == 0) break; // LCS 값이 0이라면 탐색 끝
			}
		}
		System.out.println(LCS[A.length()][B.length()]+"\n"+sb.reverse());
	}
}
