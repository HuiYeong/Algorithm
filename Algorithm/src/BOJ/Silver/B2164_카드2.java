package BOJ.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class B2164_카드2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i=1;i<=N;i++) deque.offerLast(i);
		while(deque.size()>1) {
			deque.pollFirst();
			deque.offerLast(deque.pollFirst());
		}
		System.out.println(deque.peekFirst());
	}
}
