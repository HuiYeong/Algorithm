package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B6443_애너그램 {
	static char[] word;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			word = br.readLine().toCharArray();
			Arrays.sort(word);
			while(true) {
				for (int j=0;j<word.length;j++) {
					sb.append(word[j]);
				}
				sb.append("\n");
				if (!next_permutation()) break;
			}
		}
		System.out.println(sb);
	}
	
	static boolean next_permutation() {
		int i = word.length - 1;
		while(i>0 && word[i-1]>=word[i]) --i;
		if (i==0) return false;
		 
		int j = word.length - 1;
		while(j>0 && word[i-1]>=word[j]) --j;
		
		char temp = word[i-1];
		word[i-1] = word[j];
		word[j] = temp;
		
		int k = word.length-1;
		while(i<k) {
			temp = word[i];
			word[i] = word[k];
			word[k] = temp;
			++i; --k;
		}
		return true;
	}
}
