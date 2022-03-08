package BOJ.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16562_친구비 {
	static int N, M, K;
	static int[] parents, weight;
	
	static void makeSet() {
		for (int i=1;i<=N;i++) {
			parents[i] = -1;
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
		
		if (weight[aRoot]<weight[bRoot]) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		weight = new int[N+1];
		parents = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		makeSet();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			union(v, w);
//			if (weight[v] < weight[w]) union(v, w);
//			else union(w, v);
		}
		
		int ans = 0;
		for (int i=1;i<=N;i++) {
			if (parents[i]<0) ans+=weight[i];
		}
		if (ans<=K) System.out.println(ans);
		else System.out.println("Oh no");
	}
}
