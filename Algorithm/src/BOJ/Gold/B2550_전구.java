package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 가장 긴 증가하는 부분 수열
 * 이분 탐색 사용
 * 
 * 가장 긴 증가하는 부분 수열 5 + 전깃줄 2 를 섞은 문제
 * 끝에서부터 dp 값이 바뀔 때마다 저장해줌 (역추적) 
 */
public class B2550_전구 {
	static int N, search[], dp[], result[], size, idx;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			A[i] = new Number(Integer.parseInt(st.nextToken()), i);
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			B[i] = new Number(Integer.parseInt(st.nextToken()), i);
		}
		
		Arrays.sort(A); // 오름차순으로 정렬
		search = new int[N];
		for (int i=0;i<N;i++) {
			search[i] = A[B[i].num-1].order; // 오름차순으로 정렬한 값이 일치하는 순서로 다른 전봇대도 값 변경
		}
		
		dp = new int[N];
		result = new int[N];
		result[size++] = search[0];
		dp[0] = 1;
		for (int i=1;i<N;i++) {
			idx = Arrays.binarySearch(result, 0, size, search[i]);
			// 값이 없다면 해당 값이 들어갈 수 있는 위치를 -를 붙여 알려줌
			if (idx == -(size+1)) {
				result[size++] = search[i];
				dp[i] = size;
			}
			else {
				result[-(idx+1)] = search[i];
				dp[i] = -(idx+1)+1;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(size+"\n");
		idx = size+1;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i=N-1;i>=0;i--) {
			if (idx-1 != dp[i]) continue;
			pq.add(B[i].num); 
			idx--;
		}
		while(!pq.isEmpty()) {
			sb.append(pq.poll()+" ");
		}
		System.out.println(sb);
	}
}
