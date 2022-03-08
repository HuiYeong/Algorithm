package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * LCS 알고리즘 사용
 */
public class B1958_LCS3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		String C = br.readLine();
		int[][][] LCS = new int[A.length()+1][B.length()+1][C.length()+1];
		for (int i=0;i<=A.length();i++) {
			for (int j=0;j<=B.length();j++) {
				for (int k=0;k<=C.length();k++) {
					// 마진 설정
					if (i==0 || j==0 || k==0) LCS[i][j][k] = 0; 
					// 문자가 같다면 이전 문자에 저장된 값 + 1
					else if (A.charAt(i-1) == B.charAt(j-1) && B.charAt(j-1) == C.charAt(k-1)) LCS[i][j][k] = LCS[i-1][j-1][k-1] + 1; 
					// 문자가 다르다면 현재까지 탐색한 문자에서 더 큰 값 저장
					else LCS[i][j][k] = Math.max(LCS[i-1][j][k], Math.max(LCS[i][j-1][k], LCS[i][j][k-1]));
				}
			}
		}
		System.out.println(LCS[A.length()][B.length()][C.length()]);
	}
}
