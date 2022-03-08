package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1197_최소스패닝트리 {
	static int[] parents;
	static Edge[] edgeList;
	static int V, E;
	static class Edge implements Comparable<Edge>{
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
	
	static void makeSet() {
		for (int i=1;i<=V;i++) {
			parents[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (parents[x]<0) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parents = new int[V+1];
		edgeList = new Edge[E];
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		makeSet();
		Arrays.sort(edgeList);
		long ans = 0;
		int cnt = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				ans+=edge.weight;
				if (++cnt == V-1) break;
			}
		}
		
		System.out.println(ans);
	}
}
