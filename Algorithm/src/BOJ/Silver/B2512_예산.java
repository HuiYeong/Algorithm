package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2512_예산 {
	static int N, M;
	static long sum, MAX, mid;
	static int[] budget;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		budget = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			sum+=budget[i];
		}
		M = Integer.parseInt(br.readLine());
		MAX = Long.MIN_VALUE;
		Arrays.sort(budget);
		if (sum<=M) System.out.println(budget[N-1]);
		else {
			binary(0, budget[N-1]);
			System.out.println(MAX);
		}
	}
	
	static void binary(long start, long end) {
		if (start>end) return;
		sum = 0;
		mid = (start+end)/2;
		for (int i=0;i<N;i++) {
			if (budget[i]>mid) sum+=mid;
			else sum+=budget[i];
		}
		
		if (sum>M) binary(start, mid-1);
		else {
			MAX = Math.max(MAX, mid);
			binary(mid+1, end);
		}
	}
}
