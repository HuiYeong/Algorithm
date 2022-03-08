package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1806_부분합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) {
			int value = Integer.parseInt(st.nextToken());
			arr[i] = arr[i-1] + value;
			if (value >= S) {
				System.out.println(1);
				System.exit(0);
			}
		}
		// 입력 완료
		
		if (arr[1] == S || S == 0 || arr[N] < S) {
			System.out.println(arr[1]==S?1:0);
			System.exit(0);
		}
		int start = 0, end = 2, MIN = Integer.MAX_VALUE;
		while(true) {
			if (N<=start || (end >= N && arr[end]-arr[start]<S)) break;
			
			if (end < N && arr[end]-arr[start] < S) end++;
			else if (arr[end]-arr[start] >= S) {
				MIN = Math.min(MIN, end-start);
				start++;
			}
		}
		
		System.out.println(MIN==Integer.MAX_VALUE?0:MIN);
	} 
}
