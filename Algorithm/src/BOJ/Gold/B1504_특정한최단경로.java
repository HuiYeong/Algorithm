package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1504_특정한최단경로 {
	static int N, M, V1, V2, INF; 
	static boolean flag1, flag2;
	static PriorityQueue<Node> queue;
	static List<List<Node>> list;

	static class Node implements Comparable<Node> {
		int to;
		long weight;

		public Node(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return to + " " + weight;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(from).add(new Node(to, weight));
			list.get(to).add(new Node(from, weight));
		}
		st = new StringTokenizer(br.readLine());
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
//		System.out.println("V1 : "+V1+", V2 : "+V2);
		// V1 -> V2
		long ans12 = 0;
		long temp1 = dijkstra(1, V1);
		long temp2 = dijkstra(V1, V2);
		long temp3 = dijkstra(V2, N);
		if (temp1!=INF && temp2!=INF && temp3!=INF) ans12 = temp1+temp2+temp3;
		
		long ans21 = 0;
		temp1 = dijkstra(1, V2);
		temp2 = dijkstra(V2, V1);
		temp3 = dijkstra(V1, N);
		if (temp1!=INF && temp2!=INF && temp3!=INF) ans21 = temp1+temp2+temp3;
		
		if (ans12 == 0 && ans21 == 0) System.out.println(-1);
		else if (ans12!=0 && ans21 == 0) System.out.println(ans12);
		else if (ans12==0 && ans21 !=0) System.out.println(ans21);
		else System.out.println(Math.min(ans12, ans21));
	}

	static long dijkstra(int start, int end) {
		long[] dis = new long[N+1];
		INF = 100000000;
		Arrays.fill(dis, INF);
		
		dis[start] = 0;
		queue = new PriorityQueue<>();
		queue.offer(new Node(start, 0));
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			if (now.to==end) return dis[now.to];
			
//			System.out.println("start : "+start+", end : "+end+" "+list.get(now.to).size());
//			if (now.to==N) continue;
			for (int i=0;i<list.get(now.to).size();i++) {
				int to = list.get(now.to).get(i).to;
				long value = list.get(now.to).get(i).weight;
				if (dis[to]>dis[now.to]+value) {
					dis[to] = dis[now.to]+value;
					queue.offer(new Node(to, dis[to]));
				}
			}
		} 
		return INF;
	}
}
