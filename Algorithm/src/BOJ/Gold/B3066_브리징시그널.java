package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 가장 긴 증가하는 부분 수열 문제
 * 
 * 오름차순으로 정렬된 값에 맞춰 연결선이 서로 꼬이지 않으려면 가장 긴 증가하는 부분 수열을 구해야함
 */

public class B3066_브리징시그널 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(TC-->0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			for (int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(br.readLine());
			}
			
			int[] result = new int[N];
			int size = 0;
			result[size++] = arr[0];
			for (int i=1;i<N;i++) {
				int idx = Arrays.binarySearch(result, 0, size, arr[i]);
				if (idx == -(size+1)) result[size++] = arr[i];
				else result[-(idx+1)] = arr[i];
			}
			sb.append(size+"\n");
		}
		System.out.println(sb);
	}
}
