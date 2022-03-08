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
public class B2352_반도체설계 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] result = new int[N];
		int size = 0;
		result[size++] = arr[0];
		for (int i=1;i<N;i++) {
			int idx = Arrays.binarySearch(result, 0, size, arr[i]);
			// 값이 없다면 해당 값이 들어갈 수 있는 위치를 -를 붙여 알려줌
			if (idx == -(size+1)) result[size++] = arr[i];
			else result[-(idx+1)] = arr[i];
		}
		System.out.println(size);
	}
}