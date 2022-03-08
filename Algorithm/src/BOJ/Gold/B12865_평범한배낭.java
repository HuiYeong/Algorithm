package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12865_평범한배낭 {
	static class good {
		int w, v;

		public good(int w, int v) {
			super();
			this.w = w;
			this.v = v;
		}

		@Override
		public String toString() {
			return w+" "+v;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		good[] goods = new good[N+1];
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			goods[i] = new good(w, v);
		}
		
		int[][] dp = new int[N+1][K+1];
		// 기저조건에 대한 처리 : {}, 0kg --> 어차피 0
		for (int i=1;i<=N;i++) {
			// 해당 상품까지 고려했을 때 무게를 증가시켜가며 최대 만족도를 적용해보자
			for (int k=0;k<=K;k++) {
				// 못넣는 경우
				if (goods[i].w>k) dp[i][k] = dp[i-1][k];
				// 넣을 수 있을 경우
				else dp[i][k] = Math.max(dp[i-1][k], dp[i-1][k-goods[i].w]+goods[i].v); // 안 넣을 경우와 넣을 경우 비교해서 최댓값 저장
			}
		}
		
		System.out.println(dp[N][K]);
		
		
//		int[] w = new int[N+1];
//		int[] v = new int[N+1];
//		for (int i=1;i<=N;i++) {
//			st = new StringTokenizer(br.readLine());
//			w[i] = Integer.parseInt(st.nextToken());
//			v[i] = Integer.parseInt(st.nextToken());
//		}
//		int[][] dp = new int[N+1][K+1];
//		for (int i=1;i<=N;i++) {
//			for (int j=1;j<=K;j++) {
//				if (w[i]>j) dp[i][j] = dp[i-1][j];
//				else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
//			}
//		}
//		
//		System.out.println(dp[N][K]);
	}
}
