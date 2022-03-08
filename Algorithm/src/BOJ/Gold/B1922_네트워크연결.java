package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1922_네트워크연결 {
	static int N, M;
	static int[] parents;
	static Edge[] edgeList;
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

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
	}
	
	static void makeSet() {
		for (int i=1;i<=N;i++) {
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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		edgeList = new Edge[M];
		makeSet();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		Arrays.sort(edgeList);
		
		int ans = 0, cnt = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				ans+=edge.weight;
				cnt++;
			}
			if (cnt==N-1) break;
		}
		System.out.println(ans);
	}
}
