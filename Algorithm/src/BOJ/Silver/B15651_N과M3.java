package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15651_N과M3 {
	private static int[] choosed;
	private static StringBuilder sb;
	private static int N, M;

	private static void permutation(int cnt) {
		if (cnt==M) {
			for (int i=0;i<M;i++) {
				sb.append(choosed[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=1;i<N+1;i++) {
			choosed[cnt] = i;
			permutation(cnt+1);
		}
	}
	
	// 중복 순열
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		choosed = new int[M];
		permutation(0);
		System.out.println(sb);
	}
}
