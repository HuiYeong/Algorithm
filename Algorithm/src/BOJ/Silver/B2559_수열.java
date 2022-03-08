package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2559_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] t = new int[N]; // 온도 값 담을 배열
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}
		
		int MAX = Integer.MIN_VALUE;
		int cnt = 0;
		for (int k=0;k<K;k++) {
			cnt+=t[k];
		}
		MAX = Math.max(MAX, cnt);
		for (int i=1;i<=N-K;i++) {
			cnt -= t[i-1];
			cnt += t[i+K-1];
			MAX = Math.max(MAX, cnt);
		}
		System.out.println(MAX);
	}
}
