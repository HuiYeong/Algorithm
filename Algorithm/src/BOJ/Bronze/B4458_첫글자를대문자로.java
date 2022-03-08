package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4458_첫글자를대문자로 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		int T = Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			sb = new StringBuilder(br.readLine());
			String upper = sb.substring(0, 1).toUpperCase();
			sb.replace(0, 1, upper);
			System.out.println(sb);
		}
	}
}
