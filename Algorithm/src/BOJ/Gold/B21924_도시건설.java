package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B21924_도시건설 {
	static int N, M, root[], cnt;
	static long origin, ans;
	static List<Edge> edgeList = new ArrayList<>();
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
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			origin += weight;
			edgeList.add(new Edge(from, to, weight));
		}
		
		makeSet();
		Collections.sort(edgeList);
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				ans += edge.weight;
				if (++cnt == N-1) {
					System.out.println(origin-ans);
					System.exit(0);
				}
			}
		}
		System.out.println(-1);
	}
	
	static void makeSet() {
		for (int i=0;i<=N;i++) {
			root[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (root[x]<0) return x;
		return root[x] = findSet(root[x]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return false;
		root[bRoot] = aRoot;
		return true;
	}
}
