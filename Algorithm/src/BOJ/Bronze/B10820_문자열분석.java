package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B10820_문자열분석 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = "";
		while((word = br.readLine())!=null) {
			int[] count = new int[4];
			for (int i=0;i<word.length();i++) {
				if (97<=word.charAt(i) && word.charAt(i)<=122) count[0]++;
				if (65<=word.charAt(i) && word.charAt(i)<=90) count[1]++;
				if (48<=word.charAt(i) && word.charAt(i)<=57) count[2]++;
			}
			count[3] = word.length() - count[0]-count[1]-count[2];
			
			for (int i=0;i<count.length;i++) System.out.print(count[i]+" ");
			System.out.println();
		}
	}
}
