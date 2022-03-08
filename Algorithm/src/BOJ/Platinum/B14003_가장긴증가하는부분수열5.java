package BOJ.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 가장 긴 증가하는 부분 수열
 * 이분 탐색 사용
 * 
 * 가장 긴 증가하는 부분 수열 4와 동일하게
 * 끝에서부터 dp 값이 바뀔 때마다 저장해줌 (역추적) 
 */
public class B14003_가장긴증가하는부분수열5 {
	static int size, idx, result[], dp[], ans[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		result = new int[N];
		dp = new int[N];
		dp[0] = 1;
		result[size++] = arr[0];
		for (int i=1;i<N;i++) {
			idx = Arrays.binarySearch(result, 0, size, arr[i]);
			if (idx >= 0) {
				dp[i] = dp[idx];
				continue;
			}
			if (idx == -(size+1)) {
				result[size++] = arr[i];
				dp[i] = size;
			}
			else {
				result[-(idx+1)] = arr[i];
				dp[i] = -(idx+1)+1;
			}
		}
		
		sb.append(size+"\n");
		idx = size+1;
		for (int i=N-1;i>=0;i--) {
			if (idx-1 != dp[i]) continue;
			result[dp[i]-1] = arr[i];
			idx--;
		}
		for (int i=0;i<size;i++) {
			sb.append(result[i]+" ");
		}
		System.out.println(sb);
	}
}
