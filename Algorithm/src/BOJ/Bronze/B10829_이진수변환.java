package BOJ.Bronze;

import java.util.ArrayList;
import java.util.Scanner;

public class B10829_이진수변환 {
	private static ArrayList<Long> count = new ArrayList<>();
	
	private static void recur(int cnt, long N) {
		count.add(N%2);
		if (N/2==0) return;
		recur(cnt+1, N/2);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		recur(0, N);
		
		for (int i=count.size()-1;i>=0;i--) {
			System.out.print(count.get(i));
		}
		sc.close();
	}
}
