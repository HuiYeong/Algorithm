package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11024_더하기4 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc=0;tc<T;tc++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine(), " ");
			while(st.hasMoreTokens()) sum+=Integer.parseInt(st.nextToken());
			sb.append(sum+"\n");
		}
		System.out.println(sb);
	}
}
