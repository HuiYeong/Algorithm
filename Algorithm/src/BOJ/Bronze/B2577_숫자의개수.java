package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2577_숫자의개수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(in.readLine());
		int B = Integer.parseInt(in.readLine());
		int C = Integer.parseInt(in.readLine());
		long multi = A*B*C;
		String word = String.valueOf(multi);
		int[] count = new int[10];
		for (int i=0;i<word.length();i++) {
			count[word.charAt(i)-'0']++;
		}
		
		for (int j=0;j<count.length;j++) System.out.println(count[j]);
	}
}
