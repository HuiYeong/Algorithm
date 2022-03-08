package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2023_신기한소수 {
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dfs("", 0);
		System.out.println(sb);
	}
	
	static void dfs(String s, int cnt) {
		if (cnt==N) {
			sb.append(s+"\n");
			return;
		}
		for (int i=1;i<=9;i++) {
			if (isPrime(Integer.parseInt(s+i))) dfs(s+i, cnt+1);
		}
	}
	
	static boolean isPrime(int num) {
		if (num==1) return false;
		
		int sqrt = (int)Math.sqrt(num);
		for (int i=2;i<=sqrt; i++) {
			if (num%i==0) return false;
		}
		return true;
	}
}
