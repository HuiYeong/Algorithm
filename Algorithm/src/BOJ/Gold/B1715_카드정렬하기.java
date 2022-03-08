package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B1715_카드정렬하기 {
	static class Card implements Comparable<Card> {
		long num;

		public Card(long num) {
			super();
			this.num = num;
		}

		@Override
		public int compareTo(Card o) {
			return Long.compare(this.num, o.num);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Card> cards = new PriorityQueue<>();
		for (int i=0;i<N;i++) {
			cards.offer(new Card(Integer.parseInt(br.readLine())));
		}
		long ans = 0;
		while(cards.size()>1) {
			long sum = cards.poll().num + cards.poll().num;
			ans+=sum;
			cards.offer(new Card(sum));
		}
		System.out.println(ans);
	}
}
