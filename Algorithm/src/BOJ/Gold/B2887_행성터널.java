package BOJ.Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B2887_행성터널 {
	static int N, root[], cnt, storeW;
	static Value from, to;
	static List<Value> X = new ArrayList<>(), Y = new ArrayList<>(), Z = new ArrayList<>();
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
	static class Value implements Comparable<Value> {
		int num, value;

		public Value(int num, int value) {
			super();
			this.num = num;
			this.value = value;
		}

		@Override
		public int compareTo(Value o) {
			return Integer.compare(this.value, o.value);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		root = new int[N+1];
		StringTokenizer st = null;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			X.add(new Value(i, Integer.parseInt(st.nextToken())));
			Y.add(new Value(i, Integer.parseInt(st.nextToken())));
			Z.add(new Value(i, Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(X); // x 좌표 정렬
		Collections.sort(Y); // y 좌표 정렬
		Collections.sort(Z); // z 좌표 정렬
		
		for (int i=0;i<N-1;i++) {
			from = X.get(i); to = X.get(i+1);
			edgeList.add(new Edge(from.num, to.num, Math.abs(from.value-to.value))); // 인접한 것들만 edgeList에 저장
			from = Y.get(i); to = Y.get(i+1);
			edgeList.add(new Edge(from.num, to.num, Math.abs(from.value-to.value)));
			from = Z.get(i); to = Z.get(i+1);
			edgeList.add(new Edge(from.num, to.num, Math.abs(from.value-to.value)));
		}
		
		long ans = 0;
		makeSet();
		Collections.sort(edgeList);
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) { // 아직 이어지지 않은 노드들이라면
				ans += edge.weight;
				if (++cnt == N-1) {
					System.out.println(ans);
					System.exit(0);
				}
			}
		}
	}
	
	static void makeSet() {
		for (int i=0;i<N+1;i++) {
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
		if (aRoot == bRoot) return false; // 이미 이어져있다면 놉
		root[bRoot] = aRoot; // 이어주쟈!
		return true;
	}
}
