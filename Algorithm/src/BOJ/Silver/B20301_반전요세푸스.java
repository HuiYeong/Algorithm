package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B20301_반전요세푸스 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i=1;i<=N;i++) {
			deque.offerLast(i);
		}
		int dis=0;
		boolean front = true;
		while(!deque.isEmpty()) {
			if (deque.size()!=N && dis%M ==0) {
				if (front) front = false;
				else front = true;
			}
			for (int i=1;i<K;i++) {
				if (front) deque.offerLast(deque.pollFirst());
				else deque.offerFirst(deque.pollLast());
			}
			if (front) sb.append(deque.pollFirst()+"\n");
			else sb.append(deque.pollLast()+"\n");
			dis++;
		}
		System.out.println(sb);
	}
}
