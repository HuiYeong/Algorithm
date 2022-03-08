package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15656_N과M7 {
	private static StringBuilder sb;
	private static int N, M;
	private static int[] numbers;
	private static int[] choosed;
	
	private static void permutation(int cnt) {
		if (cnt==M) {
			for (int i=0;i<M;i++) sb.append(choosed[i]+" ");
			sb.append("\n");
			return;
		}
		
		for (int i=0;i<N;i++) {
			choosed[cnt] = numbers[i];
			permutation(cnt+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		choosed = new int[M];
		st = new StringTokenizer(in.readLine()," ");
		for (int i=0;i<N;i++) numbers[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(numbers);
		permutation(0);
		System.out.println(sb);
	}
}
