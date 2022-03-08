package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B1592_영식이와친구들 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		Deque<Integer> q = new ArrayDeque<>();
		for (int i=0;i<N;i++) {
			q.offerLast(0);
		}
		int temp = 0;
		int cnt = 0;
		while(true) {
			for (int i=0;i<L;i++) {
				if (temp%2==1) q.offerLast(q.pollFirst());
				else q.offerFirst(q.pollLast());
			}
			temp = q.pollFirst();
			temp++;
			if (temp == M) break;
			q.offerFirst(temp);
			cnt++;
		}
		
		System.out.println(cnt);
	}
}
