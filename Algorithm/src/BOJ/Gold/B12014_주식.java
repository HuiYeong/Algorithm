package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 가장 긴 증가하는 부분 수열 문제
 * 
 * 오름차순으로 정렬된 값에 맞춰 연결선이 서로 꼬이지 않으려면 가장 긴 증가하는 부분 수열을 구해야함
 */
public class B12014_주식 {
	static int TC, N, K, arr[], result[], size, idx;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			result = new int[N];
			size = 0;
			result[size++] = arr[0];
			for (int i=1;i<N;i++) {
				idx = Arrays.binarySearch(result, 0, size, arr[i]);
				// 인덱스가 양수라면 이미 있는 값
				if (idx >= 0) continue;
				// 값이 없다면 해당 값이 들어갈 수 있는 위치를 -를 붙여 알려줌
				if (idx == -(size+1)) result[size++] = arr[i];
				else result[-(idx+1)] = arr[i];
			}
			sb.append("Case #"+tc+"\n");
			// 주식 사는 일이 가장 긴 증가하는 부분 수열 개수보다 크면 성공, 작으면 실패
			sb.append((size>=K?1:0)+"\n"); 
		}
		System.out.println(sb);
	}
}
