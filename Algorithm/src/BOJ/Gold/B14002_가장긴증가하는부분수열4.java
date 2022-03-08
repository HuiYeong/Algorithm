package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 가장 긴 증가하는 부분 수열 문제
 * dp 사용하여 값 구하고
 * 끝에서부터 dp 값이 바뀔 때마다 저장해줌 (역추적) 
 */
public class B14002_가장긴증가하는부분수열4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[N];
		dp[0] = 1;
		int MAX = 1;
		for (int i=1;i<N;i++) {
			dp[i] = 1;
			for (int j=i-1;j>=0;j--) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					MAX = Math.max(dp[i], MAX);
				} 
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(MAX+"\n");
		int idx = MAX+1;
		int[] result = new int[MAX];
		for (int i=N-1;i>=0;i--) {
			if (idx-1 != dp[i]) continue;
			result[dp[i]-1] = arr[i];
			idx--;
		}
		for (int i=0;i<MAX;i++) {
			sb.append(result[i]+" ");
		}
		System.out.println(sb);
	}
}
