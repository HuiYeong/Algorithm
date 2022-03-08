package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B10809_알파벳찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] alpha = new int[26];
		Arrays.fill(alpha, -1);
		String input = br.readLine();
		for (int i=0;i<input.length();i++) {
			char temp = input.charAt(i);
			if (alpha[(temp-'0')-49]==-1) alpha[(temp-'0')-49] = i;
		}
		
		for (int i=0;i<26;i++) System.out.print(alpha[i]+" ");
	}
}
