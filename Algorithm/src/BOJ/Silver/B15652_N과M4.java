package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B15652_N과M4 {
	private static int[] choosed;
	private static StringBuilder sb;
	private static int N, M;

	private static void combination(int cnt, int start) {
		if (cnt==M) {
			for (int i=0;i<M;i++) {
				sb.append(choosed[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=start;i<N+1;i++) {
			choosed[cnt] = i;
			combination(cnt+1, i);
		}
	}
	
	// 중복 조
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		choosed = new int[M];
		combination(0, 1);
		System.out.println(sb);
	}
}
