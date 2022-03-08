package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B10423_전기가부족해 {
	static int N, M, K, cnt;
	static int[] roots, rootCnt; 
	static boolean[] sup;
	static List<Edge> edgeList;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sup = new boolean[N+1]; // 발전소와 연결되어 있는지 확인하는 배열
		roots = new int[N+1]; // 루트 배열
		rootCnt = new int[N+1]; // 해당 루트에 연결되어 있는 노드 개수 관리하는 배열
		Arrays.fill(rootCnt, 1); // 해당 루트의 노드는 처음에는 전
		edgeList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<K;i++) {
			int powerNum = Integer.parseInt(st.nextToken());
			sup[powerNum] = true; 
		}
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, weight));
		}
		makeSet();
		Collections.sort(edgeList);
		int cost = 0;
		cnt = K; // 발전소와 연결된 노드 개수, 발전소인 노드는 이미 연결이 되어있다고 볼 수 있으므로 발전소 개수로 초기화
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				cost+=edge.weight;
				if (cnt==N) break;
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
		if (sup[x] && sup[y]) return false; // 둘다 발전소에 연결이 되어있으면 연결할 필요 없음
		// 발전소랑 연결
		if (sup[x] && !sup[y]) { 
			sup[y] = true; 
			cnt+=rootCnt[y]; // 해당 루트에 연결된 노드들도 모두 연결된거나 마찬가지이므로 해당 루트에 연결된 노드 개수를 더해줌
		} else if (!sup[x] && sup[y]) {
			sup[x] = true;
			cnt+=rootCnt[x];
		}
		roots[y] = x;
		rootCnt[y]--; rootCnt[x]++; // y의 루트가 x로 변경되었으므로 rootCnt 변경
		return true;
	}
}
