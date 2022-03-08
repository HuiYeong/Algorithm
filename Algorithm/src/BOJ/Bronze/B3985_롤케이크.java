package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3985_롤케이크 {
	private static int[] cake;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int expectMax = Integer.MIN_VALUE;
		int[] expectN = new int[2]; // 0 -> 방청객 번호, 1 -> 기대 개수
		int Max = Integer.MIN_VALUE;
		int[] maxN = new int[2]; 
		cake = new int[L+1];
		for (int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int P = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			expectMax = Math.max(expectMax, K-P);
			if (expectMax==K-P) {
				if (expectN[1]==expectMax) expectN[0] = Math.min(expectN[0], i);
				else expectN[0] = i; expectN[1] = expectMax;
			}
			int max = 0;
			for (int j=P;j<=K;j++) {
				if (cake[j]==1) continue;
				cake[j] = 1;
				max++;
			}
			Max = Math.max(max, Max);
			if (Max==max) {
				if (maxN[1]==Max) maxN[0] = Math.min(maxN[0], i);
				else maxN[0] = i; maxN[1] = Max;
			}
		}
		
		sb.append(expectN[0]+" "+maxN[0]);
		System.out.println(sb);
	}
}
