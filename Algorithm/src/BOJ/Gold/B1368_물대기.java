package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B1368_물대기 {
	static int N, root[], ans, cnt;
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
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		root = new int[N+1];
		for (int i=1;i<=N;i++) {
			// 우물이라는 가상의 노드 (0)가 있다고 가정하여 간선 추가
			edgeList.add(new Edge(0, i, Integer.parseInt(br.readLine())));
		}
		StringTokenizer st = null;
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<=N;j++) {
				int value = Integer.parseInt(st.nextToken());
				if (i!=j) edgeList.add(new Edge(i, j, value)); // 간선 추가
			}
		}
		makeSet(); 
		Collections.sort(edgeList);
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) { // 연결이 안되어 있으면 연결 시켜주고
				ans += edge.weight; // 비용 추가
				if (++cnt == N) { // 우물 포함 모든 노드가 연결 되면 끝
					System.out.println(ans);
					System.exit(0);
				}
			}
		}
	}
	
	static void makeSet() {
		for (int i=0;i<=N;i++) {
			root[i] = -1;
		}
	}
	
	static int findSet(int x) {
		if (root[x] < 0) return x;
		return root[x] = findSet(root[x]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false; // 이미 연결되어있음
		root[bRoot] = aRoot; // 연결 시켜줌
		return true;
	}
}
