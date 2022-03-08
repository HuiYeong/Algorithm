package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1182_부분수열의합 {
	private static int[] numbers;
	private static int N, S, ans;
	private static boolean[] isSelect;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		isSelect = new boolean[N];
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		powerset(0);
		System.out.println(ans);
	}
	
	private static void powerset(int cnt) {
		if (cnt == N) {
			int sum = 0, count = 0;
			for (int i=0;i<N;i++) {
				if (isSelect[i]) {
					count++;
					sum+=numbers[i];
				}
			}
			
			if (count!=0 && sum==S) ans++;
			return;
		}
		
		isSelect[cnt] = true;
		powerset(cnt+1);
		isSelect[cnt] = false;
		powerset(cnt+1);
	}
}
