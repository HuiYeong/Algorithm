package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 가장 긴 증가하는 부분 수열 문제
 *
 * 가장 긴 오름세 == 가장 긴 증가하는 부분 수열
 */
public class B3745_오름세 {
	static int N, arr[], result[], size, idx;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		String input = null;
		while((input = br.readLine())!=null) {
			N = Integer.parseInt(input.trim());
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
				// 값이 양수라면 이미 있다는 뜻
				if (idx >= 0) continue;
				// 값이 없다면 해당 값이 들어갈 수 있는 위치를 -를 붙여 알려줌
				if (idx == -(size+1)) result[size++] = arr[i];
				else result[-(idx+1)] = arr[i];
			}
			sb.append(size+"\n");
		}
		System.out.println(sb);
	}
}
