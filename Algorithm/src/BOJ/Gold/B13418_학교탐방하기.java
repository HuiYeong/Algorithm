package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B13418_학교탐방하기 {
	static int N, M;
	static List<Edge> edgeList;
	static int[] roots;
	static class Edge {
		int from, to, updown;

		public Edge(int from, int to, int updown) {
			super();
			this.from = from;
			this.to = to;
			this.updown = updown;
		}

		@Override
		public String toString() {
			return from+" "+to+" "+updown;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		roots = new int[N+1];
		edgeList = new ArrayList<>();
		for (int i=0;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int updown = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, updown));
		}
		// 오르막길
		Collections.sort(edgeList, (o1, o2)-> {
			return Integer.compare(o1.updown, o2.updown);
		});
		makeSet();
		int cost = 0, cnt = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				if (edge.updown==0) cost++;
				if (++cnt == N) break;
			}
		}
		int ansUp = (int)Math.pow(cost, 2);
		Collections.sort(edgeList, (o1, o2)-> {
			return Integer.compare(o2.updown, o1.updown);
		});
		makeSet();
		cost = 0; cnt = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				if (edge.updown==0) cost++;
				if (++cnt == N) break;
			}
		}
		int ansDown = (int)Math.pow(cost, 2);
		System.out.println(ansUp - ansDown);
	}
	
	static void makeSet() {
		for (int i=0;i<=N;i++) {
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
