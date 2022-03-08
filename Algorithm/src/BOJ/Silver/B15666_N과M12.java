package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15666_N과M12 {
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
		combination(0, 1);
		System.out.println(sb);
	}
	
	private static void combination(int cnt, int start) {
		if (cnt == M) {
			for (int i=0;i<M;i++) {
				sb.append(num[numbers[i]]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=start;i<=N;i++) {
			if (num[i-1]==num[i]) continue;
			numbers[cnt] = i;
			combination(cnt+1, i);
		}
	}
}
