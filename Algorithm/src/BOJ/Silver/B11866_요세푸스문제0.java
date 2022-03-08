package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B11866_요세푸스문제0 {
	private static StringBuilder sb;
	private static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Queue<Integer> yo = new LinkedList<>();
		for (int i=0;i<N;i++) {
			yo.offer(i+1);
		}
		sb.append("<");
		while(!yo.isEmpty()) {
			for (int i=1;i<K;i++) {
				yo.offer(yo.poll());
			}
			sb.append(yo.poll());
			if(yo.size()!=0) sb.append(", ");
		}
		sb.append(">");
		System.out.println(sb);
	}
}
