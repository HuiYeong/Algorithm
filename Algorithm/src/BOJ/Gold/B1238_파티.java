package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1238_파티 {
	static int N, M, X, MAX, ans;
	static List<List<Node>> list;
	static PriorityQueue<Node> pq;
	static class Node implements Comparable<Node>{
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
		}
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(from).add(new Node(to, weight));
		}
		MAX = Integer.MIN_VALUE;
		for (int i=1;i<list.size();i++) {
			ans = 0;
			int start = i;
			if (start!=X) {
				ans += dijkstra(start, X);
				ans += dijkstra(X, start);
				MAX = Math.max(MAX, ans);
			}
		}
		System.out.println(MAX);
	}
	
	static int dijkstra(int start, int end) {
		int INF = 987654321;
		int[] dis = new int[N+1];
		Arrays.fill(dis, INF);
		pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dis[start] = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (now.to==end) return dis[now.to];
			
			for (int i=0;i<list.get(now.to).size();i++) {
				int go = list.get(now.to).get(i).to;
				int value = list.get(now.to).get(i).weight;
				if (dis[go]>dis[now.to]+value) {
					dis[go] = dis[now.to]+value;
					pq.offer(new Node(go, dis[go]));
				}
			}
		}
		
		return -1;
	}
}
