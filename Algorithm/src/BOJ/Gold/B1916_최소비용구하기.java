package BOJ.Gold;

import java.io.*;
import java.util.*;

public class B1916_최소비용구하기 {
	static int N, M, start, end;
	static int[] dis;
	static List<List<Node>> list;
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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i=1;i<=N+1;i++) {
			list.add(new ArrayList<>());
		}
		StringTokenizer st = null;
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(from).add(new Node(to, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra();
		if (dis[end] == 987654321) System.out.println(0);
		else System.out.println(dis[end]);
	}
	
	static void dijkstra() {
		dis = new int[N+1];
		int INF = 987654321;
		Arrays.fill(dis, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		dis[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (now.to==end) break;
			
			for (int i=0;i<list.get(now.to).size();i++) {
				int to = list.get(now.to).get(i).to;
				int value = list.get(now.to).get(i).weight;
				if (dis[to]>dis[now.to]+value) {
					dis[to] = dis[now.to]+value;
					pq.add(new Node(to, dis[to]));
				}
			}
		}
	}
}
