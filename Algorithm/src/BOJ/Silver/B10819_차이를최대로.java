package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10819_차이를최대로 {
	private static int[] numbers, arr;
	private static boolean[] isSelect;
	private static int N, MAX;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		numbers = new int[N];
		isSelect =  new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		MAX = Integer.MIN_VALUE;
		permutation(0);
		System.out.println(MAX);
	}
	
	private static void permutation(int cnt) {
		if (cnt==N) {
			int ans = 0;
			for (int i=0;i<N-1;i++) {
				ans+=Math.abs(numbers[i]-numbers[i+1]);
			}
			MAX = Math.max(MAX, ans);
			
			return;
		}
		
		for (int i=0;i<N;i++) {
			if (isSelect[i]) continue;
			
			numbers[cnt] = arr[i];
			isSelect[i] = true;
			permutation(cnt+1);
			isSelect[i] = false;
		}
	}
}