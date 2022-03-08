package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9251_LCS {
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
		System.out.println(LCS[A.length()][B.length()]);
	}
}
