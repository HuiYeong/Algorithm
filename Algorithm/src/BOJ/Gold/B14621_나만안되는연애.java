package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B14621_나만안되는연애 {
	static int N, M;
	static boolean[] uni;
	static int[] roots;
	static List<Edge> edgeList;
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

		@Override
		public String toString() {
			return from+" "+to+" "+weight;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		uni = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) {  
			if (st.nextToken().equals("W")) uni[i] = true;
		}
		roots = new int[N+1];
		edgeList = new ArrayList<>();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, weight));
		}
		Collections.sort(edgeList);
		makeSet();
		boolean flag = false;
		int cost = 0, cnt = 0;
		for (Edge edge : edgeList) {
			if (uni[edge.from]!=uni[edge.to] && union(edge.from, edge.to)) {
				cost += edge.weight;
				if (++cnt == N-1) {
					flag = true;
					break;
				}
			}
		}
		if (flag) System.out.println(cost);
		else System.out.println(-1);
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
