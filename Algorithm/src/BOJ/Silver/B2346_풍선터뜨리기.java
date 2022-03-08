package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B2346_풍선터뜨리기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Deque<String> deque = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i=0;i<N;i++) {
			String num = st.nextToken()+" "+(i+1);
			deque.offerLast(num);
		}	
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(deque.pollFirst()," ");
			int num = Integer.parseInt(st.nextToken());
			sb.append(st.nextToken()+" ");
			if (deque.isEmpty()) break;
			if (num>0) {
				for (int j=0;j<num-1;j++) {
					deque.offerLast(deque.pollFirst());
				}
			}
			else {
				for (int j=0;j<Math.abs(num);j++) {
					deque.offerFirst(deque.pollLast());
				}
			}
		}
		
		System.out.println(sb);
	}
}
