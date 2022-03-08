package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1717_집합의표현 {
	static int N, M;
	static int[] parents, rank;
	static void makeSet() {
		for (int i=0;i<=N;i++) {
			parents[i] = -1;
			rank[i] = 0;
		}
	}
	
	static int findSet(int x) {
		if (parents[x]<0) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot==bRoot) return;
		if (rank[aRoot]<rank[bRoot]) parents[aRoot] = bRoot;
		else parents[bRoot] = aRoot;
		
		if (rank[aRoot]==rank[bRoot]) rank[aRoot]++;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		rank = new int[N+1];
		makeSet();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int menu = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (menu==0) union(from, to);
			else {
				if (findSet(from)==findSet(to)) sb.append("YES\n");
				else sb.append("NO\n");
			}
		}
		System.out.println(sb);
	}
}
