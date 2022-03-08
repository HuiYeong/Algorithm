package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B16398_행성연결 {
	static int N;
	static int[] roots;
	static List<Edge> edgeList;
	static class Edge implements Comparable<Edge>{
		int from ,to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return from+" "+to+" "+weight;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N  = Integer.parseInt(br.readLine());
		edgeList = new ArrayList<>();
		roots = new int[N+1];
		StringTokenizer st = null;
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<=N;j++) {
				int weight = Integer.parseInt(st.nextToken()); 
				if (i==j) continue;
				edgeList.add(new Edge(i, j, weight));
			}
		}
		Collections.sort(edgeList);
		makeSet();
		long cost = 0; 
		int cnt = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				cost += edge.weight;
				if (++cnt == N-1) break;
			}
		}
		System.out.println(cost);
	}
	
	static void makeSet() {
		for (int i=1;i<=N;i++) {
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
