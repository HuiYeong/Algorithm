package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B8595_히든넘버 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String word = br.readLine();
		long hidden = 0;
		int inteN = 0;
		boolean succ = false;
		for (int i=0;i<word.length();i++) {
			if (48<=word.charAt(i) && word.charAt(i)<=57) {
				succ = true;
				inteN++;
			}
			else {
				if (succ) {
					hidden+= Long.parseLong(word.substring(i-inteN, i));
					succ = false;
					inteN = 0;
				}
			}
		}
		if (succ) {
			hidden+= Long.parseLong(word.substring(word.length()-inteN, word.length()));
		}
		
		System.out.println(hidden);
	}
}
