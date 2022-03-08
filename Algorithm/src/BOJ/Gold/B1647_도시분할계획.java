package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1647_도시분할계획 {
	static int V, E, MIN;
	static PriorityQueue<Edge> edgeList;
	static int[] roots;
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return from+" "+to+" "+weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new PriorityQueue<>();
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.offer(new Edge(from, to, weight));
		}
		
		roots = new int[V+1];
		makeSet();
		int cost = 0;
		int cnt = 0;
		int MAX = Integer.MIN_VALUE;
		while(!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();
			if (union(edge.from, edge.to)) {
				cost += edge.weight;
				MAX = Math.max(MAX, edge.weight);
				if (++cnt == V-1) {
					System.out.println(cost-MAX);
					return;
				}
			}
		}
	}
	
	static void makeSet() {
		for (int i=1;i<=V;i++) {
			roots[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (roots[x]<0) return x;
		
		return roots[x] = findSet(roots[x]);
	}
	
	static boolean union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x==y) return false;
		roots[y] = x;
		return true;
	}
}
