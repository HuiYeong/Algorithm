package BOJ.Bronze;

import java.util.Scanner;

public class B11328_Strtry {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t=0;t<T;t++) {
			char[] word1 = sc.next().toCharArray();
			char[] word2 = sc.next().toCharArray();
			int[] count = new int[26];
			
			for (char ch : word1) count[ch-97]++;
			for (char ch : word2) count[ch-97]--;
			
			boolean success = true;
			for (int j=0;j<count.length;j++) {
				if (count[j]!=0) success = false;
			}
			
			if(success) System.out.println("Possible");
			else System.out.println("Impossible");
		}
		sc.close();
	}
}
