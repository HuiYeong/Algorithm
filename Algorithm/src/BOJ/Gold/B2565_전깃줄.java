package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 가장 긴 증가하는 부분 수열 문제
 * 전봇대 하나의 순서를 오름차순으로 변경. 다른 전봇대도 오름차순으로 변경한 전봇대와 일치하도록 값 변경
 * 
 * 오름차순으로 정렬한 전봇대 : A, 오름차순으로 변경한 전봇대와 일치하도록 값을 변경한 전봇대 : B
 * 전봇대 A는 이미 오름차순으로 정렬, 전봇대 B는 이분 탐색으로 가장 긴 증가하는 부분 수열 탐색
 */
public class B2565_전깃줄 {
	static int N, search[], result[], size, idx;
	static class Number implements Comparable<Number> {
		int num, order;

		public Number(int num, int order) {
			super();
			this.num = num;
			this.order = order;
		}

		@Override
		public int compareTo(Number o) {
			return Integer.compare(this.num, o.num);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Number[] A = new Number[N];
		Number[] B = new Number[N];
		StringTokenizer st = null;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = new Number(Integer.parseInt(st.nextToken()), i);
			B[i] = new Number(Integer.parseInt(st.nextToken()), i);
		}
		Arrays.sort(A); // 오름차순으로 정렬 
		search = new int[N];
		for (int i=0;i<N;i++) {
			search[i] = B[A[i].order].num; // 오름차순으로 정렬한 값이 일치하는 순서로 다른 전봇대도 값 변경
		}
		
		result = new int[N];
		result[size++] = search[0];
		for (int i=1;i<N;i++) {
			idx = Arrays.binarySearch(result, 0, size, search[i]);
			if (idx >= 0) continue; // 이미 값이 있을 때는 건너뛰기
			// 값이 없다면 해당 값이 들어갈 수 있는 위치를 -를 붙여 알려줌
			if (idx == -(size+1)) result[size++] = search[i];
			else result[-(idx+1)] = search[i];
		}
		System.out.println(N-size);
	}
}
