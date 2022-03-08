package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2999_비밀이메일 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String m = br.readLine();
		int N = m.length();
		int r = 0, c = 0;
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				if (i*j==N && i<=j) {
					c = i;
					r = j;
				}
			}
		}
		char[][] secret = new char[r][c];
		int cnt = 0;
		for (int i=0;i<r;i++) {
			for (int j=0;j<c;j++) {
				secret[i][j] = m.charAt(cnt++);
			}
		}
		
		for (int i=0;i<c;i++) {
			for (int j=0;j<r;j++) {
				sb.append(secret[j][i]);
			}
		}
		System.out.println(sb);
	}
}
