package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15665_N과M11 {
	private static int N, M;
	private static int[] numbers, num;
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[M];
		num = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num);
		permutation(0);
		System.out.println(sb);
	}
	
	private static void permutation(int cnt) {
		if (cnt==M) {
			for (int i=0;i<M;i++) {
				sb.append(num[numbers[i]]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=1;i<=N;i++) {
			if (num[i-1]==num[i]) continue;
			numbers[cnt] = i;
			permutation(cnt+1);
		}
	}
}
