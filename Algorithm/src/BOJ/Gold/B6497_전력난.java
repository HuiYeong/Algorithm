package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B6497_전력난 {
	static int M, N;
	static List<Edge> edgeList;
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
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 집의 수
			N = Integer.parseInt(st.nextToken()); // 길의 수
			if (M==0 && N==0) break;
			roots = new int[M];
			edgeList = new ArrayList<>();
			int totalCost = 0;
			for (int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				totalCost += weight;
				edgeList.add(new Edge(from, to, weight));
			}
			int cost = 0, cnt = 0;
			Collections.sort(edgeList);
			makeSet();
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					cost+=edge.weight;
					if (++cnt == M-1) break;
				}
			}
			sb.append((totalCost-cost)+"\n");
		}
		System.out.println(sb);
	}
	
	static void makeSet() {
		for (int i=0;i<M;i++) {
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
