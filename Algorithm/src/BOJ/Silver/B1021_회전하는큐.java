package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B1021_회전하는큐 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> deque = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i=1;i<=N;i++) deque.offerLast(i);
		st = new StringTokenizer(in.readLine(), " ");
		int[] pick = new int[M];
		int cnt = 0;
		for (int i=0;i<M;i++ ) {
			pick[i] = Integer.parseInt(st.nextToken());
			int menu = 0, search = 1;
			Iterator<Integer> iter = deque.iterator();
			while(iter.hasNext()) {
				if (iter.next().equals(pick[i])) break;
				else search++;
			}
			
			menu = deque.size()-search<deque.size()/2?1:0;
			
			while(true) {
				if (pick[i] == deque.peekFirst()) {
					deque.pollFirst();
					break;
				}
				else {
					if (menu == 0) deque.offerLast(deque.pollFirst());
					else deque.offerFirst(deque.pollLast());
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
