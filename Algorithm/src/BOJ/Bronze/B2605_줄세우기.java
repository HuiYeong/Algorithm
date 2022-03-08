package BOJ.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B2605_줄세우기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> d = new ArrayDeque<>();
		Deque<Integer> temp = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) {
			int num = Integer.parseInt(st.nextToken());
			for (int j=0;j<num;j++) {
				temp.offerLast(d.pollLast());
			}
			d.offerLast(i);
			while(!temp.isEmpty()) {
				d.offerLast(temp.pollLast());
			}
		}
		
		for (int i=0;i<N;i++) {
			sb.append(d.pollFirst());
			if (d.size()!=0) sb.append(" ");
		}
		System.out.println(sb);
	}
}
